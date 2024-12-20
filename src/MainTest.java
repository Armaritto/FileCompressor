import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

class MainTest {
    static File file;
    StringBuilder sb;
    static int finalN = 5;
    static boolean runAll = true;
    @BeforeAll
    static void createFileToPutResults() {
        file = new File("/home/ubuntu/GitHub/FileCompressor/Tests/Results.txt");
        try {
            if (file.createNewFile())
                System.out.println("File created: " + file.getName());
            else
                System.out.println("File already exists.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    @BeforeEach
    void setUp() {
        sb = new StringBuilder();
    }
    private String rewriteSize(long n){
        if (n < 1024)
            return n + " B";
        n /= 1024;
        if (n < 1024)
            return n + " KB";
        n /= 1024;
        if (n < 1024)
            return n + " MB";
        n /= 1024;
        return n + " GB";
    }
    private void runTest(String fileName) throws IOException {
        for (int n = runAll ? 1 : finalN; n <= finalN; n++) {
            String path = "/home/ubuntu/GitHub/FileCompressor/Tests/" + fileName;
            String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/" + fileName, String.valueOf(n)};
            Main.main(args);
            sb.append(fileName).append(" (Size: ").append(rewriteSize(Files.size(Path.of(path)))).append(" bytes)").append(" n = ").append(n).append("\n");
            sb.append(Main.compressedParms.toString()).append("\n");

            args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229." + n + "." + fileName + ".hc"};
            Main.main(args);
            sb.append(Main.decompressionParms.toString()).append("\n");

            Assertions.assertTrue(CompareFiles.compare(fileName, n));
            sb.append("correct\n\n\n");
            Files.write(file.toPath(), sb.toString().getBytes(), StandardOpenOption.APPEND);
            sb.delete(0, sb.length());
        }
    }
    @Test
    void gbbct10_seq() throws IOException {
        runTest("gbbct10.seq");
    }
    @Test
    void largeTestFile_txt() throws IOException {
        runTest("largeTestFile.txt");
    }
    @Test
    void new_txt() throws IOException {
        runTest("new.txt");
    }
    @Test
    void discord_deb() throws IOException {
        runTest("discord.deb");
    }
    @Test
    void RickRoll_mp4() throws IOException {
        runTest("RickRoll.mp4");
    }
    @Test
    void Despacito_mp3() throws IOException {
        runTest("Despacito.mp3");
    }
    @Test
    void greedy_pdf() throws IOException {
        runTest("greedy.pdf");
    }
    @Test
    void parking_jpeg() throws IOException {
        runTest("parking.jpeg");
    }
    @Test
    void upmovie_jpg() throws IOException {
        runTest("up-movie.jpg");
    }
}