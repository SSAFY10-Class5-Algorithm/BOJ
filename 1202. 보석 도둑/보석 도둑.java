import java.util.*;
import java.io.*;


public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static StringTokenizer st;
	public static int INF = 100_000_000;
	public static int[] dx = {0,1,0,-1};
	public static int[] dy = {-1,0,1,0};

	
	
	public static class Crystal{
		int mass;
		int value;
		
		public Crystal() {}
		public Crystal(int mass, int value) {
			this.mass = mass;
			this.value = value;
		}
		@Override
		public String toString() {
			return "Crystal [mass=" + mass + ", value=" + value + "]";
		}
	}
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Crystal> list = new ArrayList<>();
		int[] backpack = new int[M];
		
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			list.add(new Crystal(m,v));
		}
		
		Collections.sort(list,(a,b) -> a.mass - b.mass);
		
		for(int i = 0; i < M; i++) {
			int size = Integer.parseInt(br.readLine());
			backpack[i] = size;
		}
		
	    Arrays.sort(backpack);

	    
	    
	    
	    int listIdx = 0;
	    long answer = 0;
	    
	    PriorityQueue<Crystal> pq = new PriorityQueue<>((a,b) -> b.value - a.value); 
	    
	    for(int i = 0 ; i < M; i++) { 
	    	while(listIdx < N && list.get(listIdx).mass <= backpack[i]) {
	    		Crystal cur =list.get(listIdx++);
	    		pq.add(new Crystal(cur.mass,cur.value));
	    	}
	    	if(!pq.isEmpty()) answer+=pq.poll().value;
	    }
	 
	    
	    
		System.out.println(answer);
		
	}
}
