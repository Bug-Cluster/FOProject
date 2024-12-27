public class toolKit1 {
    String[] sliceArray(String[] arr, int start, int end){
        if(end > start){
            throw new IllegalArgumentException();
        }
        String[] returnArray = new String[end-start];
        int j = 0;
        for (int i = start; i < end; i++) {
            j ++;
            returnArray[j] = arr[i];
        }
        return returnArray;
    }
}
