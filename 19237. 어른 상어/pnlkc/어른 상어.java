import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Pos {
	int x, y, d;
	List<List<Integer>> p = new ArrayList<>();

	public Pos(int y, int x) {
		for (int i = 0; i <= 5; i++) {
			p.add(new ArrayList<>());
		}

		this.x = x;
		this.y = y;
	}
}

class Smell {
	int n, t;

	public Smell(int n, int t) {
		this.n = n;
		this.t = t;
	}

	@Override
	public String toString() {
		return "Smell [n=" + n + ", t=" + t + "]";
	}
}

public class Main {
	static int[] dx = { 0, 0, 0, -1, 1 }; // 위, 아래, 왼쪽, 오른쪽
	static int[] dy = { 0, -1, 1, 0, 0 }; // 위, 아래, 왼쪽, 오른쪽

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st1.nextToken());
		int M = Integer.parseInt(st1.nextToken());
		int K = Integer.parseInt(st1.nextToken());
		int[][] map = new int[N][N];
		Smell[][] smell = new Smell[N][N];
		Map<Integer, Pos> sharkList = new HashMap<>();
		Map<Integer, Integer> dirMap = new HashMap<>();

		dirMap.put(1, 2);
		dirMap.put(2, 1);
		dirMap.put(3, 4);
		dirMap.put(4, 3);

		// 지도 초기화
		for (int i = 0; i < N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st2.nextToken());
				if (map[i][j] != 0) {
					sharkList.put(map[i][j], new Pos(i, j));
					smell[i][j] = new Smell(map[i][j], K);
				}
			}
		}

		// 상어들의 시작 방향
		StringTokenizer st3 = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			sharkList.get(i + 1).d = Integer.parseInt(st3.nextToken());
		}

		// 각 방향에 따른 우선순위
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < 4; j++) {
				StringTokenizer st4 = new StringTokenizer(br.readLine());
				sharkList.get(i + 1).p.get(j + 1).add(0);
				for (int k = 0; k < 4; k++) {
					sharkList.get(i + 1).p.get(j + 1).add(Integer.parseInt(st4.nextToken()));
				}
			}
		}
		
		int cnt = M;
		for (int t = 1; t <= 1000; t++) {
			int[][] tMap = new int[N][N];
			Map<Integer, Pos> tSharkList = new HashMap<>();

			// 상어 이동
			p: for (int key : sharkList.keySet()) {
				Pos shark = sharkList.get(key);

				List<Integer> dir = shark.p.get(shark.d);
				int self = 0;

				for (int i = 1; i <= 4; i++) {
					int nx = shark.x + dx[dir.get(i)];
					int ny = shark.y + dy[dir.get(i)];

					
					if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
						continue;
					}

					if (smell[ny][nx] != null) {
						if (smell[ny][nx].n == key && self == 0) {
							self = i;
						}
						continue;
					}

					if (tMap[ny][nx] != 0) {
						cnt--;
						
						if (tMap[ny][nx] > key) {
							tSharkList.remove(tMap[ny][nx]);
							tMap[ny][nx] = key;
							shark.x = nx;
							shark.y = ny;
							shark.d = dir.get(i);
							tSharkList.put(key, shark);
						}

						continue p;
					} else {
						tMap[ny][nx] = key;
						shark.x = nx;
						shark.y = ny;
						shark.d = dir.get(i);
						tSharkList.put(key, shark);

						continue p;
					}
				}

				shark.x += dx[dir.get(self)];
				shark.y += dy[dir.get(self)];
				shark.d = dir.get(self);
				tMap[shark.y][shark.x] = key;
				tSharkList.put(key, shark);
			}
			map = tMap;
			sharkList = tSharkList;

			// 냄새 지속턴 감소
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (smell[i][j] != null) {
						if (--smell[i][j].t == 0) {
							smell[i][j] = null;
						}
					}
				}
			}

			// 그 자리에 냄새 남기기
			for (int key : sharkList.keySet()) {
				Pos shark = sharkList.get(key);
				smell[shark.y][shark.x] = new Smell(key, K);
			}

			// 상어가 한마리만 남는 경우
			if (cnt == 1) { 
				System.out.println(t);
				return;
			}
		}

		System.out.println(-1);
	}
}