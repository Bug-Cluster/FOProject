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

    void predicateOperations(String[] opp){
        switch(opp[0]){
            case "var":
                Variables.put(opp[1], getValue(opp[3]));
                debug("new variable created: "+opp[1]+opp[3]);
                break;
            case "print":
                break;
            case "if":
                break;
            case "while":
                break;
        }

        for (int i = 0; i < opp.length; i++) {
            String opTok = opp[i];
            int operationalDepth = 0; // depth of ()
            Stack<Integer> subOperationStart = new Stack<>();
            switch (opTok){
                case "(":
                    operationalDepth ++;
                    subOperationStart.push(i+1);
                case ")":
                    operationalDepth --;
                    if(operationalDepth < 0){
                        throw new SwiftSwap("now operations to close");
                    }
                    int subOpStart = subOperationStart.pop();
                    float a = arithmeticOperation(cutArray(opp,subOpStart,i-1));
                    opp = replaceArray(opp,subOpStart-1,i,String.valueOf(a));
                    i -= subOpStart;
            }
        }
        arithmeticOperation(opp);
    }

    //this executes operations without arithmetic order
    float arithmeticOperation(String[] opp){

        float output = (float) getValue(opp[0]);
        for (int i = 0; i < opp.length; i++) {
            String opTok = opp[i];
            switch (opTok){
                case "+":
                    output += (float) getValue(opp[i-1]);
                    break;
                case "-":
                    output -= (float) getValue(opp[i-1]);
                    break;
                case "/":
                    output /= (float) getValue(opp[i-1]);
                    break;
                case "*":
                    output *= (float) getValue(opp[i-1]);
                    break;
                case "%":
                    output %= (float) getValue(opp[i-1]);
                    break;
            }
        }
        return output;
    }

    private Object getValue(String s){
        Object output;
        try{
            output = Integer.parseInt(s);
        }
        catch (NumberFormatException e){
            if(Variables.containsKey(s)){
                output = Variables.get(s);
            }
            else{
                throw new SwiftSwap("Variable" + s + " does not exits");
            }
        }
        return output;
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