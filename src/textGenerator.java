import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class textGenerator {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LINE_LENGTH = 1000;
    private static final int NUM_LINES = 30000;

    public static void main(String[] args) {
        String fileName = "new.txt";
        generateLargeTestFile(fileName, NUM_LINES, LINE_LENGTH);
    }

    public static void generateLargeTestFile(String fileName, int numLines, int lineLength) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < numLines; i++) {
                writer.write(generateRandomLine(lineLength));
                writer.newLine();
            }
            System.out.println("File generated successfully: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomLine(int length) {
        Random random = new Random();
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            line.append(CHARACTERS.charAt(index));
            if(i%83 == 0 && i != 0)
                line.append("\n");
        }
        return line.toString();
    }
}