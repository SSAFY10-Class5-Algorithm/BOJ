import java.io.*;
import java.util.*;
 
public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static long[] dp;
	public static long c;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long a = Integer.parseInt(st.nextToken());
		long b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		System.out.println(getMod(a,b));
		
		bw.flush();
		bw.close();
		
	}
	
	public static long getMod(long num, long count) {
		
		if(count == 1)
			return num % c;
		
		
		long temp = getMod(num,count/2);
		
		if(count % 2 == 1)
			return (temp * temp % c) * num % c;
		
		return temp * temp % c;
		
		
	}
	
}