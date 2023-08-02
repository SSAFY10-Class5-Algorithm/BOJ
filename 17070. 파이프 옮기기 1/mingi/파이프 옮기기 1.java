import java.util.Scanner;

class Main {
	static int state = 0;
	static long count = 0;

	public static void main(String args[]) {


				Scanner sc = new Scanner(System.in);

				int N = sc.nextInt();
				int[][] map = new int[N][N];

				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						map[r][c] = sc.nextInt();
					}
				}

				fife(0, 1, map, state); // state = 0 -> 시작 파이프가 가로
				System.out.println(count);
			}

			public static void fife(int r, int c, int[][] map, int state) {
				if ((r == map.length - 1) && (c == map.length - 1)) {
					count++;
					return;
				}
				
				if (state == 0) {
					if (c < (map.length - 1) && map[r][c + 1] == 0) fife(r, c + 1, map, 0); //가로이동
					if (r < (map.length - 1) && c < (map.length - 1)) {
						if(map[r][c + 1] == 0 && map[r + 1][c + 1] == 0 && map[r + 1][c] == 0) fife(r + 1, c + 1, map, 2); //대각이동
					}

				}

				if (state == 1) {
					if (r < (map.length - 1) && map[r + 1][c] == 0) fife(r + 1, c, map, 1); //세로이동
					if (r < (map.length - 1) && c < (map.length - 1)) {
						if(map[r][c + 1] == 0 && map[r + 1][c + 1] == 0 && map[r + 1][c] == 0) fife(r + 1, c + 1, map, 2); //대각이동
					}
				}
				
				if (state == 2) {
					if (c < (map.length - 1) && map[r][c + 1] == 0) fife(r, c + 1, map, 0); //가로이동
					if (r < (map.length - 1) && map[r + 1][c] == 0) fife(r + 1, c, map, 1); //세로이동
					if (r < (map.length - 1) && c < (map.length - 1)) {
						if(map[r][c + 1] == 0 && map[r + 1][c + 1] == 0 && map[r + 1][c] == 0) fife(r + 1, c + 1, map, 2); //대각이동
					}
				}
			}
       }