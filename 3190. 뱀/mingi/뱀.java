import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static int[] dy = {0,1,0,-1}; //우 하 좌 상 (시계 방향)
	static int[] dx = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		int time = 0;
		map[0][0] = 1;
		
		for(int k=0; k<K; k++) { //사과 세팅
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			map[r][c] = 2;
		}
		
		int L = Integer.parseInt(br.readLine()); // 회전 횟수;
		Queue<String[]> dir = new LinkedList<>();
		
		for(int l=0; l<L; l++) { //회전 세팅
			StringTokenizer st = new StringTokenizer(br.readLine());
			String t = st.nextToken();
			String d = st.nextToken();
			dir.offer(new String[] {t,d});
		}
		
		Queue<Integer[]> q = new LinkedList<>(); //뱀의 자취를 담은 Queue
		q.offer(new Integer[] {0,0}); //시작 위치 좌표 자취 Queue에 담기
		int d = 0; //시작방향
		int r = 0; //시작좌표
		int c = 0;
		
		while(true) {
			if(!dir.isEmpty()) { // 방향전환
				String[] turn = dir.peek();
				if(Integer.parseInt(turn[0]) == time) {
					dir.poll();
					if(turn[1].equals("D")) {
						d += 1;
						d = d%4;
						if(d<0) d+=4;
					}else if(turn[1].equals("L")) {
						d -= 1;
						d = d%4;
						if(d<0) d+=4;
					}
				}
			}
			time++;
			if(r+dy[d]>=0 && r+dy[d]<N && c+dx[d]>=0 && c+dx[d]<N) { //벽에 닿지않는 조건
				if(map[r+dy[d]][c+dx[d]]==2) {
					map[r+dy[d]][c+dx[d]] = 1; //머리 늘리기
					q.offer(new Integer[] {r+dy[d],c+dx[d]}); //새로 늘어난 위치 좌표 q에 쌓기
					r = r+dy[d]; //머리위치 초기화
					c = c+dx[d];
				}else if(map[r+dy[d]][c+dx[d]] == 0) {
					Integer[] tail = q.poll(); //꼬리 좌표 꺼내기
					map[r+dy[d]][c+dx[d]] = 1; //머리 늘리기
					q.offer(new Integer[] {r+dy[d],c+dx[d]}); //새로 늘어난 위치 좌표 q에 쌓기
					map[tail[0]][tail[1]] = 0; // 꼬리 제거
					r = r+dy[d]; //머리위치 초기화
					c = c+dx[d];
				}else if(map[r+dy[d]][c+dx[d]] == 1) break; //자기 꼬리 닿는경우
				continue;
			}
			break; // 벽에 닿는 경우
		}
		System.out.println(time);
	}
}