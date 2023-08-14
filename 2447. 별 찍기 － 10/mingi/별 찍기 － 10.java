import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.parseInt(br.readLine());
		String[] str = {"***","* *","***"};
		int N = 3;
		
		while(N*3 <= input) {
			String space = "";
			for(int i=0; i<str.length; i++) space+=" ";
			
			String[] str2 = new String[N*3];
			for(int i=0; i<3; i++) {
				for(int j=0; j<N; j++) {
					if(i == 1) {
						str2[j + N*i] = str[j]+space+str[j];
						continue;
					}
					str2[j + N*i] = str[j]+str[j]+str[j];
				}
			}
			str = str2;
			N *= 3;
		}
		for(String s : str) System.out.println(s);
	}
}