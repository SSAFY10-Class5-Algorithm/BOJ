import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int N;
	static int M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		int map[][][] = new int[N][M][2];
		
		for(int r=0; r<N; r++) {
			String[] row = br.readLine().split("");
			for(int c=0; c<M; c++) {
				int num = Integer.parseInt(row[c]); 
				for(int h=0; h<2; h++) {
					map[r][c][h] = num;
				}
			}
		}
		map[0][0][0] = 1;
		map[0][0][1] = 1;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0,0,0});
		System.out.println(bfs(map,q));
		
	}
	static int bfs(int[][][] map, Queue<int[]> q) {
		int size = q.size();
		while(!q.isEmpty()) {
			for(int i=0; i<size; i++) {
				int r = q.peek()[0];
				int c = q.peek()[1];
				int m = q.poll()[2];
				if(r==N-1 && c==M-1) return map[r][c][m];
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
					if(map[nr][nc][m]==0) {
						map[nr][nc][m]=map[r][c][m]+1;
						q.offer(new int[] {nr,nc,m});
					}else if(m==0 && map[nr][nc][m]==1) {
						map[nr][nc][m+1]=map[r][c][m]+1;
						q.offer(new int[] {nr,nc,m+1});
					}
				}
			}
			size = q.size();
		}
		return -1;
	}
}