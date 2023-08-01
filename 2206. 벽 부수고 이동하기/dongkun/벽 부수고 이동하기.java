import java.util.*;
import java.io.*;
public class Main {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static int[][] board;
	public static boolean[][] visited;
	public static boolean[][] visited2;
	
	public static Queue<Position> qu = new LinkedList<>();
	
	public static int[] dx = {0,1,0,-1};
	public static int[] dy = {-1,0,1,0};
	
	public static class Position {
		int x;
		int y;
		boolean chance = true;
		int distance;
		
		public Position() {
			// TODO Auto-generated constructor stub
		}

		public Position(int x, int y, boolean chance,int distance) {
			super();
			this.x = x;
			this.y = y;
			this.chance = chance;
			this.distance = distance;
		}

		@Override
		public String toString() {
			return "Position [x=" + x + ", y=" + y + ", chance=" + chance + ", distance=" + distance + "]";
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int answer = -1;
		
		board = new int[R][C];
		visited = new boolean[R][C];
		visited2 = new boolean[R][C];
		
		
		for(int i = 0; i < R; i++) 
			board[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		

		qu.add(new Position(0,0,true,1));
		
		while(!qu.isEmpty()) {
			Position cur = qu.poll();
			if(cur.x == C - 1 && cur.y == R - 1) {
				answer = cur.distance;
				break;
			}
			
			if(cur.chance) {
				if(visited[cur.y][cur.x])continue;
				else {
					visited2[cur.y][cur.x] = true;
					visited[cur.y][cur.x] = true;
				}
				
			}else {
				if(visited2[cur.y][cur.x])continue;
				else 
					visited2[cur.y][cur.x] = true;
			}
			
			
			
			for(int i = 0; i < 4; i++) {
				int mx = cur.x + dx[i];
				int my = cur.y + dy[i];
				
				if(mx < 0 || mx >= C || my < 0 || my >= R)continue;
				
				if(cur.chance) {
					if(board[my][mx] == 0) {
						qu.add(new Position(mx,my,true,cur.distance+1));
					}else {
						qu.add(new Position(mx,my,false,cur.distance+1));
					}
				}else {
					if(board[my][mx] == 0) {
						qu.add(new Position(mx,my,false,cur.distance+1));
					}
				}
			}
		}
		System.out.println(answer);
		
	}
	
}