import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SwiftInterpreter extends toolKit1 {
    private String[] code;
    private Map<String, Object> Values = new HashMap<>();

    public void setCode(String code){
        this.code = code.replace("\n","").split(";");
        print(this.code);
    }

    public void run(final int limit){
        int lim = limit;
        int ExeLine = 0;
        int FinishLine = code.length;

        while(ExeLine < FinishLine){
            int errorCode = detectFunction(code[ExeLine]);

            // exception detection
            if(errorCode != -1){
                System.out.println((ExeLine + 1) + ") " + errorCode + ": " + LogException(errorCode));
                break;
            }
            ExeLine ++;
        }
        System.out.println("Program has finished");
    }

    private int detectFunction(String codeLine){
        String[] Line = codeLine.split(" ");
        int ECode = -1;

        switch (Line[0]){
            case "var":
                ECode = CreateVariable(Line);
                break;
            default: ECode = 0;
        }
        return ECode;
    }


    //one line operations
    private int CreateVariable(String[] Line){
        String VarName;
        Object VarState = null;
        int len = Line.length;

        if(len > 1) {
            if(!safeToken(Line[1],3)){
                return 0;
            }
            VarName = Line[1];
            if(len > 2 && Line[2].equals("=")){
                if(len > 3) {
                    switch(Line[3].indexOf('"')){
                        case -1:
                            if(len != 4){
                                return 0;
                            }
                            VarState = constructNumber(Line[3]);
                            if(VarState == null){
                                return 0;
                            }
                            break;
                        case 0:
                            VarState = constructString(sliceArray(Line,3,len-1));
                            if(VarState == null){
                                return 0;
                            }
                            break;
                        default:
                            return 0;
                    }
                }
            }
        }
        else{return 0;}

        if(Values.containsKey(VarName)){
            return 2;
        }
        print("New variable created: " +  VarName + " = " + VarState);
        Values.put(VarName,VarState);
        return -1;
    }

    private String constructString(String[] array){
        if(array.length > 1){
            StringBuilder output = new StringBuilder();
            output.append(array[0].replace("\"",""));
            output.append(" ");
            for(int i = 1; i < array.length; i++) {
                if(array[i].indexOf('"') != -1){
                    boolean[] o = quoteDetection(array[i]);
                    if(o[1] || o[0]){
                        return null;
                    }
                    if(o[2] && i != array.length - 1){
                        return null;
                    }
                    output.append(array[i].replace("\"",""));
                }
                else {
                    if(i == array.length - 1){
                        return null;
                    }
                    output.append(array[i]);
                    output.append(" ");
                }
            }
            return output.toString();
        }
        else{
            boolean[] o = quoteDetection(array[0]);
            if(o[0] && !o[1] && o[2]){
                return array[0].replace("\"","");
            }
            else{
                return null;
            }
        }
    }

    private Object constructNumber(String s){
        if(!s.contains(".")){
            return Integer.parseInt(s);
        }
        else if(s.split("\\.").length == 2){
            if(s.charAt(0) == '.' || s.charAt(s.length()-1) == '.'){
                return null;
            }
            return Float.parseFloat(s);
        }
        return null;
    }
    //Other
    private String LogException(int e){
        String Error = "Exeption not found";
        switch (e){
            case 0:
                Error = "Syntax error";
                break;
            case 1:
                Error = "Null";
                break;
            case 2:
                Error = "Variable already exists";
        }
        return Error;
    }

    // For me, for quick debuting, should not be used in main code. (if i did not miss it)
    private void print(String i){
        System.out.println("Debug: " + i);
    }
    private void print(int i){
        System.out.println("Debug: " + i);
    }
    private void print(String[] i){
        System.out.println("Debug: " + Arrays.toString(i));
    }
}
