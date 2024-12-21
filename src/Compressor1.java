import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.Base64;
import java.util.HashMap;

public class Compressor1 {
    HashMap<Byte, Integer> freq = new HashMap<>();
    HashMap<Byte, Integer> dict = new HashMap<>();
    int padding = 0;
    public CompressedParms compress(String path) throws IOException {
        CompressedParms compressedParms = new CompressedParms();
        Time begin = new Time(System.currentTimeMillis());
        initializeFreq(path);
        encode();
//        for(byte key: dict.keySet())
//            System.out.println(Base64.getEncoder().encodeToString(new byte[]{key}) + " : " + dict.get(key));
        String newPath = convertPath(path,1);
        try (BufferedOutputStream bufferedOutputStream
                     = new BufferedOutputStream(new FileOutputStream(newPath))) {
            writeHeader(bufferedOutputStream);
            writeContent(bufferedOutputStream,path);
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
    private void initializeFreq(String path) {
        try (BufferedInputStream bufferedInputStream
                     = new BufferedInputStream(new FileInputStream(path))) {
            byte[] buffer = new byte[65536];
            int bytesRead;
            byte key;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                for (int i=0;i<bytesRead;i++) {
                    key = buffer[i];
                    freq.compute(key, (k, v) -> v == null ? 1 : v + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file.");
        }
    }
    private void encode(){
        Huffman1 huffman = new Huffman1();
        dict = huffman.encode(freq);
    }
    private void writeHeader(BufferedOutputStream bufferedOutputStream) throws IOException {
        bufferedWriting("n", 1, bufferedOutputStream);
        bufferedWriting("padding", 0, bufferedOutputStream);
        bufferedWriting("size", dict.size(), bufferedOutputStream);
        for (byte key : freq.keySet()) {
            String keyBase64 = Base64.getEncoder().encodeToString(new byte[]{key});
            bufferedWriting(keyBase64, dict.get(key), bufferedOutputStream);
        }
    }
    private void writeContent(BufferedOutputStream bufferedOutputStream, String oldPath) throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(oldPath))) {
            byte[] buffer = new byte[65536];
            int bytesRead;
            byte key;
            int encodedValue;
            int bitBuffer = 0;
            int bitCount = 0;
            int bitLength;
            while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                for (int i=0;i<bytesRead;i++){
                    key = buffer[i];
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
        if(value == null)
            System.out.println(key);
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