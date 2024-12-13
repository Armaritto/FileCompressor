import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.Arrays;
import java.util.HashMap;

/**
 Buffered Input Stream
 <br>
 <a href="https://docs.oracle.com/javase/8/docs/api/java/io/BufferedInputStream.html">
 Oracle Docs
 </a>
 <a href="https://www.geeksforgeeks.org/java-io-bufferedinputstream-class-java/">
 Geeks for Geeks
 </a>
 <br>
 in Java, the read method of BufferedInputStream returns -1 to indicate the end of the file (EOF)
 <br><br>
 Random Access File
 <br>
 <a href="https://www.digitalocean.com/community/tutorials/java-randomaccessfile-example">
 Digital Ocean
 </a>
 <a href="https://docs.oracle.com/javase/8/docs/api/java/io/RandomAccessFile.html">
 Oracle Docs
 </a>
 */
public class Compressor {
    HashMap<String, Integer> freq = new HashMap<>();
    HashMap<String, String> dict = new HashMap<>();
    int padding = 0;
    public CompressedParms compress(String path, int n) throws IOException {
        CompressedParms compressedParms = new CompressedParms();
        Time begin = new Time(System.currentTimeMillis());
        initializeFreq(path,n);
        encode();
        System.out.println(dict);
        String newPath = convertPath(path,n);
        try (BufferedOutputStream bufferedOutputStream
                     = new BufferedOutputStream(new FileOutputStream(newPath))) {
            writeHeader(bufferedOutputStream,n);
            writeContent(bufferedOutputStream,path,n);
        }
        catch (IOException e) {
            System.out.println("Error writing the content.");
        }
        System.out.println("padding: "+padding);
        writePadding(newPath);
        Time end = new Time(System.currentTimeMillis());
        compressedParms.setCompressionTime((int) (end.getTime() - begin.getTime()));
        File oldFile = new File(path);
        File newFile = new File(newPath);
        compressedParms.setCompressionRatio((int) (newFile.length() * 100 / oldFile.length()));
        return compressedParms;
    }
    private void initializeFreq(String path, int n) {
        try (BufferedInputStream bufferedInputStream
                     = new BufferedInputStream(new FileInputStream(path))) {
            byte[] buffer = new byte[n];
            int bytesRead;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                if (bytesRead<n)
                    buffer = Arrays.copyOf(buffer, bytesRead);
                String key = new String(buffer);
                freq.put(key, freq.getOrDefault(key, 0) + 1);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
        }
    }
    private void encode(){
        Huffman huffman = new Huffman();
        dict = huffman.encode(freq);
    }
    private void writeHeader(BufferedOutputStream bufferedOutputStream, int n) throws IOException {
        bufferedWriting("padding", "0", bufferedOutputStream);
        bufferedWriting("n", String.valueOf(n), bufferedOutputStream);
        bufferedWriting("size", String.valueOf(dict.size()), bufferedOutputStream);
        for(String key: freq.keySet())
            bufferedWriting(key, dict.get(key), bufferedOutputStream);
    }
    private void writeContent(BufferedOutputStream bufferedOutputStream, String oldPath, int n) throws IOException {
        try (BufferedInputStream bufferedInputStream
                     = new BufferedInputStream(new FileInputStream (oldPath))) {
            byte[] buffer = new byte[n];
            int bytesRead;
            StringBuilder bitString = new StringBuilder();
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                if(bytesRead<n)
                    buffer = Arrays.copyOf(buffer, bytesRead);
                String key = new String(buffer);
                String encodedValue = dict.get(key);
                bitString.append(encodedValue);
                while(bitString.length()>=8) {
                    String byteString = bitString.substring(0, 8);
                    byte b = (byte) Integer.parseInt(byteString, 2);
                    bufferedOutputStream.write(b);
                    bitString.delete(0, 8);
                }
            }
            if(!bitString.isEmpty()){
                padding = 8 - bitString.length();
                while(bitString.length()<8)
                    bitString.append("0");
                String byteString = bitString.substring(0, 8);
                byte b = (byte) Integer.parseInt(byteString, 2);
                bufferedOutputStream.write(b);
            }
        }
        catch (IOException e) {
            System.out.println("Error writing the content.");
        }
    }
    private String convertPath(String path, int n) {
        int index = path.lastIndexOf('/');
        String dirPath = path.substring(0, index + 1);
        String filename = path.substring(index + 1);
        int dotIdx = filename.lastIndexOf('.');
        String fileName = filename.substring(0, dotIdx);
        String extension = filename.substring(dotIdx);
        String newFilename = "21010229." + n + "." + fileName + extension + ".hc";
        return dirPath + newFilename;
    }
    private static void bufferedWriting(String key, String value, BufferedOutputStream bufferedOutputStream) throws IOException {
        bufferedOutputStream.write(key.getBytes());
        bufferedOutputStream.write(",".getBytes());
        bufferedOutputStream.write(value.getBytes());
        bufferedOutputStream.write("\n".getBytes());
    }
    private void writePadding(String path) throws IOException {
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        boolean paddingUpdated = false;
        file.seek(0);
        String line;
        long writePosition = 0;
        while ((line = file.readLine()) != null) {
            String decodedLine = new String(line.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            if (decodedLine.startsWith("padding,")) {
                paddingUpdated = true;
                writePosition = file.getFilePointer() - line.getBytes(StandardCharsets.ISO_8859_1).length - 1;
                break;
            }
            if (!decodedLine.contains(",")) break;
        }
        if (paddingUpdated) {
            file.seek(writePosition);
            file.write(("padding," + padding + "\n").getBytes(StandardCharsets.UTF_8));
        }
        else {
            file.seek(0);
            file.write(("padding," + padding + "\n").getBytes(StandardCharsets.UTF_8));
        }
        file.close();
    }
}
