import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
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
        try (FileInputStream fileInputStream = new FileInputStream(input);
                                        FileChannel fileChannel = fileInputStream.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate((int) fileChannel.size());
            fileChannel.read(buffer);
            buffer.flip();
            readHeader(buffer);
            String outputPath = convertPath(input);
            readAndWriteContent(buffer, outputPath);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
            int lastByte = 0;
            while (buffer.hasRemaining()) {
                byte curr = buffer.get();
//                int n = buffer.hasRemaining() ? 7 : 7 - padding;
                int n;
                if(buffer.hasRemaining())
                    n = 7;
                else {
                    lastByte++;
                    n = 7 - padding - 1;
                    System.out.println("padding"+padding);
                    System.out.println("n"+n);
                }
                if (lastByte > 1)
                    break;
                for (int i = n; i >= 0; i--) {
                    if(lastByte == 1)
                        System.out.println("before"+bitString);
                    bitString.append((curr >> i) & 1);
                    if(lastByte == 1)
                        System.out.println("after"+bitString);
                    if (dict.containsKey(bitString.toString())) {
                        ByteBuffer decompressedDataBuffer = dict.get(bitString.toString());
                        byte[] decompressedBytes = decompressedDataBuffer.array();
                        bufferedOutputStream.write(decompressedBytes);
                        System.out.println(bitString + " : " + Arrays.toString(decompressedBytes));
                        bitString.setLength(0);
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error writing the decompressed content.");
        }
    }
}
