import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

public class Decompressor {
    HashMap<String, ByteBuffer> dict = new HashMap<>();
    private int n;
    private int size;
    private int padding;
    public DecompressionParms decompress(String input) {
        DecompressionParms decompressionParms = new DecompressionParms();
        Time begin = new Time(System.currentTimeMillis());
        try (FileInputStream fileInputStream = new FileInputStream(input);
                                        FileChannel fileChannel = fileInputStream.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(buffer);
            buffer.flip();
            readHeader(buffer);
//            for(String key: dict.keySet())
//                System.out.println(key + " : " + new String(dict.get(key).array(), StandardCharsets.UTF_8));
            String outputPath = convertPath(input);
            readAndWriteContent(buffer, outputPath);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Time end = new Time(System.currentTimeMillis());
        decompressionParms.setDecompressionTime((int) (end.getTime() - begin.getTime()));
        return decompressionParms;
    }

    private void readHeader(ByteBuffer buffer) throws IOException {
        StringBuilder lineBuilder = new StringBuilder();
        while (buffer.hasRemaining()) {
            char byteRead = (char) buffer.get();
            if (byteRead == '\n') {
                String line = lineBuilder.toString();
                lineBuilder.setLength(0);
                String[] parts = line.split(",", 2);
                switch (parts[0]){
                    case "padding":
                        padding = Integer.parseInt(parts[1]);
                        break;
                    case "n":
                        n = Integer.parseInt(parts[1]);
                        break;
                    case "size":
                        size = Integer.parseInt(parts[1]);
                        break;
                }
                if (parts[0].equals("size"))
                    break;
            }
            else
                lineBuilder.append(byteRead);
        }
        for (int i = 0; i < size; i++) {
            lineBuilder.setLength(0);
            while (buffer.hasRemaining()) {
                char byteRead = (char) buffer.get();
                if (byteRead == '\n') {
                    String line = lineBuilder.toString();
                    lineBuilder.setLength(0);
                    String[] parts = line.split(",", 2);
                    ByteBuffer originalData = ByteBuffer.wrap(Base64.getDecoder().decode(parts[0]));
                    dict.put(parts[1], originalData);
                    break;
                }
                else
                    lineBuilder.append(byteRead);
            }
        }
    }

    private String convertPath(String path) {
        int index = path.lastIndexOf('/');
        String dirPath = path.substring(0, index + 1);
        String filename = path.substring(index + 1);
        int dotIdx = filename.lastIndexOf('.');
        String fileName = filename.substring(0, dotIdx);
        String newFilename = "extracted." + fileName;
        return dirPath + newFilename;
    }
    private void readAndWriteContent(ByteBuffer buffer, String path) {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path))) {
            StringBuilder bitString = new StringBuilder();
//            StringBuilder allBytes = new StringBuilder();
            while (buffer.hasRemaining()) {
                byte curr = buffer.get();
                int k = buffer.hasRemaining() ? 7 : 7 - padding;
                if(!buffer.hasRemaining() && padding > 0)
                    curr = (byte) (curr >> padding);
                for (int i = k; i >= 0; i--) {
                    bitString.append((curr >> i) & 1);
//                    allBytes.append((curr >> i) & 1);
                    ByteBuffer decompressedDataBuffer = dict.get(bitString.toString());
                    if (decompressedDataBuffer != null) {
                        byte[] decompressedBytes = decompressedDataBuffer.array();
                        bufferedOutputStream.write(decompressedBytes);
                        bitString.setLength(0);
                    }
                }
            }
//            System.out.println("allBytes: "+allBytes);
        }
        catch (IOException e) {
            System.out.println("Error writing the decompressed content.");
        }
    }
}
