import java.util.*;
import java.io.*;

public class Main {
	static int arr[][]; 
	static boolean visit[];

	static int N;
	static int result;
	static int min_count = Integer.MAX_VALUE;

	static class Bacon {
		int num;
		int value;

		public Bacon(int num, int value) {
			this.num = num;
			this.value = value;
		}
	} // End of Bacon

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 유저의 수
		int M = Integer.parseInt(st.nextToken()); // 친구 관계의 수

		arr = new int[N + 1][N + 1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			arr[y][x] = 1;
			arr[x][y] = 1;
		}

		for(int i=1; i<=N; i++) {
			visit = new boolean[N + 1];
			BFS(i);
		}

		System.out.println(result);
	} // End of main

	static void BFS(int start) {
		Queue<Bacon> que = new LinkedList<>();
		int count = 0;

		// 자기자신은 true처리를 하고 시작
		visit[start] = true;
		que.offer(new Bacon(start, 0));

		while( !que.isEmpty() ) {
			Bacon bacon = que.poll();
			count += bacon.value;

			for(int i=1; i<=N; i++) {
				int num = arr[bacon.num][i];

				if(num == 1 && visit[i] == false) {
					visit[i] = true;
					que.offer(new Bacon(i, bacon.value + 1));
				}
			}
		}

		if(min_count > count) {
			min_count = count;
			result = start;
		}

	} // End of BFS

} // End of class


//5 5
//1 3
//1 4
//4 5
//4 3
//3 2