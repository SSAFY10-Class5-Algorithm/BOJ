import java.util.Scanner;

public class _6198_옥상정원꾸미기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		long[] arr = new long[N+1];
		
		for (int i = 1; i < N+1; i++) {
			arr[i] = sc.nextInt();
		}
		
		long count = 0;
		
		for (int i = 1; i < N+1; i++) {
			label : for (int j = i+1; j < N+1; j++) {
				if (arr[i] <= arr[j]) {
					break label;
				}
				count++;
			}
		}
		System.out.println(count);
	}
}
