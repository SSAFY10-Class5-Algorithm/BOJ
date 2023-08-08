import java.util.*;
import java.io.*;
public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static int INF = 100_000_000;
	public static int[] dx = {0,1,0,-1};
	public static int[] dy = {-1,0,1,0};

	public static int R;
	public static int C;
	public static int T;
	public static int[][] board;
	public static int[][] tempBoard;
	public static int[] airU = new int[2];
	public static int[] airD = new int[2];
	public static Queue<Air> dirty = new LinkedList<>();
	
	public static class Air{
		int x;
		int y;
		int size;
		
		public Air() {}
		public Air(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		
		
		boolean isFind = false;
		for(int i = 0 ; i < R; i++) {
			board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for(int x = 0; x < C; x++){
				if(board[i][x] == -1 && !isFind) {
					airU[0] = i;
					airU[1] = x;
					airD[0] = i+1;
					airD[1] = x;
					isFind =true;
				}
				else if(board[i][x] > 0) {
					dirty.add(new Air(x,i,board[i][x]));
				}
			}
		}
		
		
		while(T-->0) {
			Queue<Air> qu = new LinkedList<>();
			int[][] visited = new int[R][C];
			tempBoard = new int[R][C];
			
			while(!dirty.isEmpty()) {
				qu.add(dirty.poll());
			}
			

			
			while(!qu.isEmpty()) {
				Air cur = qu.poll();
				
				if(visited[cur.y][cur.x]>0)continue;
				
				int count = 0;
				int spread = (int) Math.floor(cur.size / 5);
				for(int i = 0; i < 4; i++) {
					int mx = cur.x + dx[i];
					int my = cur.y + dy[i];
				
					if(mx < 0 || mx >= C || my <0 || my >= R || board[my][mx] == -1 )continue;
					count++;
					tempBoard[my][mx] += spread;
					
				}
				tempBoard[cur.y][cur.x] -= spread * count;
			}
			

			
			for(int i = 0 ; i < R; i++) {
				for(int j = 0 ; j < C;j++) {
					board[i][j] += tempBoard[i][j];
				}
			}
			
			
//			System.out.println();
//			for(int[] a : board)
//				System.out.println(Arrays.toString(a));

			
			cleaningTopAir();
			cleaningBottomAir();
			
			for(int i = 0 ; i < R; i++) {
				for(int j = 0 ; j < C;j++) {
					if(board[i][j] > 0) dirty.add(new Air(j,i,board[i][j]));
				}
			}


		}
		
		int answer = 2;
		
		for(int i = 0 ; i < R; i++) {
			for(int j = 0 ; j < C;j++) {
				answer += board[i][j];
			}
		}
		

		
		System.out.print(answer);

	}
	
	public static void cleaningTopAir() {
		for(int i = airU[0] - 1; i > 0; i--) {
			board[i][0] = board[i-1][0];
		}

		for(int i =0; i < C-1; i++) {
			board[0][i] = board[0][i+1];
		}
		
		for(int i = 0; i < airU[0] ; i++) {
			board[i][C-1] = board[i+1][C-1];
		}
		
		for(int i = C-1; i > 1; i--) {
			board[airU[0]][i] = board[airU[0]][i-1];
		}
		board[airU[0]][1] = 0;
	}
	public static void cleaningBottomAir() {
		for(int i = airD[0] + 1; i < R - 1; i++) {
			board[i][0] = board[i+1][0];
		}

		for(int i =0; i < C-1; i++) {
			board[R-1][i] = board[R-1][i+1];
		}
		
		for(int i = R-1; i > airD[0] ; i--) {
			board[i][C-1] = board[i-1][C-1];
		}
		
		for(int i = C-1; i > 1; i--) {
			board[airD[0]][i] = board[airD[0]][i-1];
		}
		board[airD[0]][1] = 0;
	}
}


//8 6 3
//5 0 0 0 0 5
//0 0 0 0 0 0
//-1 0 0 0 0 5
//-1 0 0 0 0 5
//0 0 0 0 0 0
//0 0 0 0 0 0
//0 0 0 0 0 0
//5 0 0 0 0 5