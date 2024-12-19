import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest_100 {

    @Test
    void new_txt() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/new.txt", "100"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.100.new.txt.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("new.txt",100));
    }

    @Test
    void largeTestFile_txt() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/largeTestFile.txt", "100"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.100.largeTestFile.txt.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("largeTestFile.txt",100));
    }

    @Test
    void gbbct10_seq() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/gbbct10.seq", "100"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.100.gbbct10.seq.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("gbbct10.seq",100));
    }

    @Test
    void greedy_pdf() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/greedy.pdf", "100"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.100.greedy.pdf.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("greedy.pdf",100));
    }

    @Test
    void upmovie_jpg() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/up-movie.jpg", "100"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.100.up-movie.jpg.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("up-movie.jpg",100));
    }

    @Test
    void parking_jpeg() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/parking.jpeg", "100"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.100.parking.jpeg.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("parking.jpeg",100));
    }

    @Test
    void discord_deb() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/discord.deb", "100"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.100.discord.deb.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("discord.deb",100));
    }

    @Test
    void RickRoll_mp4() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/RickRoll.mp4", "100"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.100.RickRoll.mp4.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("RickRoll.mp4",100));
    }

    @Test
    void Despacito_mp3() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/Despacito.mp3", "100"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.100.Despacito.mp3.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("Despacito.mp3",100));
    }

}