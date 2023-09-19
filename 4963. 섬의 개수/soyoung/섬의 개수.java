import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	// 상 우상 우 우하 하 좌하 좌 좌상 
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	
	
	static int w;
	static int h;
	static int[][] map;
	static boolean[][] visited;
	
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while (true) {		
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new int[h][w];
			visited = new boolean[h][w];
			
			if (w==0 && h==0) break;
			
			for (int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());                                   
				}
			}
			
			int cnt = 0; // 섬의 개수
			
			// 2차원 배열을 행 우선 순회하면서
			// 방문하지 않은 1을 만난 경우
			// 그 (r, c) 부터 DFS 탐색을 시작
			
			for (int r=0; r<h; r++) {
				for (int c=0; c<w; c++) {
					if (map[r][c] == 1 && !visited[r][c]) {
						 // 방문완 (먼저 방문 완료로 변경)
						visited[r][c] = true;
						// 시작점부터 탐색 시작 (섬 크기 탐색)
						dfs(r,c); 
						// 섬 발견했으므로 +1
						cnt++;
					}            
				}
			}
			
			System.out.println(cnt);
		} // while
	}

	private static void dfs(int r, int c) {
		
		// 현재 정점(r, c)
		// 이웃한 정점 중에서 아직 방문하지 않은 곳이 있다면
		// 그 정점으로 탐색을 계속 해 나가면 됨 (재귀)
		// 재귀 > 스택 > DFS
		
		// 1) 이웃한 정점 : 후보8개 > 배열 범위 안 > 1인 곳  
		// 2) 방문하지 않은 경우
		
		for (int d=0; d<8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr >= 0 && nc >= 0 && nr < h && nc < w 
					&& map[nr][nc] == 1
					&& !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr,nc);
			}
		}
	}
}//class