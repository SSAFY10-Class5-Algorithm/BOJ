import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, K, P, X, cnt;
	static int[] current, change;
	static int[][] led = new int[10][7];
	static int[][] howMany = new int[10][10];

	static {
		led[0] = new int[] { 1, 1, 1, 0, 1, 1, 1 };
		led[1] = new int[] { 0, 0, 1, 0, 0, 1, 0 };
		led[2] = new int[] { 1, 0, 1, 1, 1, 0, 1 };
		led[3] = new int[] { 1, 0, 1, 1, 0, 1, 1 };
		led[4] = new int[] { 0, 1, 1, 1, 0, 1, 0 };
		led[5] = new int[] { 1, 1, 0, 1, 0, 1, 1 };
		led[6] = new int[] { 1, 1, 0, 1, 1, 1, 1 };
		led[7] = new int[] { 1, 0, 1, 0, 0, 1, 0 };
		led[8] = new int[] { 1, 1, 1, 1, 1, 1, 1 };
		led[9] = new int[] { 1, 1, 1, 1, 0, 1, 1 };

		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				int n = 0;

				for (int k = 0; k < 7; k++) {
					if (led[i][k] != led[j][k])
						n++;
				}
				howMany[i][j] = n;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		current = new int[K];
		change = new int[K];

		String curr = String.valueOf(X);

		for (int i = 1; i <= curr.length(); i++) {
			current[K - i] = curr.charAt(curr.length() - i) - '0';
		}

		reverse(0, 0);

		sb.append(cnt);
		System.out.println(sb);
	}

	public static void reverse(int idx, int ledCnt) {
		if (ledCnt > P)
			return;

		if (idx == K) {
			if (ledCnt >= 1 && inRange(change)) {
				cnt++;
			}
			return;
		}

		for (int i = 0; i <= 9; i++) {
			change[idx] = i;
			reverse(idx + 1, ledCnt + howMany[current[idx]][change[idx]]);
		}
	}

	public static boolean inRange(int[] arr) {
		int num = 0;
		int dg = 1;

		for (int i = arr.length - 1; i >= 0; i--) {
			num += arr[i] * dg;
			dg *= 10;
		}

		if (num == 0 || num > N)
			return false;
		return true;
	}
}
