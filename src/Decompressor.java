import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

public class Decompressor {
    HashMap<Integer, ByteBuffer> dict = new HashMap<>();
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
//            for(int key: dict.keySet())
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
        String line;
        String[] parts;
        char byteRead;
        while (buffer.hasRemaining()) {
            byteRead = (char) buffer.get();
            if (byteRead == '\n') {
                line = lineBuilder.toString();
                lineBuilder.setLength(0);
                parts = line.split(",", 2);
                switch (parts[0]){
                    case "padding":
                        padding = Integer.parseInt(parts[1]);
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
        for (int i=0;i<size;i++) {
            lineBuilder.setLength(0);
            ByteBuffer originalData;
            while (buffer.hasRemaining()) {
                byteRead = (char) buffer.get();
                if (byteRead == '\n') {
                    line = lineBuilder.toString();
                    lineBuilder.setLength(0);
                    parts = line.split(",", 2);
                    originalData = ByteBuffer.wrap(Base64.getDecoder().decode(parts[0]));
                    dict.put(Integer.valueOf(parts[1]), originalData);
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
            int bitBuffer = 1;
            byte curr;
            int k;
            byte[] decompressedBytes;
            while (buffer.hasRemaining()) {
                curr = buffer.get();
                k = buffer.hasRemaining() ? 7 : 7 - padding;
                if(!buffer.hasRemaining() && padding > 0)
                    curr = (byte) (curr >> padding);
                for (int i = k; i >= 0; i--) {
                    bitBuffer = (bitBuffer << 1) | ((curr >> i) & 1);
                    if (dict.containsKey(bitBuffer)) {
                        ByteBuffer decompressedDataBuffer = dict.get(bitBuffer);
                        decompressedBytes = decompressedDataBuffer.array();
                        bufferedOutputStream.write(decompressedBytes);
                        bitBuffer = 1;
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error writing the decompressed content.");
        }
    }
}
