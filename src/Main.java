import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String DebugCode = """
                var A = 20;
                let B = 10;
                
                if A > 0{
                    var c = 10;
                    if c > 0 {
                        var sub_d = 0;
                        DEBUG!ListVar;
                    }
                    DEBUG!ListVar;
                }
                DEBUG!ListVar;
                """;
        String SumOfN = """
                var N = 10;
                var Sum = 0;
                
                while N > 0 {
                    Sum = Sum + N;
                    N = N - 1;
                }
                print Sum;
                DEBUG!ListVar;
                """;
        String FactorialOfN = """
                let N = 5;
                let Fract = 1;
                while N > 0 {
                    Fract = Fract * N;
                    N  = N -1;
                }
                print Fract;
                DEBUG!ListVar;
                """;
        String GCD = """
                let a = 48;
                let b = 18;
                var GCD = 0;
                
                while b > 0 {
                    if b > a {
                        var temp = a;
                        a = b;
                        b = temp;
                    } 
                    
                    if b == 0 {
                        GCD = a;
                    }
                    a = a%b;
                }
                print GCD;
                """;
        String ReverseNum = """
               let number = 1234;
               let reversedNumber = 0;
                
               while number > 0 {
                    let lastDigit = number % 10;
                    reversedNumber = (reversedNumber * 10) + lastDigit;
                    number = number / 10;
               }
                
               print reversedNumber;
                """;

        String IsPalindrome = """
                let x = 101;
                let reversed = 0;
                let original = x;
                
                let digit = 0;
                while x > 0 {
                    digit = x % 10;
                    reversed = reversed * 10 + digit;
                    x = x/10;
                }
                
                print reversed;
                if original == reversed{
                    print true;
                }
                
                """;
        Executor swiftExe = new Executor();
        swiftExe.run(IsPalindrome);
    }
}
