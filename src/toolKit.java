public class toolKit {
    boolean[] findChar(String s, char c){
        boolean[] output = new boolean[4]; // [exists, first, mid, last]
        if(s.charAt(0) == c){
            output[1] = true;
            output[0] = true;
        }
        if(s.charAt(s.length()-1) == c){
            output[3] = true;
            output[0] = true;
        }
        for (int i = 1; i < s.length()-1; i++) {
            if(s.charAt(i) == c){
                output[2] = true;
                output[0] = true;
            }
        }
        return output;
    }

    String[] cutArray(String[] arr, int start, int end){
        if(end < start){
            throw new IllegalArgumentException("end should be greater than start");
        }
        if(end > arr.length - 1){
            throw new IllegalArgumentException("end should be in range of array");
        }
        if(start < 0){
            throw new IllegalArgumentException("start should be positive");
        }

        String[] returnArray = new String[end-start+1];
        int j = 0;
        for (int i = start; i <= end; i++) {
            returnArray[j] = arr[i];
            j ++;
        }
        return returnArray;
    }

    String[] replaceArray(String[] arr, int start, int end, String replacement){
        if(end < start){
            throw new IllegalArgumentException("end should be greater than start");
        }
        if(end > arr.length - 1){
            throw new IllegalArgumentException("end should be in range of array");
        }
        if(start < 0){
            throw new IllegalArgumentException("start should be positive");
        }

        String[] returnArray = new String[arr.length-(end-start)];

        int j = 0;
        for (int i = 0; i < returnArray.length; i++) {
            if(j != start){
                returnArray[i] = arr[j];
                j++;
            }
            else{
                returnArray[i] = replacement;
                j = end+1;
            }
        }
        return returnArray;
    }

    String cutString(String s, int start, int end){
        if(end < start){
            throw new IllegalArgumentException("end should be greater than start");
        }
        if(end > s.length() - 1){
            throw new IllegalArgumentException("end should be in range of array");
        }
        if(start < 0){
            throw new IllegalArgumentException("start should be positive");
        }

        StringBuilder output = new StringBuilder();
        for (int i = start; i <= end; i++) {
            output.append(s.charAt(i));
        }
        return output.toString();
    }
}
