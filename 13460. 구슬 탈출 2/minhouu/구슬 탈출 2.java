import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static int N;
	static int M;
	static char[][] board;
	static int[] dx = new int[] { -1, 0, 1, 0 };
	static int[] dy = new int[] { 0, 1, 0, -1 };
	static int result;

	static class node {
		int x;
		int y;
	}

	public static void main(String[] args) throws IOException {
		// 입력부
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		StringTokenizer st = new StringTokenizer(s);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		for (int i = 0; i < N; i++) {
			s = bf.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		result = INF;

		combination(0, new int[10]);
//		tilt(new int[] { 1, 2, 1, 0, 1, 2, 3, 0, 1, 2 });
//		tilt(new int[] { 3, 2, 3, 0, 1, 2, 2, 2, 2, 1 });
		System.out.println(result == INF ? -1 : result);
	}

	public static void combination(int cnt, int[] comb) {
		if (cnt == 10) {
			tilt(comb);
//			System.out.println(Arrays.toString(comb));
			return;
		}

		for (int i = 0; i < 4; i++) {
			comb[cnt] = i;
			combination(cnt + 1, comb);
		}
	}

	public static void tilt(int[] comb) {
		char[][] copiedBoard = new char[N][M];
		node r = new node();
		node b = new node();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copiedBoard[i][j] = board[i][j];
				if (board[i][j] == 'R') {
					r.x = i;
					r.y = j;
				}
				if (board[i][j] == 'B') {
					b.x = i;
					b.y = j;
				}
			}
		}
		boolean rEscape = false;
		boolean bEscape = false;
		for (int i = 0; i < 10; i++) {
			int dir = comb[i];
			boolean rStop = false;
			boolean bStop = false;
			while (!(rStop && bStop)) {
				int x1 = r.x + dx[dir];
				int y1 = r.y + dy[dir];
				int x2 = b.x + dx[dir];
				int y2 = b.y + dy[dir];

				if (copiedBoard[x1][y1] == '#') {
					rStop = true;
				}
				if (copiedBoard[x2][y2] == '#') {
					bStop = true;
				}

				if (copiedBoard[x1][y1] == 'O') {
					rEscape = true;
				}
				if (copiedBoard[x2][y2] == 'O') {
					bEscape = true;
				}

				if (copiedBoard[x1][y1] == 'B') {
					if (bStop) {
						rStop = true;
					}
				}
				if (copiedBoard[x2][y2] == 'R') {
					if (rStop) {
						bStop = true;
					}
				}

				if (rEscape) {
					copiedBoard[r.x][r.y] = '.';
					rStop = true;
				}
				if (bEscape) {
					copiedBoard[b.x][b.y] = '.';
					bStop = true;
				}

				if (!rStop) {
					copiedBoard[x1][y1] = 'R';
					if (copiedBoard[r.x][r.y] != 'B') {
						copiedBoard[r.x][r.y] = '.';
					}
					r.x = x1;
					r.y = y1;
				}
				if (!bStop) {
					copiedBoard[x2][y2] = 'B';
					if (copiedBoard[b.x][b.y] != 'R') {
						copiedBoard[b.x][b.y] = '.';
					}
					b.x = x2;
					b.y = y2;
				}

			}
			if (bEscape) {
				return;
			}
			
			if (rEscape) {
//				for (int j = 0; j < N; j++) {
//				for (int k = 0; k < M; k++) {
//					System.out.print(copiedBoard[j][k]);
//				}
//				System.out.println();
//			}

				result = Math.min(result, i + 1);
//				System.out.println(Arrays.toString(comb) + " " + (i + 1));
				break;
			}
//			for (int j = 0; j < N; j++) {
//				for (int k = 0; k < M; k++) {
//					System.out.print(copiedBoard[j][k]);
//				}
//				System.out.println();
//			}
		}
	}
}
