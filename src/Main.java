class Main{
    public static void main(String[] args) {
        String code = """
                var a = 3;
                var b = 3;
                if a > b {
                    print("a > b");
                }
                """;
        SwiftInterpreter SwiftExecutor = new SwiftInterpreter();
        SwiftExecutor.setCode(code);
        SwiftExecutor.run(-1);
    }
}