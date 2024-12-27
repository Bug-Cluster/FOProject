class Main{
    public static void main(String[] args) {
        String code = """
                var a = 1;
                var b = 2;
                a = a + b;
                """;
        SwiftInterpreter SwiftExecutor = new SwiftInterpreter();
        SwiftExecutor.setCode(code);
        SwiftExecutor.run(-1);
    }
}