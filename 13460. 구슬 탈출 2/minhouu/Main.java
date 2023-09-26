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

	// 좌표 저장 위한 class
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

		// 10번 회전 시행하는 모든 경우의 수 탐색
		combination(0, new int[10]);
		System.out.println(result == INF ? -1 : result);
	}

	public static void combination(int cnt, int[] comb) {
		if (cnt == 10) {
			tilt(comb);
			return;
		}

		for (int i = 0; i < 4; i++) {
			comb[cnt] = i;
			combination(cnt + 1, comb);
		}
	}

	// comb[]에 들어있는 방향에 맞게 배열을 수정해주는 함수
	public static void tilt(int[] comb) {

		// 1. board를 복사한다
		// 복사 과정에서 R과 B의 위치를 각각 node로 저장한다
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

		// 2. 조건에 맞게 배열을 기울여서 R, B를 해당 방향으로 밀착시킨다

		boolean rEscape = false; // R이 탈출했는지 체크
		boolean bEscape = false; // B가 탈출했는지 체크

		for (int i = 0; i < 10; i++) {
			// 탈출조건 : 이미 result에 기록된 기울이기 횟수보다 i가 커진경우 return
			if (i > result - 1) {
				return;
			}
			int dir = comb[i];
			boolean rStop = false; // R이 벽에 닿아서 멈췄는지 확인
			boolean bStop = false; // B가 벽에 닿아서 멈췄는지 확인

			// R과 B가 모두 멈춘 경우가 아닐 때 while문이 돌아간다
			while (!(rStop && bStop)) {
				// R과 B의 dir방향 한 칸 앞 요소를 선택한다
				int x1 = r.x + dx[dir];
				int y1 = r.y + dy[dir];
				int x2 = b.x + dx[dir];
				int y2 = b.y + dy[dir];

				// 벽을 만나면 stop = true
				if (copiedBoard[x1][y1] == '#') {
					rStop = true;
				}
				if (copiedBoard[x2][y2] == '#') {
					bStop = true;
				}

				// O(탈출구)를 만나면 escape = true
				if (copiedBoard[x1][y1] == 'O') {
					rEscape = true;
				}
				if (copiedBoard[x2][y2] == 'O') {
					bEscape = true;
				}

				// 만약 앞에 다른 구슬이 있으면(R은 B, B는 R)
				if (copiedBoard[x1][y1] == 'B') {
					// 앞의 구슬이 멈춘 경우 뒤의 구슬도 멈추고 종료
					if (bStop) {
						rStop = true;
						continue;
					}
				}
				if (copiedBoard[x2][y2] == 'R') {
					if (rStop) {
						bStop = true;
						continue;
					}
				}

				// 만약 escape == true인 경우, copiedBoard 상의 R 혹은 B를 지운다
				if (rEscape) {
					copiedBoard[r.x][r.y] = '.';
					rStop = true;
				}
				if (bEscape) {
					copiedBoard[b.x][b.y] = '.';
					bStop = true;
				}

				// 해당 구슬이 멈추지 않은 경우, dir방향으로 한 칸 이동시킨다
				if (!rStop) {
					copiedBoard[x1][y1] = 'R';
					copiedBoard[r.x][r.y] = '.';
					r.x = x1;
					r.y = y1;
				}
				if (!bStop) {
					copiedBoard[x2][y2] = 'B';
					// 과거 위치에 다른 구슬이 올려져 있는 경우
					// 내 뒤에 붙어있는 구슬이 따라오고 있는 경우이므로
					// .으로 초기화해주지 않는다
					// ==> 바로 위의 if문에서 한 칸 앞으로 온 경우이기 때문
					if (copiedBoard[b.x][b.y] != 'R') {
						copiedBoard[b.x][b.y] = '.';
					}
					b.x = x2;
					b.y = y2;
				}

			}

			// while문이 끝나고 난 뒤
			// b가 탈출했다면 실패한것이므로 함수 끝낸다
			if (bEscape) {
				return;
			}

			// b가 탈출하지 않고 r만 탈출한 경우 result를 업데이트한다
			if (rEscape) {
				result = Math.min(result, i + 1);
				break;
			}
		}
	}
}
