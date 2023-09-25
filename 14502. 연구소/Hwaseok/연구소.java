import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static List<int[]> list = new ArrayList<>();
	static int N;
	static int M;
	static int max=0;

	public static void main(String[] args) throws IOException {
		// 1단계
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		바이러스 찾기
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 2) {
					list.add(new int[] { i, j });
				}

			}
		}
		
		// 2단계
//		List<int[]> selected = new ArrayList<>();
		//재귀의 시작이자 끝나는 조건아랫것이 끝나면 재귀가 끝난다.
		callme(0, 0, new ArrayList<>());
		System.out.println(max);
	}

	// 3단계
	static void callme(int x, int y, List<int[]> selected) {
		// 종료조건
		if (selected.size() == 3) {
			int[][] copyarr = new int[arr.length][arr[0].length];
			for (int i = 0; i < copyarr.length; i++) {
				for (int j = 0; j < copyarr[0].length; j++) {
					copyarr[i][j] = arr[i][j];
				}

			}
			for (int i = 0; i < selected.size(); i++) {
				copyarr[selected.get(i)[0]][selected.get(i)[1]] = 1;
			}
			bfs(copyarr);
			return;
		}
		if (x == arr.length) {
			return;
		}

		// 4단계
		int nextX = x;
		int nextY = y + 1;
		if (nextY == arr[0].length) {
			nextX += 1;
			nextY = 0;
		}
		// 5단계
		if (arr[x][y] == 0) {
			// 현재좌표를 벽으로 바꾸는 경우
			selected.add(new int[] { x, y });

			callme(nextX, nextY, selected);
			selected.remove(selected.size() - 1);
			// 현재좌표를 벽으로 선택하지 않는 경우
			callme(nextX, nextY, selected);
		} else {
			callme(nextX, nextY, selected);
		}

	}

	static void bfs(int[][] copyarr) {
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };
		for (int i = 0; i < list.size(); i++) {
			Queue<Integer> queue1 = new LinkedList<>();
			Queue<Integer> queue2 = new LinkedList<>();
			queue1.add(list.get(i)[0]);
			queue2.add(list.get(i)[1]);
			while (!queue1.isEmpty()) {
				int a = queue1.poll();
				int b = queue2.poll();
				//바이러스 넣어주기
				for (int j = 0; j < dy.length; j++) {
					int nx = dx[j]+a;
					int ny = dy[j]+b;
					if(nx<copyarr.length && nx>=0 && ny<copyarr[0].length && ny>=0) {
						if(copyarr[nx][ny] == 0) {
							copyarr[nx][ny]=2;
							queue1.add(nx);
							queue2.add(ny);
						}
						
					}
				}
			}
		}
		
		
		int count = 0;
		for (int i = 0; i < copyarr.length; i++) {
			for (int j = 0; j < copyarr[0].length; j++) {
				if(copyarr[i][j]==0) {
					count++;
				}
			}
		}
		max = Math.max(count, max);
//		if(max == count) {
//			System.out.println(count+"------------------------------------");
//			for (int i = 0; i < copyarr.length; i++) {
//				System.out.println(Arrays.toString(copyarr[i]));
//			}
		}
	}