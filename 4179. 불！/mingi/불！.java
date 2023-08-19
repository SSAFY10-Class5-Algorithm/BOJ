import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static String[][] map;
	static int N;
	static int M;
	static int time;
	static boolean posible;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<int[]> jihoon = new LinkedList<>(); //지훈이의 이동좌표
		Queue<int[]> fire = new LinkedList<>(); //불의 이동좌표
		String[] input = br.readLine().split(" ");
		
 		N = Integer.parseInt(input[0]);
 		M = Integer.parseInt(input[1]);

 		map = new String[N][M];


 		for(int r=0; r<N; r++) {
 			String[] row = br.readLine().split("");
 			for(int c=0; c<M; c++) {
 				if(row[c].equals("J")) jihoon.offer(new int[] {r,c});
 				if(row[c].equals("F")) fire.offer(new int[] {r,c});
 				map[r][c] = row[c];
 			}
 		}
 		
 		bfs(jihoon, fire);
 		if(posible) {
 			System.out.println(time);
 			return;
 		}
 		System.out.println("IMPOSSIBLE");
	}
	
	static void bfs(Queue<int[]> jihoon, Queue<int[]> fire) {
		while(!jihoon.isEmpty()) {
			int jSize = jihoon.size();
			int fSize = fire.size();
			
			for(int i=0; i<jSize; i++) {
				int r = jihoon.peek()[0];
				int c = jihoon.poll()[1];
				if(map[r][c].equals("F")) continue;
				
				if(r==0||r==N-1||c==0||c==M-1){
					 time++;
					posible = true;
					return;
				}
				for(int d=0; d<4; d++) {
					int nr = r+dy[d];
					int nc = c+dx[d];
					if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
						if(map[nr][nc].equals(".")) {
							jihoon.offer(new int[] {nr,nc});
							map[nr][nc] = "J";
						}
				}
			}
			time++;
			jSize = jihoon.size();
			
			for(int i=0; i<fSize; i++) {
				int r = fire.peek()[0];
				int c = fire.poll()[1];
				for(int d=0; d<4; d++) {
					int nr = r+dy[d];
					int nc = c+dx[d];
					if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
						if(!map[nr][nc].equals("F") && !map[nr][nc].equals("#")) {
							fire.offer(new int[] {nr,nc});
							map[nr][nc] = "F";
						}
				}
			}
			fSize = fire.size();
		}
	}
}