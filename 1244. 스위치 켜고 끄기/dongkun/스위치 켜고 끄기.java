import java.io.*;
import java.util.*;
 
public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());

		int[] switchs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		int count = Integer.parseInt(br.readLine());;
		
		for(int i = 0; i < count; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int gene = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			if(gene == 1) {
				for(int x = 1; x * num <= N; x++) {
					switchs[x*num - 1] = switchs[x*num - 1] == 1 ? 0 : 1;
				}
			}else {
				for(int x = 1; x <= N ; x++) {
					if(num - 1 - x < 0 || num - 1 + x >= N)break;
					
					if(switchs[num - 1 - x] == switchs[num - 1 + x]) {
						switchs[num - 1 - x] = switchs[num - 1 - x] == 1 ? 0 : 1;
						switchs[num - 1 + x] = switchs[num - 1 + x] == 1 ? 0 : 1;
					}else{
						break;
					}
				}
				switchs[num - 1] = switchs[num - 1] == 1 ? 0 : 1;
			}
			
		}
		
		for(int x = 0; x < N; x++) {
			if(x % 20 != 0) bw.write(" ");
			bw.write(Integer.toString(switchs[x]));
			if(x % 20 == 19 && x != N -1)bw.write("\n");
		}
		
		
		bw.flush();
		bw.close();
	}
}

