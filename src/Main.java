import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String code = """
                var a = 1;
                var b = 2;
                DEBUG!ListVar;
                if {
                var k = a;
                DEBUG!ListVar;
                }
                var j = 20;
                DEBUG!ListVar;
                """;
        Executor swiftExe = new Executor();
        swiftExe.run(code);
    }
}
