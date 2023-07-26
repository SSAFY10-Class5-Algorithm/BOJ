import java.io.*;
import java.util.*;
 
public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


	public static class Node implements Comparable<Node>{
		int distance;
		int next;
		
		public Node() {
			// TODO Auto-generated constructor stub
		}
		public Node(int next, int distance) {
			this.distance = distance;
			this.next = next;
		}
		@Override
		public int compareTo(Node o) {
			
			return this.distance - o.distance;
		}
		
		
	}
	
	public static ArrayList<ArrayList<Node>> route;
	public static int P;
	public static int N;
	public static int start;
	public static int INF = 1_000_000;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		P = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine());
		
		route = new ArrayList<ArrayList<Node>>();
		
		for(int i = 0 ; i <= P; i++) {
			ArrayList<Node> temp = new ArrayList<>();
			route.add(temp);
		}
		
		for(int t = 0; t < N; t++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine()," ");
			
			int s = Integer.parseInt(st1.nextToken());
			int e = Integer.parseInt(st1.nextToken());
			int d = Integer.parseInt(st1.nextToken());
			
			route.get(s).add(new Node(e,d));
			
		}
		
		int[] answerList = dijkstra(start);
		
		for(int i = 1; i <= P; i++) {
			if(answerList[i] >= INF)
				bw.write("INF\n");
			else
				bw.write(Integer.toString(answerList[i]) + "\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	public static int[] dijkstra(int startPoint) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dp = new int[P+1];
		boolean[] visited = new boolean[P+1];
		
		Arrays.fill(dp, INF);
		dp[startPoint] = 0;
		
		pq.add(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node target = pq.poll();
			
			if(visited[target.next]) continue;
			visited[target.next] = true;
			
			for(Node next : route.get(target.next)) {
				if(dp[target.next] + next.distance < dp[next.next]) {
					dp[next.next] = dp[target.next] + next.distance;
					pq.add(new Node(next.next,dp[next.next]));
				}
			}
			
		}
		
		return dp;
	}
}
