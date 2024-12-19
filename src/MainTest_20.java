import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

class MainTest_20 {
    static File file;
    StringBuilder sb;
    @BeforeAll
    static void createFileToPutResults(){
        file = new File("/home/ubuntu/GitHub/FileCompressor/Tests/Results-20.txt");
        try {
            if (file.createNewFile())
                System.out.println("File created: " + file.getName());
            else
                System.out.println("File already exists.");
        }
        catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        sb = new StringBuilder();
    }

    @Test
    void new_txt() throws IOException {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/new.txt", "20"};
        Main.main(args);
        sb.append("new.txt\n");
        sb.append(Main.compressedParms.toString()).append("\n");

        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.20.new.txt.hc"};
        Main.main(args);
        sb.append(Main.decompressionParms.toString()).append("\n");

        Assertions.assertTrue(CompareFiles.compare("new.txt",20));
        sb.append("correct\n\n\n");
        Files.write(file.toPath(), sb.toString().getBytes(), StandardOpenOption.APPEND);
    }

    @Test
    void largeTestFile_txt() throws IOException {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/largeTestFile.txt", "20"};
        Main.main(args);
        sb.append("largeTestFile.txt\n");
        sb.append(Main.compressedParms.toString()).append("\n");

        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.20.largeTestFile.txt.hc"};
        Main.main(args);
        sb.append(Main.decompressionParms.toString()).append("\n");

        Assertions.assertTrue(CompareFiles.compare("largeTestFile.txt",20));
        sb.append("correct\n\n\n");
        Files.write(file.toPath(), sb.toString().getBytes(), StandardOpenOption.APPEND);
    }

    @Test
    void gbbct10_seq() throws IOException {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/gbbct10.seq", "20"};
        Main.main(args);
        sb.append("gbbct10.seq\n");
        sb.append(Main.compressedParms.toString()).append("\n");

        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.20.gbbct10.seq.hc"};
        Main.main(args);
        sb.append(Main.decompressionParms.toString()).append("\n");

        Assertions.assertTrue(CompareFiles.compare("gbbct10.seq",20));
        sb.append("correct\n\n\n");
        Files.write(file.toPath(), sb.toString().getBytes(), StandardOpenOption.APPEND);
    }

    @Test
    void greedy_pdf() throws IOException {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/greedy.pdf", "20"};
        Main.main(args);
        sb.append("greedy.pdf\n");
        sb.append(Main.compressedParms.toString()).append("\n");

        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.20.greedy.pdf.hc"};
        Main.main(args);
        sb.append(Main.decompressionParms.toString()).append("\n");

        Assertions.assertTrue(CompareFiles.compare("greedy.pdf",20));
        sb.append("correct\n\n\n");
        Files.write(file.toPath(), sb.toString().getBytes(), StandardOpenOption.APPEND);
    }

    @Test
    void upmovie_jpg() throws IOException {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/up-movie.jpg", "20"};
        Main.main(args);
        sb.append("up-movie.jpg\n");
        sb.append(Main.compressedParms.toString()).append("\n");

        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.20.up-movie.jpg.hc"};
        Main.main(args);
        sb.append(Main.decompressionParms.toString()).append("\n");

        Assertions.assertTrue(CompareFiles.compare("up-movie.jpg",20));
        sb.append("correct\n\n\n");
        Files.write(file.toPath(), sb.toString().getBytes(), StandardOpenOption.APPEND);
    }

    @Test
    void parking_jpeg() throws IOException {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/parking.jpeg", "20"};
        Main.main(args);
        sb.append("parking.jpeg\n");
        sb.append(Main.compressedParms.toString()).append("\n");

        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.20.parking.jpeg.hc"};
        Main.main(args);
        sb.append(Main.decompressionParms.toString()).append("\n");

        Assertions.assertTrue(CompareFiles.compare("parking.jpeg",20));
        sb.append("correct\n\n\n");
        Files.write(file.toPath(), sb.toString().getBytes(), StandardOpenOption.APPEND);
    }

    @Test
    void discord_deb() throws IOException {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/discord.deb", "20"};
        Main.main(args);
        sb.append("discord.deb\n");
        sb.append(Main.compressedParms.toString()).append("\n");

        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.20.discord.deb.hc"};
        Main.main(args);
        sb.append(Main.decompressionParms.toString()).append("\n");

        Assertions.assertTrue(CompareFiles.compare("discord.deb",20));
        sb.append("correct\n\n\n");
        Files.write(file.toPath(), sb.toString().getBytes(), StandardOpenOption.APPEND);
    }

    @Test
    void RickRoll_mp4() throws IOException {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/RickRoll.mp4", "20"};
        Main.main(args);
        sb.append("RickRoll.mp4\n");
        sb.append(Main.compressedParms.toString()).append("\n");

        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.20.RickRoll.mp4.hc"};
        Main.main(args);
        sb.append(Main.decompressionParms.toString()).append("\n");

        Assertions.assertTrue(CompareFiles.compare("RickRoll.mp4",20));
        sb.append("correct\n\n\n");
        Files.write(file.toPath(), sb.toString().getBytes(), StandardOpenOption.APPEND);
    }

    @Test
    void Despacito_mp3() throws IOException {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/Despacito.mp3", "20"};
        Main.main(args);
        sb.append("Despacito.mp3\n");
        sb.append(Main.compressedParms.toString()).append("\n");

        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.20.Despacito.mp3.hc"};
        Main.main(args);
        sb.append(Main.decompressionParms.toString()).append("\n");

        Assertions.assertTrue(CompareFiles.compare("Despacito.mp3",20));
        sb.append("correct\n\n\n");
        Files.write(file.toPath(), sb.toString().getBytes(), StandardOpenOption.APPEND);
    }

}