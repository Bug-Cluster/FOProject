public class toolKit1 {
    String[] sliceArray(String[] arr, int start, int end){
        if(end < start){
            throw new IllegalArgumentException("end should be greater than start");
        }
        String[] returnArray = new String[end-start+1];
        int j = 0;
        for (int i = start; i <= end; i++) {
            returnArray[j] = arr[i];
            j ++;
        }
        return returnArray;
    }

    boolean[] quoteDetection(String s){
        boolean[] output = new boolean[3];
        if(s == null){
            throw new IllegalArgumentException("string should not be null");
        }

        if(s.charAt(0) == '"'){
            output[0] = true;
        }
        for (int i = 1; i < s.length()-1; i++) {
            if(s.charAt(i) == '"'){
                output[1] = true;
                break;
            }
        }
        if(s.charAt(s.length()-1) =='"'){
            output[2] = true;
        }

        return output;
    }
}
