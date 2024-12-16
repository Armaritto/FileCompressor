import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.Arrays;
import java.util.Base64;
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
    HashMap<ByteBuffer, Integer> freq = new HashMap<>();
    HashMap<ByteBuffer, String> dict = new HashMap<>();
    int padding = 0;
    public CompressedParms compress(String path, int n) throws IOException {
        CompressedParms compressedParms = new CompressedParms();
        Time begin = new Time(System.currentTimeMillis());
        initializeFreq(path,n);
        encode();
//        for(ByteBuffer key: dict.keySet())
//            System.out.println(new String(key.array(), StandardCharsets.UTF_8) + " : " + dict.get(key));
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
                byte[] bufferCopy = Arrays.copyOf(buffer, bytesRead);
                ByteBuffer key = ByteBuffer.wrap(bufferCopy);
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
        for (ByteBuffer key : freq.keySet()) {
            String keyBase64 = Base64.getEncoder().encodeToString(key.array());
            bufferedWriting(keyBase64, dict.get(key), bufferedOutputStream);
        }
    }
    private void writeContent(BufferedOutputStream bufferedOutputStream, String oldPath, int n) throws IOException {
        try (BufferedInputStream bufferedInputStream
                     = new BufferedInputStream(new FileInputStream (oldPath))) {
            byte[] buffer = new byte[n];
            int bytesRead;
            StringBuilder bitString = new StringBuilder();
//            StringBuilder allBytes = new StringBuilder();
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                byte[] bufferCopy = Arrays.copyOf(buffer, bytesRead);
                ByteBuffer key = ByteBuffer.wrap(bufferCopy);
                String encodedValue = dict.get(key);
                if (encodedValue != null) {
                    bitString.append(encodedValue);
                    while (bitString.length()>=8) {
                        String byteString = bitString.substring(0, 8);
//                        allBytes.append(byteString);
                        byte b = (byte) Integer.parseInt(byteString, 2);
                        bufferedOutputStream.write(b);
                        bitString.delete(0, 8);
                    }
                }
                else
                    System.out.println("No encoding found for: " + Arrays.toString(bufferCopy));
            }
            if(!bitString.isEmpty()){
                padding = 8 - bitString.length();
                while(bitString.length()<8)
                    bitString.append("0");
                String byteString = bitString.substring(0, 8);
//                allBytes.append(byteString);
                byte b = (byte) Integer.parseInt(byteString, 2);
                bufferedOutputStream.write(b);
            }
//            System.out.println("allBytes: "+allBytes);
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
    private void writePadding(String path) throws IOException{
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        file.seek(0);
        file.write(("padding," + padding + "\n").getBytes(StandardCharsets.UTF_8));
        file.close();
    }
}