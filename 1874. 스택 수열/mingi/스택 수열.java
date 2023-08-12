import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> q = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());
        for(int i=1; i<=N; i++) q.offer(i);

        while (!(q.isEmpty()&&stack.isEmpty())){
            int num = Integer.parseInt(br.readLine());
            if(stack.isEmpty()){
                while(!q.isEmpty() && q.peek()<=num){
                    stack.push(q.poll());
                    sb.append("+").append("\n");
                }
                stack.pop();
                sb.append("-").append("\n");
                continue;
            }
            if(stack.peek() < num){
                while(!q.isEmpty() && q.peek()<=num){
                    stack.push(q.poll());
                    sb.append("+").append("\n");
                }
                stack.pop();
                sb.append("-").append("\n");
            } else if (stack.peek() > num) {
                System.out.println("NO");
                return;
            } else if (stack.peek() == num) {
                stack.pop();
                sb.append("-").append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}