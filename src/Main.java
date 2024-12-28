import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String code = """
                var a = 10 + (10*(20-10));
                a = 20;
                var b = 100-5;
                DEBUG!ListVar;
                """;
        Executor swiftExe = new Executor();
        swiftExe.setCode(code);
        swiftExe.run(-1);
    }
}
