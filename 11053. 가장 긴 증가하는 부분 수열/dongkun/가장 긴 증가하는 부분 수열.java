import java.util.*;
import java.io.*;
public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] board = new int[T+1];
		int[] dp = new int[T+1];
		int max = 0;
		
		int count = 1;
		while(T-- > 0) {
			board[count] = Integer.parseInt(st.nextToken());
			count++;
		}count--;
		
		
		for(int i = 1 ; i <= count; i++) {
			dp[i] = 1;
			for(int j = 1; j <= i; j++) {
				if(board[j] < board[i] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}
			if(max < dp[i])max= dp[i];
		}
		System.out.println(max);
	}

}


