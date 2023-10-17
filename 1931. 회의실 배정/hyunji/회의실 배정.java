import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	
	static int N;
	static int meeting[][];
	static boolean time[];
	static int count;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		meeting = new int[N+1][2];
		time = new boolean[N+1];
		
		for(int i=0; i<N; i++) {
			meeting[i][0] = sc.nextInt();
			meeting[i][1] = sc.nextInt();
		}
		
		Arrays.sort(meeting, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				
				//종료시간이 같을 경우 시작 시간이 빠른 순으로 정렬
				if(o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}			
				
				return o1[1] - o2[1];
			}
		});
		
		count = 0;
		int end_time = 0;
		
		for(int i=1; i<=N; i++) {
			if(meeting[i][0] >= end_time) {
				count++;
				end_time = meeting[i][1];
			}
		}
		
		System.out.println(count);
		
	}
}