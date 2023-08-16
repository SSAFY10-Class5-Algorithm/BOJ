import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<int[]> stack1 = new Stack<>();
		Stack<int[]> stack2 = new Stack<>();
		int N = Integer.parseInt(br.readLine());

		for(int i=0; i<N; i++) { //옥상 높이를 스택에 담는다.
			stack1.push(new int[] {Integer.parseInt(br.readLine()),i});
		}
		long sum=0; //밴치마킹이 가능한 옥상 수
		while(!stack1.isEmpty()) {
			int[] building = stack1.pop(); //뒤에 있는 빌딩배열을 꺼내온다. (빌딩높이, 빌딩 인덱스)
			while(!stack2.isEmpty()) { //밴치 마킹 수를 파악한 빌딩들을 담아두는 스택 (밴치마킹 스택이라 부른다.)
				if(building[0]<=stack2.peek()[0]) { //밴치마킹 스택의 top의 빌딩 높이가 현재 빌딩의 높이보다 같거나 높다면
					sum += stack2.peek()[1]-(building[1]+1); //top 빌딩과 현재 빌딩 사이에 몇개의 빌딩이 있는지 계산한다.
					stack2.push(building); //현재 빌딩을 스택에 쌓는다.
					break;
				}else if(building[0]>stack2.peek()[0]) {//밴치마킹 스택의 top의 빌딩 높이가 현재 빌딩의 높이보다 작다면
					stack2.pop(); //밴치마킹 스택의 top을 버려준다.
				}
			}
			if(stack2.isEmpty()) { //밴치마킹 스택이 비어있다면 (경우: 현재 빌딩이 오른쪽에 위치한 모든 빌딩보다 높다, 가장 오른쪽에 있는 빌딩)
				stack2.push(building);
				sum += N-(building[1]+1); //빌딩 끝과 현재 빌딩의 거리를 계산한다.
			}
		}
		System.out.println(sum);
	}
}