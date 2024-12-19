import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest_2 {

    @Test
    void new_txt() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/new.txt", "2"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.2.new.txt.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("new.txt",2));
    }

    @Test
    void largeTestFile_txt() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/RickRoll.mp4", "2"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.2.RickRoll.mp4.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("RickRoll.mp4",2));
    }

    @Test
    void gbbct10_seq() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/gbbct10.seq", "2"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.2.gbbct10.seq.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("gbbct10.seq",2));
    }

    @Test
    void greedy_pdf() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/greedy.pdf", "2"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.2.greedy.pdf.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("greedy.pdf",2));
    }

    @Test
    void upmovie_jpg() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/up-movie.jpg", "2"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.2.up-movie.jpg.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("up-movie.jpg",2));
    }

    @Test
    void parking_jpeg() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/parking.jpeg", "2"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.2.parking.jpeg.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("parking.jpeg",2));
    }

    @Test
    void discord_deb() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/discord.deb", "2"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.2.discord.deb.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("discord.deb",2));
    }

    @Test
    void RickRoll_mp4() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/RickRoll.mp4", "2"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.2.RickRoll.mp4.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("RickRoll.mp4",2));
    }

    @Test
    void Despacito_mp3() {
        String[] args = {"c", "/home/ubuntu/GitHub/FileCompressor/Tests/Despacito.mp3", "2"};
        Main.main(args);
        args = new String[]{"d", "/home/ubuntu/GitHub/FileCompressor/Tests/21010229.2.Despacito.mp3.hc"};
        Main.main(args);
        Assertions.assertTrue(CompareFiles.compare("Despacito.mp3",2));
    }

}