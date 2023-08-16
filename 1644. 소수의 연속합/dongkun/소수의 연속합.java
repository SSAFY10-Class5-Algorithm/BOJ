import java.util.*;
import java.io.*;


public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	public static StringBuilder sb = new StringBuilder();
	public static int INF = 100_000_000;
	public static int[] dx = {0,1,0,-1};
	public static int[] dy = {-1,0,1,0};

	public static void main(String[] args) throws IOException{
		int num = Integer.parseInt(br.readLine());
		int[] primes = getPrime(num);
		
		int answer = 0;
		
		int left = 0;
		int right = 0;
		
		while(right < primes.length) {
			int sum = 0;
			for(int i = left; i <=right; i++) {
				sum+=primes[i];
			}
			if(sum > num) {
				left++;
			}else if(sum < num) {
				right++;
			}else {
				left++;
				answer++;
			}
		}
		System.out.println(answer);

	}
	public static int[] getPrime(int num) {
		boolean[] visited = new boolean[num+1];
		visited[0] = true;
		visited[1] = true;
		
		for(int i = 2; Math.pow(i, 2) <= num; i++) {
			int c = 2;
			while(c * i <= num) {
				visited[c * i] = true;
				c++;
			}
		}

		ArrayList<Integer> nums = new ArrayList<>();
		
		for(int i = 0; i < visited.length;i++) {
			if(!visited[i]) {
				nums.add(i);
			}
		}
		int[] primes = new int[nums.size()];
		for(int i = 0; i < nums.size();i++)
			primes[i] = nums.get(i);
		return primes;
	}
}	
