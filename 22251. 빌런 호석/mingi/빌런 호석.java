import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N,K,P,X;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]); //층수
		K = Integer.parseInt(in[1]); //led 자리수
		P = Integer.parseInt(in[2]); //반전 가능한 개수
		X = Integer.parseInt(in[3]); //실제 층수.

		int[][] map = {
				{0,4,3,3,4,3,2,3,1,2},
				{4,0,5,3,2,5,6,1,5,4},
				{3,5,0,2,5,4,3,4,2,3},
				{3,3,2,0,3,2,3,2,2,1},
				{4,2,5,3,0,3,4,3,3,2},
				{3,5,4,2,3,0,1,4,2,1},
				{2,6,3,3,4,1,0,5,1,2},
				{3,1,4,2,3,4,5,0,4,3},
				{1,5,2,2,3,2,1,4,0,1},
				{2,4,3,1,2,1,2,3,1,0},	
		};
		//1층부터 N층까지
		char[] target = new char[K];
		char[] x = String.valueOf(X).toCharArray(); 
		for(int i=0; i<x.length; i++) {
			target[K-1-i] = x[x.length-1-i];
		}
		
		int count = 0; //경우의수
		for(int i=1; i<=N; i++) {
			if(i == X) continue;
			int reverseNum = 0; //반전시킨 개수
			char[] temp = String.valueOf(i).toCharArray();
			char[] led = new char[K];
			for(int j=0; j<temp.length; j++) {
				led[K-1-j] = temp[temp.length-1-j];
			}

			for(int k=0; k<K; k++) {
				if(target[k]==led[k]) continue;
				int r = (target[k]==0) ? 0 : target[k]-48;
				int c = (led[k]==0) ? 0 : led[k]-48;
				reverseNum += map[r][c];
				if(reverseNum > P) break;
			}
			
			if(reverseNum <= P) count++;
		}
		System.out.println(count);
	}
}