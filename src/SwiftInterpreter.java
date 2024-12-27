import java.io.Serial;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SwiftInterpreter extends toolKit1 {
    private String[] code;
    private Map<String, Object> Values = new HashMap<>();

    public void setCode(String code){
        this.code = code.split(";");
    }

    public void run(final int limit){
        int lim = limit;
        int ExeLine = 0;
        int FinishLine = code.length;

        while(ExeLine < FinishLine){
            int errorCode = detectFunction(code[ExeLine]);
            ExeLine ++;

            // exception detection
            if(errorCode != -1){
                System.out.println(errorCode + ") " + LogExeption(errorCode));
                break;
            }
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
        print(Line);

        if(len > 1) {
            VarName = Line[1];
            if(len > 2 && Line[2].equals("=")){
                if(len > 3) {
                    switch(Line[3].indexOf('"')){
                        case -1:
                            VarState = Line[3];
                            break;
                        case 0:
                            print(sliceArray(Line,3,len-1));
                    }
                }
            }
        }
        else{return 0;}

        return -1;
    }

    private String constructString(){
        return null;
    }

    //Other
    private String LogExeption(int e){
        String Error = "Exeption not found";
        switch (e){
            case 0:
                Error = "Syntax error";
                break;
            case 1:
                Error = "Null";
                break;
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
