import java.util.*;
import java.io.*;


public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	public static StringBuilder sb = new StringBuilder();
	public static int INF = 100_000_000;
	public static int[] dx = {0,1,0,-1};
	public static int[] dy = {-1,0,1,0};
	
	public static Map<Integer,Problem> memo = new HashMap<>();
	
	public static class Problem implements Comparable<Problem>{
		int no;
		ArrayList<Integer> pre = new ArrayList<>();
		int need = 0;
		
		public Problem() {
			
		}
		
		public Problem(int no) {
			this.no = no;
		}
		
		public void addPre(int no) {
			pre.add(no);
		}
		public void removePre(int no) {
			pre.remove(pre.indexOf(no));
		}
		
		public void addNeed() {
			need++;
		}
		public void removeNeed() {
			need--;
		}
		
		@Override
		public int compareTo(Problem o) {
			// TODO Auto-generated method stub
			return this.no - o.no;
		}
		@Override
		public String toString() {
			return "[no : "+Integer.toString(this.no) + ", priority : " + this.pre.toString() +"]";
		}
	}

	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) {
			memo.put(i, new Problem(i));
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int f = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			memo.get(f).addPre(n);
			memo.get(n).addNeed();
		}
		
		
		PriorityQueue<Problem> qu = new PriorityQueue<>();

		for(int i = 1 ; i <= N; i++) {
			Problem temp = memo.get(i);
			if(temp.need == 0) {
				qu.add(temp);
			}
		}
		

		while(!qu.isEmpty()) {
			Problem cur = qu.poll();
			sb.append(cur.no + " ");
			for(int next : cur.pre) {
				memo.get(next).removeNeed();
				if(memo.get(next).need == 0) {
					qu.add(memo.get(next));
				}
			}

		}
		System.out.println(sb.toString().trim());
	}
	
	
}	
