import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	public static int INF = Integer.MAX_VALUE;
	
	
	static long swapCount = 0;
    static long[] sorted;
	
	public static void main(String[] args) throws IOException {
		
		int N = Integer.parseInt(br.readLine());
       
        
        sorted = new long[N];
        long[] arr = new long[N];
 
        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
 
        mergeSort(arr, 0, N-1);
	        
        System.out.println(swapCount);
	}
    static void mergeSort(long[] arr, int low, int high) {
        if(low < high) {
            int mid = (low+high)/2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid+1, high);
            merge(arr, low, mid, high);
        }
    }
 
    static void merge(long[] arr, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int index = low;
 
        while(i <= mid && j <= high) {
            if(arr[i] <= arr[j])
                sorted[index++] = arr[i++];
            else {
                sorted[index++] = arr[j++];
                swapCount += (mid + 1 - i);
            }
        }
 
        while(i <= mid) 
            sorted[index++] = arr[i++];
        
        while(j <= high)
            sorted[index++] = arr[j++];
 
        for(int k=low; k<=high; k++)
            arr[k] = sorted[k];
    }
}

