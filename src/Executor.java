import java.util.*;

public class Executor extends toolKit {

    private String Code; //code it runs
    private int Pointer = 0; //tracks where executor is at
    private Map<String, Object> UpperVariables; //stores global variables (used only for sub loop in recursion)
    private Map<String, Object> Variables = new HashMap<>(); //stores local variables, (for main loop its global)

    // WARNING! : this method is for only recursion, almost same as normal .run() with minor changes (view other one for explanation via //comments)
    public void run(String code, Map<String, Object> Var){
        this.Code = code.replace("\n","");
        UpperVariables = Var; // import variables
        System.out.println(Var);
        System.out.println(Variables);
        int FinishLine = Code.length();
        while (Pointer <= FinishLine){
            if(Pointer == FinishLine){
                debug("Program has been executed");
                break;
            }
            String opp = nextOperation();
            if(opp == null)
                throw new SwiftSwap("Syntax Error");
            String[] tokOpp = tokenizeOpp(opp);

            debug(tokOpp);
            // predicates operation
            predicateOperations(tokOpp);

            Pointer ++;
        }
    }

    public void run(String code){

        this.Code = code.replace("\n","");

        int FinishLine = Code.length();

        while (Pointer <= FinishLine){

            if(Pointer == FinishLine){
                debug("Program has been executed");
                break;
            }

            // gets next operation (until ";" or "{" )
            String opp = nextOperation();

            // triggers if very last operation does not end
            if(opp == null)
                throw new SwiftSwap("Syntax Error");

            // tokenize into string array
            String[] tokOpp = tokenizeOpp(opp);

            debug(tokOpp);
            // predicates operation
            predicateOperations(tokOpp);

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
                output.append(c);
                debug("end at " + Code.charAt(Pointer));
                return output.toString();
            }
            else if (c == '}') {
                output.append(c);
                debug("end at " + Code.charAt(Pointer));
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
        String storageTarget = null;
        switch(opp[0]){
            case "var":
                if(opp[2].equals("=")){
                    float value = arithmeticOperationPreExe(
                            cutArray(opp,3,opp.length-1));
                    Variables.put(opp[1], value);
                    debug("new variable created: "+opp[1]+" = "+value);
                }
                else{
                    Variables.put(opp[1], null);
                    debug("new variable created: "+opp[1]);
                }
                break;
            case "print":
                break;
            case "if":
                String subCode = findSubCode(Pointer);
                Executor exe = new Executor();
                exe.run(subCode,Variables);
                break;
            case "while", "}":
                break;
            case "{":
                break;
            case "DEBUG!ListVar":
                String[] keys = Variables.keySet().toArray(new String[0]);
                Object[] values = Variables.values().toArray();
                System.out.println("==================");
                for(int i = 0; i < keys.length; i++) {
                    System.out.println(keys[i] +" = "+ values[i]);
                }
                System.out.println("==================");
                break;
            default:
                if(opp[1].equals("=")){
                    if(Variables.containsKey(opp[0])){
                        System.out.println(opp[0]);
                        float value = arithmeticOperationPreExe(
                                cutArray(opp,2,opp.length-1));
                        Variables.put(opp[0], value);
                        debug("variable "+opp[0]+" = "+value);
                    } else if (UpperVariables.containsKey(opp[0])) {
                        float value = arithmeticOperationPreExe(
                                cutArray(opp,2,opp.length-1));
                        UpperVariables.put(opp[0], value);
                        debug("variable "+opp[0]+" = "+value);
                    } else{
                        throw new SwiftSwap("Syntax error");
                    }
                }
        }
    }

    // calculates priority with depth
    float arithmeticOperationPreExe(String [] opp){
        int operationalDepth = 0; // depth of ()
        Stack<Integer> subOperationStart = new Stack<>();

        for (int i = 0; i < opp.length; i++) {
            String opTok = opp[i];
            switch (opTok){
                case "(":
                    operationalDepth ++;
                    subOperationStart.push(i+1);
                    break;
                case ")":
                    operationalDepth --;
                    if(operationalDepth < 0){
                        throw new SwiftSwap("no operations to close");
                    }
                    int subOpStart = subOperationStart.pop();
                    debug("oppDepth: "+subOpStart + " -> " + (i));
                    float a = arithmeticOperationExe(cutArray(opp,subOpStart,i-1));
                    opp = replaceArray(opp,subOpStart-1,i,String.valueOf(a));
                    i -= subOpStart;
                    debug(opp);
                    break;
            }
        }
        if(operationalDepth != 0){
            throw new SwiftSwap("operations are not closed");
        }
        debug(opp);
        //debug(arithmeticOperationExe(opp));
        return arithmeticOperationExe(opp);
    }

    // executes operations without arithmetic order
    float arithmeticOperationExe(String[] opp){

        float output = (float) getValue(opp[0]);
        for (int i = 0; i < opp.length; i++) {
            String opTok = opp[i];
            switch (opTok){
                case "+":
                    output += (float) getValue(opp[i+1]);
                    break;
                case "-":
                    output -= (float) getValue(opp[i+1]);
                    break;
                case "/":
                    output /= (float) getValue(opp[i+1]);
                    break;
                case "*":
                    output *= (float) getValue(opp[i+1]);
                    break;
                case "%":
                    output %= (float) getValue(opp[i+1]);
                    break;
            }
        }
        return output;
    }

    private Object getValue(String s){
        Object output;
        try{
            output = Float.parseFloat(s);
        }
        catch (NumberFormatException e){
            if(Variables.containsKey(s)){
                output = Variables.get(s);
            } else if (UpperVariables != null && UpperVariables.containsKey(s)) {
                output = UpperVariables.get(s);
            } else{
                throw new SwiftSwap("Variable " + s + " does not exits");
            }
        }
        return output;
    }

    private String findSubCode(int startPoint){
        int operationalDepth = 0;
        int codeStart = -1;
        for (int i = startPoint; i < Code.length(); i++) {
            if(Code.charAt(i) == '{'){
                if(operationalDepth == 0){
                    codeStart = i+1;
                }
                operationalDepth ++;
            }
            if(Code.charAt(i) == '}'){
                operationalDepth --;
            }
            if(operationalDepth == 0 && codeStart != -1){
                Pointer = i;
                return cutString(Code, codeStart, i-1);
            }
        }
        throw new SwiftSwap("end of code not found");
    }

    // for debug, not part of actual code
    private void debug(String s){
        //System.out.println("debug: " + s);
    }
    private void debug(String[] s){
        //System.out.println("debug: " + Arrays.toString(s));
    }
    private void debug(int i){
        //System.out.println("debug: " + i);
    }
    private void debug(float f){
        //System.out.println("debug: " + f);
    }
}