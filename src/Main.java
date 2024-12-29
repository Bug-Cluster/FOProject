import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String code = """
                var a = 1;
                var b = 2;
                
                while a > 10 {
                print a;
                a = a+1;
                }
                
                var j = 20;
                """;
        Executor swiftExe = new Executor();
        swiftExe.run(code);
    }
}
