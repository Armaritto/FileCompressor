/**
    digit regex
    <br>
    <a href="https://stackoverflow.com/questions/2841550/what-does-d-mean-in-a-regular-expression">
    StackOverFlow
    </a>
 */
public class Main {
    public static CompressedParms compressedParms;
    public static DecompressionParms decompressionParms;
    public static void main(String[] args){
        Compressor1 compressor1 = new Compressor1();
        Compressor compressor = new Compressor();
        Decompressor decompressor = new Decompressor();
        if(args.length<2){
            System.out.println("Invalid number of arguments.");
            return;
        }
        char operator = args[0].charAt(0);
        if(operator!='c' && operator!='d'){
            System.out.println("Invalid operator.");
            return;
        }
        if(operator=='c' && args.length!=3){
            System.out.println("Invalid number of arguments.");
            return;
        }
        String path = args[1];
        if(operator=='c' && !args[2].matches("\\d+")){
            System.out.println("Invalid n.");
            return;
        }
        try{
            if(operator == 'c'){
                int n = Integer.parseInt(args[2]);
                System.out.println("Compressing...");
                if(n==1)
                    compressedParms = compressor1.compress(path);
                else
                    compressedParms = compressor.compress(path,n);
                System.out.println(compressedParms);
            }
            else{
                System.out.println("Decompressing...");
                decompressionParms = decompressor.decompress(path);
                System.out.println(decompressionParms);
            }
            System.out.println("Bye.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}