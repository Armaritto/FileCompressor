import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void new_txt() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/new.txt", "1"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/21010229.1.new.txt.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("new.txt"));
    }

    @Test
    void largeTestFile_txt() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/RickRoll.mp4", "1"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/21010229.1.RickRoll.mp4.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("RickRoll.mp4"));
    }

    @Test
    void gbbct10_seq() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/gbbct10.seq", "1"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/21010229.1.gbbct10.seq.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("gbbct10.seq"));
    }

    @Test
    void greedy_pdf() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/greedy.pdf", "1"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/21010229.1.greedy.pdf.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("greedy.pdf"));
    }

    @Test
    void upmovie_jpg() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/up-movie.jpg", "1"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/21010229.1.up-movie.jpg.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("up-movie.jpg"));
    }

    @Test
    void parking_jpeg() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/parking.jpeg", "1"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/21010229.1.parking.jpeg.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("parking.jpeg"));
    }

    @Test
    void discord_deb() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/discord.deb", "1"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/21010229.1.discord.deb.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("discord.deb"));
    }

    @Test
    void RickRoll_mp4() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/RickRoll.mp4", "1"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/21010229.1.RickRoll.mp4.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("RickRoll.mp4"));
    }

    @Test
    void Despacito_mp3() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Despacito.mp3", "1"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/21010229.1.Despacito.mp3.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("Despacito.mp3"));
    }

}