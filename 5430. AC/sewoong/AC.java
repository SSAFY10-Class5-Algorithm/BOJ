import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        // 입력
        // 테스트 케이스 T
        int T = Integer.parseInt(br.readLine());
        for(int test_case=1;test_case<=T;test_case++) {
            // 수행할 함수 p
            String p = br.readLine();
            // 배열에 들어있는 수 n
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            // 배열에 입력할 수를 입력받을 line
            String[] in = br.readLine().split(",");
            // 배열에 들어갈 수가 있다면
            if(n!=0) {
                // 배열에 입력
                arr[0] = Integer.parseInt(in[0].replace("[", "").replace("]", ""));
                arr[n-1] = Integer.parseInt(in[n-1].replace("[", "").replace("]", ""));
                for(int i=1; i<n-1; i++) {
                    arr[i] = Integer.parseInt(in[i]);
                }
            }

            // 문제풀이
            // start와 end 포인터를 움직이는 아이디어
            // D가 나오면 start++
            // R이 나오면 isR을 바꿔줌
            int start = 0;
            int end = n-1;
            boolean isR = false;
            boolean isBreak = false;
            for(int idx=0;idx<p.length();idx++){
                char cmd = p.charAt(idx);
                if(cmd=='D'){
                    if(start>end) {
                        isBreak = true;
                        break;
                    }
                    if(!isR) start++;
                    else end--;
                }
                if(cmd=='R') isR = isR? false: true;
            }

            // 명령을 모두 수행한 이후
            // 명령 도중 break 되었다면 error 출력
            // start부터 end까지 순회하며 배열 출력
            // error
            if(isBreak){
                sb.append("error");
            }
            // 배열이 비워져있는 경우
            else if(start>end){
                sb.append("[]");
            }
            // 배열에 숫자가 있는 경우
            else{
                sb.append("[");
                if(!isR){
                    for(int i=start;i<end;i++){
                        sb.append(arr[i]).append(",");
                    }
                    sb.append(arr[end]);
                }
                else{
                    for(int i=end;i>start;i--){
                        sb.append(arr[i]).append(",");
                    }
                    sb.append(arr[start]);
                }
                sb.append("]");
            }
            sb.append("\n");
        }
        // 출력
        System.out.print(sb);
    }
}