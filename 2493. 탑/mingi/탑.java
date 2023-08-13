import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<int[]> stack = new Stack<>();


        int N = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split(" ");
        for(int i=1; i<=N; i++){
            int tower = Integer.parseInt(arr[i-1]);

            while (!stack.isEmpty()){
                if(stack.peek()[0] < tower){
                    stack.pop();
                } else if (stack.peek()[0] >= tower) {
                    sb.append(stack.peek()[1]).append(" ");
                    stack.push(new int[] {tower,i});
                    break;
                }
            }

            if(stack.isEmpty()){
                sb.append("0").append(" ");
                stack.push(new int[] {tower,i});
                continue;
            }
        }
        System.out.println(sb.toString());
    }
}