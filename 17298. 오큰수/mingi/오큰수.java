import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<int[]> nums = new Stack<>();
        Stack<int[]> NGE = new Stack<>();

        int N = Integer.parseInt(br.readLine());
        int[] answer = new int[N];
        String[] input = br.readLine().split(" ");

        for(int i=0; i<N; i++) nums.push(new int[] {Integer.parseInt(input[i]), i}); //스택에 input값과 값의 index를 같이 스택에 담는다.

        while (!nums.isEmpty()){ //위에 담아둔 스택이 소진될 때까지 시행한다. 꺼낼때마다 오큰수를 판단 할 것임
            int[] num = nums.pop(); // 스택 꺼내기
            while (!NGE.isEmpty()){ // 오큰수를 담는 스택이 비어있지 않다면 실행
                
                if(num[0]<NGE.peek()[0]){//오큰수 스택에 가장 앞의 수가 나보다 크다면 
                    answer[num[1]] = NGE.peek()[0]; //그 수가 나의 오큰수이다.
                    NGE.push(num); //현재 값을 추가로 오큰수 스택에 쌓는다.
                    break;
                }else if(num[0]>=NGE.peek()[0]){ //오큰수 스택의 앞의값이 나보다 작다면
                    NGE.pop(); //그 값을 버린다. 이과정을 나보다 큰수가 만나거나 오큰수 스택이 비어질 때까지 반복한다. <핵심>
                }
            }
            if(NGE.isEmpty()){ //오큰수 스택이 비어있다면 
                NGE.push(num); // 자기 자신을 넣어준다.
                answer[num[1]] = -1; // 비어있다는건 현재값의 오큰수가 없다는것 (-1)
            }
        }
        for(int n : answer) sb.append(n).append(" ");
        System.out.println(sb.toString());
    }
}