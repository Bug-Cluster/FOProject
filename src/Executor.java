import java.util.*;

public class Executor extends toolKit {

    private String Code; //code it runs
    private int Pointer = 0; //tracks where executor is at
    private Map<String, Object> Variables = new HashMap<>();
    private int subLoopDepth = 0;

    public void setCode(String code){
        this.Code = code.replace("\n","");
    }

    public void run(final int limit){
        int ExeCycle = 0; //counts how many times new line was executed

        int FinishLine = Code.length();

        while (Pointer <= FinishLine){

            // gets next operation (until ";" or "{" )
            String opp = nextOperation();

            // triggers if very last operation does not end
            if(opp == null)
                throw new SwiftSwap("Syntax Error");

            // tokenize into string array
            String[] tokOpp = tokenizeOpp(opp);

            // predicates operation

            debug(tokOpp);
            Pointer ++;
        }
    }

    private String nextOperation(){
        //int startPointer = Pointer;
        StringBuilder output = new StringBuilder();
        while(Pointer < Code.length()){
            char c = Code.charAt(Pointer);
            if(c == ';'){
                return output.toString();
            }
            else if(c == '{'){
                //subLoopDepth++;
                return output.toString();
            }

            output.append(c);
            Pointer ++;
        }
        return null;
    }

    //advanced splitter with multiple control values
    String[] tokenizeOpp(String opp){
        List<String> tokenList = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < opp.length(); i++) {
            char c = opp.charAt(i);

            if(c == ' '){
                if(!token.isEmpty()){
                    tokenList.add(token.toString());
                    token.setLength(0);
                }
            }
            else if(c == '=' || c == '+' || c == '-' || c == '/' || c == '*' || c == '%' || c == '(' || c == ')' || c == '{' || c == '}' || c == ':'){
                if(!token.isEmpty()){
                    tokenList.add(token.toString());
                    token.setLength(0);
                }
                tokenList.add(Character.toString(c));
            }
            else{
               token.append(c);
            }
        }
        if(!token.isEmpty()){
            tokenList.add(token.toString());
            token.setLength(0);
        }

        String[] output = new String[tokenList.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = tokenList.get(i);
        }
        return output;
    }

    void predicateOperation(String[] opp){
        //writing...
    }

    private void debug(String s){
        System.out.println("debug: " + s);
    }
    private void debug(String[] s){
        System.out.println("debug: " + Arrays.toString(s));
    }
    private void debug(int i){
        System.out.println("debug: " + i);
    }
}