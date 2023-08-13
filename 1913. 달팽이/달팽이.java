import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	public static StringBuilder sb;

	static int[] dr1 = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dc1 = { 0, 1, 0, -1 };
	static int[] dr2 = { 1, 0, -1, 0 }; // 하 우 상 좌
	static int[] dc2 = { 0, 1, 0, -1 };
	static int[][] map;

	public static void main(String[] args) throws IOException {
		sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int find = Integer.parseInt(br.readLine());

		map = new int[N][N];

		int findR = 0, findC = 0;
		
		makeSnail1(N);
//		makeSnail2(N);
		
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map.length; j++) {
				sb.append(map[i][j]).append(" ");
				if(map[i][j] == find) {
					findR = i+1;
					findC = j+1;
				}
			}
			sb.append("\n");
		}
		
		sb.append(findR).append(" ").append(findC);
		System.out.println(sb);

	}

	public static void makeSnail1(int N) { // 안쪽부터 바깥쪽으로 채워 나가기
		int row = N / 2, col = N / 2, d = 0;
		int move = 1;	//이동 가능 횟수
		int count = 0;	//이동 횟수
		int moveChk = 0;	//방향전환 최대2번
		
		for(int i = 1; i<= N*N; i++) {
			map[row][col] = i;
			
			row += dr1[d];
			col += dc1[d];
			count++;
			
			if(count == move) {	//최대 이동 횟수에 도달하면
				d = (d+1)%4;	//방향바꾸기
				count = 0;		//이동 횟수 초기화
				moveChk++;		//방향전환 횟수 +1
			}
			if(moveChk == 2) {	//방향전환을 2번하게되면
				move++;			//최대 이동 횟수 추가
				moveChk = 0;	//방향전환 횟수 초기화
			}
		}
		

	}
	

	public static void makeSnail2(int N) { // 바깥쪽부터 안쪽으로 채워 나가기
		int row = 0, col = 0, d = 0;
		int num = N * N;

		while (num > 0) {

			map[row][col] = num--;
			int newDir = setD(d, N, row, col);
			if(newDir != d) {	//방향이 바뀌면 바뀐 방향으로 증가하게끔
				row += dr2[newDir];
				col += dc2[newDir];
				d = newDir;
			}else {
				row += dr2[d];
				col += dc2[d];
			}
			
		}
	}

	public static int setD(int d, int N, int row, int col) {
		if (row + dr2[d] >= N || col + dc2[d] >= N || row + dr2[d] < 0 || col + dc2[d] < 0) {
			d = (d + 1) % 4;
		} else if (map[row + dr2[d]][col + dc2[d]] != 0) {
			d = (d + 1) % 4;
		}

		return d;
	}

}