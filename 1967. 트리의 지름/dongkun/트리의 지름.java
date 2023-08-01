import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
 
public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static int INF = 1_000_000;
	public static Map<Integer,ArrayList<Node>> nodes = new HashMap<>();
	public static int T;
	
	
	public static class Node implements Comparable<Node>{
		
		int to;
		int distance;
		
		public Node() {
			
		}

		public Node(int to, int distance) {
			super();
			this.to = to;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			
			return this.distance - o.distance;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		T = Integer.parseInt(br.readLine());
		
		if(T == 1) {
			System.out.println(0);
		}else {
			for(int t = 1; t < T; t++) {
				StringTokenizer st =new StringTokenizer(br.readLine());
				
				
				int p1 = Integer.parseInt(st.nextToken());
				int p2 = Integer.parseInt(st.nextToken());
				int distance = Integer.parseInt(st.nextToken());
				
				if(nodes.containsKey(p1)) {
					nodes.get(p1).add(new Node(p2,distance));
				}else {
					ArrayList<Node> temp = new ArrayList<>();
					temp.add(new Node(p2,distance));
					nodes.put(p1,temp);
				}
				if(nodes.containsKey(p2)) {
					nodes.get(p2).add(new Node(p1,distance));
				}else {
					ArrayList<Node> temp = new ArrayList<>();
					temp.add(new Node(p1,distance));
					nodes.put(p2,temp);
				}
				
			}
				int[] first = djikstra(1);
				int max = Arrays.stream(first).reduce(0, (x,v)-> x > v ? x : v);
				int maxIndex = IntStream.range(0, first.length).
		                filter(i -> max == first[i]).
		                findFirst().orElse(-1);
				int[] second = djikstra(maxIndex);
				int answer = Arrays.stream(second).reduce(0, (x,v)-> x > v ? x : v);
				
				System.out.println(answer);
			bw.flush();
			bw.close();
		}
		
	}

	public static int[] djikstra(int start) {
		int[] dp = new int[T+1];
		boolean[] visited= new boolean[T+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		Arrays.fill(dp, INF);
		dp[0] = 0;
		dp[start] = 0;
		pq.add(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(visited[cur.to]) continue;
			visited[cur.to] = true;
			
			for(Node next : nodes.get(cur.to)) {
				if(dp[next.to] > dp[cur.to] + next.distance) {
					dp[next.to] = dp[cur.to] + next.distance;
					pq.add(new Node(next.to,dp[next.to]));
				}
			}
		}
		return dp;
	}
}