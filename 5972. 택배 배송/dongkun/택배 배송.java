import java.io.*;
import java.util.*;

public class Main {
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int INF = Integer.MAX_VALUE;

	public static class Node implements Comparable<Node>{
		int to;
		int distance;

		public Node() {		}
		public Node(int to,int distance) {
			this.to = to;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}
	}
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayList<ArrayList<Node>> route = new ArrayList<>();
		
		for(int i = 0 ; i <= N; i ++) {
			route.add(new ArrayList<Node>());
		}
		
		for(int i = 0 ; i < M;i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int distance = Integer.parseInt(st.nextToken());
			
			route.get(a).add(new Node(b,distance));
			route.get(b).add(new Node(a,distance));
		}
		
		int[] dp = new int[N+1];
		boolean[] visited = new boolean[N+1];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		dp[1] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(1,0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.to])continue;
			visited[cur.to] = true;
			
			for(Node next : route.get(cur.to)) {
				if(dp[next.to] > dp[cur.to] + next.distance) {
					dp[next.to] = dp[cur.to] + next.distance;
					pq.add(new Node(next.to,dp[next.to]));
				}
			}
		}
		
		
		System.out.println(dp[N]);
		
	}
	
	
}

