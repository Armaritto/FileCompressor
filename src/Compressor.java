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
 <br><br>
 Base64
 <br>
 <a href="https://www.baeldung.com/java-base64-encode-and-decode">
 Baeldung
 </a>
 <a href="https://www.geeksforgeeks.org/basic-type-base64-encoding-and-decoding-in-java/">
 Geeks for Geeks
 </a>
 <br><br>
 HashMap compute
 <br>
 <a href="https://stackoverflow.com/questions/70430071/for-hashmap-is-it-more-efficient-to-use-compute-or-put">
 StackOverFlow
 </a>
 */
public class Compressor {
    HashMap<ByteBuffer, Integer> freq = new HashMap<>();
    HashMap<ByteBuffer, Integer> dict = new HashMap<>();
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
            writeHeader(bufferedOutputStream);
            writeContent(bufferedOutputStream,path,n);
        }
        catch (IOException e) {
            System.out.println("Error writing the content.");
        }
//        System.out.println("padding: "+padding);
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
            byte[] buffer = new byte[65536];
            int bytesRead;
            ByteBuffer key;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                for (int i=0;i<bytesRead;i+=n) {
                    key = ByteBuffer.wrap(Arrays.copyOfRange(buffer, i, i + Math.min(n, bytesRead - i)));
                    freq.compute(key, (k, v) -> v == null ? 1 : v + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
        }
    }
    private void encode(){
        Huffman huffman = new Huffman();
        dict = huffman.encode(freq);
    }
    private void writeHeader(BufferedOutputStream bufferedOutputStream) throws IOException {
        bufferedWriting("padding", 0, bufferedOutputStream);
        bufferedWriting("size", dict.size(), bufferedOutputStream);
        for (ByteBuffer key : freq.keySet()) {
            String keyBase64 = Base64.getEncoder().encodeToString(key.array());
            bufferedWriting(keyBase64, dict.get(key), bufferedOutputStream);
        }
    }
    private void writeContent(BufferedOutputStream bufferedOutputStream, String oldPath, int n) throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(oldPath))) {
            byte[] buffer = new byte[65536];
            int bytesRead;
            ByteBuffer key;
            int encodedValue;
            int bitBuffer = 0;
            int bitCount = 0;
            int bitLength;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                for (int i=0;i<bytesRead;i+=n){
                    int length = Math.min(n, bytesRead - i);
                    key = ByteBuffer.wrap(Arrays.copyOfRange(buffer, i, i + length));
                    encodedValue = dict.get(key);
                    bitLength = Integer.SIZE - Integer.numberOfLeadingZeros(encodedValue) - 1;
                    for (int j=bitLength-1;j>=0;j--) {
                        bitBuffer = (bitBuffer << 1) | ((encodedValue >> j) & 1);
                        bitCount++;
                        if (bitCount == 8) {
                            bufferedOutputStream.write((byte) bitBuffer);
                            bitBuffer = 0;
                            bitCount = 0;
                        }
                    }
                }
            }
            if(bitCount > 0) {
                padding = 8 - bitCount;
                bitBuffer <<= (8 - bitCount);
                bufferedOutputStream.write((byte) bitBuffer);
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
    private static void bufferedWriting(String key, Integer value, BufferedOutputStream bufferedOutputStream) throws IOException {
        bufferedOutputStream.write(key.getBytes());
        bufferedOutputStream.write(",".getBytes());
        bufferedOutputStream.write(value.toString().getBytes()); //------------------->>
        bufferedOutputStream.write("\n".getBytes());
    }
    private void writePadding(String path) throws IOException{
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        file.seek(0);
        file.write(("padding," + padding + "\n").getBytes(StandardCharsets.UTF_8));
        file.close();
    }
}