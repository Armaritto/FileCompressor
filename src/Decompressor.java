import java.io.*;
import java.sql.Time;
import java.util.HashMap;

public class Decompressor {
    HashMap<String, String> dict = new HashMap<>();
    private int n;
    private int size;
    private int padding;
    public DecompressionParms decompress(String input) {
        DecompressionParms decompressionParms = new DecompressionParms();
        Time begin = new Time(System.currentTimeMillis());
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(input))) {
            readHeader(bufferedInputStream);
            String outputPath = convertPath(input);
//            printContent(bufferedInputStream);
            read_writeContent(bufferedInputStream, outputPath);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(n);
        System.out.println(size);
        System.out.println(dict);
        Time end = new Time(System.currentTimeMillis());
        decompressionParms.setDecompressionTime((int) (end.getTime() - begin.getTime()));
        return decompressionParms;
    }
    private void readHeader(BufferedInputStream inputStream) throws IOException {
        StringBuilder lineBuilder = new StringBuilder();
        int byteRead;
        boolean headerParsed = false;
        while ((byteRead = inputStream.read()) != -1) {
            if (byteRead == '\n') {
                String line = lineBuilder.toString();
                lineBuilder.setLength(0);
                line = line.replace("\\n", "\n");
                String[] parts = line.split(",");
                if (parts[0].equals("padding")) {
                    padding = Integer.parseInt(parts[1]);
                } else if (parts[0].equals("n")) {
                    n = Integer.parseInt(parts[1]);
                } else if (parts[0].equals("size")) {
                    size = Integer.parseInt(parts[1]);
                    headerParsed = true;
                    break;
                }
            }
            else
                lineBuilder.append((char) byteRead);
        }
        if (!headerParsed)
            throw new IOException("Header not properly formatted.");
        boolean newLineFlag = false;
        for (int i = 0; i < size; i++) {
            lineBuilder.setLength(0);
            while ((byteRead = inputStream.read()) != -1) {
                if (byteRead == '\n') {
                    String line = lineBuilder.toString();
                    lineBuilder.setLength(0);
                    if (line.isEmpty()) {
                        newLineFlag = true;
                        continue;
                    }
                    String[] parts = line.split(",", 2);
                    if (newLineFlag) {
                        dict.put(parts[1], "\n");
                        newLineFlag = false;
                    }
                    else
                        dict.put(parts[1], parts[0]);
                    break;
                }
                else
                    lineBuilder.append((char) byteRead);
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
    private void read_writeContent(BufferedInputStream bufferedInputStream, String path){
        try (BufferedOutputStream bufferedOutputStream
                     = new BufferedOutputStream(new FileOutputStream(path))) {
            int byteRead;
            boolean flag = false;
            StringBuilder sb = new StringBuilder();
            while((byteRead = bufferedInputStream.read()) != -1) {
                if(bufferedInputStream.available() == 0)
                    flag = true;
                byte curr = (byte) byteRead;
                for(int i=7;i>=0;i--) {
                    sb.append((curr>>i)&1);
                    if(dict.containsKey(sb.toString())){
                        bufferedOutputStream.write(dict.get(sb.toString()).getBytes());
                        sb.setLength(0);
                    }
                    if(flag && i==padding)
                        break;
                }
            }
        }
        catch (IOException e) {
            System.out.println("Error writing the content.");
        }
    }
    private void printContent(BufferedInputStream bufferedInputStream) throws IOException {
        int byteRead;
        StringBuilder sb = new StringBuilder();
        while((byteRead = bufferedInputStream.read()) != -1) {
            byte curr = (byte) byteRead;
            for(int i=7;i>=0;i--) {
                sb.append((curr>>i)&1);
            }
        }
        System.out.println(sb);
    }
}
