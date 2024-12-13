public class DecompressionParms {
    private int decompressionTime;
    public int getDecompressionTime() {
        return decompressionTime;
    }
    public void setDecompressionTime(int decompressionTime) {
        this.decompressionTime = decompressionTime;
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
        return "Decompression time: " + formatTime(decompressionTime);
    }
}
