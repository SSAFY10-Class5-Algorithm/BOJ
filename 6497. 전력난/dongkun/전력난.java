import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int INF = Integer.MAX_VALUE;
	
	static class Route implements Comparable<Route>{
		
		int to;
		int cost;

		public Route() {}
		public Route(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Route o) {
			return this.cost - o.cost;
		}
		@Override
		public String toString() {
			return "Route [to=" + to + ", cost=" + cost + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			
			ArrayList<ArrayList<Route>> routes = new ArrayList<>();
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			if( N == 0 && M == 0) break;
			
			int initCost = 0;
			
			for(int i = 0; i < N;i++)routes.add(new ArrayList<>());
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				routes.get(a).add(new Route(b,cost));
				routes.get(b).add(new Route(a,cost));
				initCost += cost;
			}
			
			PriorityQueue<Route> pq = new PriorityQueue<>();
			boolean[] visited = new boolean[N];
			int[] dp = new int[N];
			int answer = 0;
			Arrays.fill(dp, INF);
			dp[0] = 0;
			
			pq.add(new Route(0,0));
			
			while(!pq.isEmpty()) {
				Route cur = pq.poll();
				if(visited[cur.to])continue;
				visited[cur.to] = true;
				answer+= cur.cost;
				for(Route next : routes.get(cur.to)) {
					pq.add(new Route(next.to,next.cost));
				}
			}
			System.out.println(initCost - answer);
		}
	}
}


