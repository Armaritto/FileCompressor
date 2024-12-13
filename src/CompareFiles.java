import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CompareFiles {
    public static void main(String[] args) {
        Path file1 = Paths.get("/home/ubuntu/GitHub/FileCompressor/new.txt");
        Path file2 = Paths.get("/home/ubuntu/GitHub/FileCompressor/extracted.21010229.1.new.txt");
        try {
            boolean isEqual = Files.mismatch(file1, file2) == -1;
            System.out.println("Files are equal: " + isEqual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}