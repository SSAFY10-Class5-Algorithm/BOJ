import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
	int x, y;

	public Pos(int y, int x) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int N, L, R;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st1.nextToken()); // 맵 크기
		L = Integer.parseInt(st1.nextToken()); // 최소 필요 인구 차이
		R = Integer.parseInt(st1.nextToken()); // 최대 가능 인구 차이
		map = new int[N][N];
		
		// 지도 초기화
		for (int i = 0; i < N; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st2.nextToken());
			}
		}
		
		// 인구 이동
		int day = 0;
		boolean isChange = true; // 인구 이동 확인용 변수
		
		while (isChange) { // 인구 이동이 있는 동안 반복
			isChange = false;
			boolean[][] isVisit = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!isVisit[i][j]) {
						if (bfs(j, i, isVisit)) { // 인구 이동이 있는 경우
							isChange = true; // 변경이 있었다고 설정
						}
					}
				}
			}
			
			if (isChange) { // 인구 이동이 있었으면 날짜 증가
				day++;
			}
		}
		
		System.out.println(day);
	}
	
	// 국가 연합을 하기 위한 bfs
	public static boolean bfs(int x, int y, boolean[][] isVisit) {
		Queue<Pos> q = new LinkedList<>(); // bfs를 위한 Queue
		Queue<Pos> list = new LinkedList<>(); // 국가 연합 리스트
		q.offer(new Pos(y, x));
		list.offer(new Pos(y, x)); 
		isVisit[y][x] = true;
		int sum = map[y][x]; // 인구수 총합
		boolean isChange = false; // 인구 변화가 있었는지 확인하기 위한 변수
		
		while (!q.isEmpty()) { // bfs로 국가 연합 만들기
			Pos c = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = c.x + dx[d];
				int ny = c.y + dy[d];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) { // 범위를 벗어나는 경우
					continue;
				}
				
				if (isVisit[ny][nx]) { // 이미 방문한 경우
					continue;
				}
				
				int diff = Math.abs(map[c.y][c.x] - map[ny][nx]);
				if (diff < L || diff > R) { // 인구 차이가 요구조건을 충족하지 못하는 경우
					continue;
				}
				
				isVisit[ny][nx] = true;
				q.offer(new Pos(ny, nx));
				list.offer(new Pos(ny, nx));
				sum += map[ny][nx];
			}
		}
		
		int population = sum / list.size(); // 국가 연합 인구 평균
		while (!list.isEmpty()) { // 국가 연합 인구를 평균으로 변경
			Pos c = list.poll();
			if (map[c.y][c.x] != population) { // 인구 이동이 이루어진 경우
				map[c.y][c.x] = population;
				isChange = true;
			}
		}
		
		return isChange;
	}
}
