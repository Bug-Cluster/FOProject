public class Main {
    public static void main(String[] args) {
        String code = """
                var a = 32;
                var a=32;
                """;
        Executor swiftExe = new Executor();
        swiftExe.setCode(code);
        swiftExe.run(-1);
    }
}
