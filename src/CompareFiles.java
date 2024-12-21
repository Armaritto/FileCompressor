import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CompareFiles {
    public static boolean compare(String fileName, int n) {
        Path file1 = Paths.get("/home/ubuntu/GitHub/FileCompressor/Tests/extracted.21010229." + n + "." + fileName);
        Path file2 = Paths.get("/home/ubuntu/GitHub/FileCompressor/Tests/" + fileName);
        try{
            boolean isEqual = Files.mismatch(file1, file2) == -1;
            System.out.println("Files are equal: " + isEqual);
            return isEqual;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        System.out.println(compare("new.txt",1));
    }

}