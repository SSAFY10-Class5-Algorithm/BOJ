import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int L = sc.nextInt();
		
		int[] ball = new int[N];
		int index = 0;
		ball[index] += 1;
		int count = 0;
		
		while(true) {
			if(ball[index] == M)
				break;

			// 잡은 공이 홀수일 때
			if (ball[index] % 2 == 1) {
				index = (index + L) % N;
				ball[index] += 1;
				count ++;
			} 
			
			// 잡은 공이 짝수일 때
			else { 
				if (index >= L) {
					index = index - L;
					ball[index] += 1;
					count++;
				} else {
					index = N+index-L;
					ball[index] += 1;
					count++;
				}
			}	
		}
		System.out.println(count);
	}
}
