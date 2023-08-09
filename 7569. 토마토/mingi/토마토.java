import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static int dy[] = {-1,1,0,0,0,0}; // 상 하 좌 우 위 아래
	static int dx[] = {0,0,-1,1,0,0};
	static int dz[] = {0,0,0,0,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[1]); //상자의 행
		int M = Integer.parseInt(s[0]); // 상자의 열
		int H = Integer.parseInt(s[2]); // 상자의 높이
		int[][][] map = new int[N][M][H]; //토마토상자
		Queue<Integer[]> q = new LinkedList<>(); //토마토에 좌표를 저장하는 q, new Integer{r,c,h}

		int all = 0; // 모든 토마토가 익었는지 확인하기 위한 용도.
		for(int h=0; h<H; h++) {
			for(int r=0; r<N; r++) {
				String[] row = br.readLine().split(" "); 
					for(int c=0; c<M; c++) {
						int tomato = Integer.parseInt(row[c]);
						map[r][c][h] = tomato; //토마토 채우기
						if(tomato == 1 || tomato == -1) all++; //빈공간 또한 익은거로 처리
						if(tomato==1) {
							q.offer(new Integer[] {r,c,h}); //익은 토마토 좌표 채우기
						}
					}
			}
		}
		
		if(N*M*H == all) { // 모든 토마토가 익었다.
			System.out.println(0);
			return;
		}
		
		int time = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i=0; i<size; i++) {
				Integer[] index = q.poll(); //행,열,높이
				int r = index[0];
				int c = index[1];
				int h = index[2];
				
				for(int d=0; d<6; d++) {
					if(r+dy[d] >= 0 && r+dy[d]<N && c+dx[d] >= 0 && c+dx[d]<M && h+dz[d] >= 0 && h+dz[d]<H) {
						if(map[r+dy[d]][c+dx[d]][h+dz[d]]==1 || map[r+dy[d]][c+dx[d]][h+dz[d]]== -1) continue;
						q.offer(new Integer[] {r+dy[d],c+dx[d],h+dz[d]});
						map[r+dy[d]][c+dx[d]][h+dz[d]] = 1;
					}
				}
			}
			time++;
		}
		for(int h=0; h<H; h++) {
			for(int r=0; r<N; r++) {
				for(int c=0; c<M; c++) {
					if(map[r][c][h] == 0) {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		
		System.out.println(time-1);
	}
}