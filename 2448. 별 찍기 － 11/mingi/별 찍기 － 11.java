import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.parseInt(br.readLine());
		int N = 5; //3일 때 맨 밑 행의 열의 개수 (바닥에 *이 몇개 깔려있는지)
		String[] str = {"  *  "," * * ","*****"}; // input이 3일때 모양
		
		while(N < input) { // ex) input이 24면, 6일때 12일때 그리고 24일때 순차적으로 반복하여 쌓아야 함 
			 //쌓는 방식: 원래 모양(top이라 하자) 아래에 원래 모양 + "공백" + 원래 모양(bottom이라 하자)을 추가로 쌓는다.
			 //top이 bottom의 중앙위에 쌓여지려면 top의 모든 각 행들은 좌우에 공백을 추가 해야한다. (bottom 행과 길이가 같아지게)
			String space = "";
			String[] str2 = new String[2*str.length]; 
			for(int i=0; i<(N+1)/2; i++) space += " "; //top 삼각형 좌우에 공백 추가
			
			for(int i=0; i<2*str.length; i++) {
				if(i<str.length) {
					String st = space+str[i]+space; //top 삼각형 만들기
					str2[i] = st;
				}else {
					str2[i] = str[i-str.length] + " " + str[i-str.length]; //바텀 삼각형 만들기ㅐ
				}
			}
			
			N = 2*N+1; //밑 바닥면의 * 수를 늘려준다. 3->5, 6->11, 12->23...
			str = str2; //str 더큰 피라미드로 초기화 -> 반복
		}
		for(String s : str) System.out.println(s);
	}
}