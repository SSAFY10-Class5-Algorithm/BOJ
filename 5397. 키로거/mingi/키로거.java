import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			String[] keyLog = br.readLine().split("");
			Stack<String> cursorL = new Stack<>(); //문자열을 담아두는 곳
			Stack<String> cursorR = new Stack<>(); //커서의 위치가 달라짐에 따라 뒤에오는 문자들을 임시로 담아두는 곳
			
			for(String key : keyLog) {
				if(key.equals("<")) {
					if(!cursorL.isEmpty()) cursorR.push(cursorL.pop());
				}else if(key.equals(">")) {
					if(!cursorR.isEmpty()) cursorL.push(cursorR.pop());
				}else if(key.equals("-")){
					if(!cursorL.isEmpty()) cursorL.pop();
				}else {
					cursorL.push(key);
				}
			}
			while(!cursorR.isEmpty()) cursorL.push(cursorR.pop()); //cursorR에 나머지 문자 옮겨주기 
			for(String s : cursorL) sb.append(s);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}