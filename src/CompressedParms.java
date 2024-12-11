public class CompressedParms {
    private int compressionRatio;
    private int compressionTime;
    public int getCompressionRatio() {
        return compressionRatio;
    }
    public void setCompressionRatio(int compressionRatio) {
        this.compressionRatio = compressionRatio;
    }
    public int getCompressionTime() {
        return compressionTime;
    }
    public void setCompressionTime(int compressionTime) {
        this.compressionTime = compressionTime;
    }
    private String formatTime(int timeInMs) {
        if (timeInMs >= 1000) {
            int timeInSeconds = timeInMs / 1000;
            return timeInSeconds + " s";
        } else {
            return timeInMs + " ms";
        }
    }
    @Override
    public String toString() {
        return "Compression ratio: " + compressionRatio + "% \n" +
                "Compression time: " + formatTime(compressionTime);
    }
}
