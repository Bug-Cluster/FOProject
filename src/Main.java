class Main{
    public static void main(String[] args) {
        String code = """
                var a = 1;
                var B = "i";
                if a > b {
                    print("a > b");
                }
                """;
        SwiftInterpreter SwiftExecutor = new SwiftInterpreter();
        SwiftExecutor.setCode(code);
        SwiftExecutor.run(-1);
    }
}