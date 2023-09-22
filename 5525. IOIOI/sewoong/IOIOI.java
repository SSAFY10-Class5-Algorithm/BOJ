import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        // 입력
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        char[] arr;
        arr = br.readLine().toCharArray();

        // 문제풀이
        // 문제풀이 아이디어 : dp로 풀 수 있다.
        // I가 나왔을 때, IOI 패턴이 반복되고 있는 지를 본다.
        // 반복된다면 카운트+1, 아니면 카운트 0으로 초기화
        // 카운트가 0으로 초기화 되는 시점에서, 직전까지 업데이트 된 값과 N의 차이를 구한다.(해당 값이 N보다 크거나 같은 경우에만)
            // Pn에서 Pm과 매칭되는 패턴의 수 : n-m+1

        // dp 배열 선언
        int[] dp = new int[M];
        dp[0] = 0;
        dp[1] = 0;
        // 결과를 담을 result
        int result = 0;
        for(int i=2;i<M;i++){
            // 해당 인덱스의 char이 I인 경우와 O인 경우
            // I라면, IOI 매칭 검사
            // 매칭되면 직전 카운트 값에서 1 추가, 아니라면 0으로 초기화
            if(arr[i]=='I'){
                if(arr[i-2]=='I'&&arr[i-1]=='O'){
                    dp[i] = dp[i-1]+1;
                }
                else{
                    dp[i] = 0;
                }
            }
            // O라면, OIO 매칭 검사
            // 매칭되면 직전 카운트 값 그대로 복사, 아니라면 0으로 초기화
            else{
                if(arr[i-2]=='O'&&arr[i-1]=='I'){
                    dp[i] = dp[i-1];
                }
                else{
                    dp[i] = 0;
                }
            }

            // 만약 dp[i]가 0으로 초기화 된다면,
            // 직전값 - N + 1을 result에 더하기
            // 직전값이 N보다 크거나 같은 경우에만
            if(dp[i]==0&&dp[i-1]>=N){
                result += dp[i-1]-N+1;
            }

        }

        // dp[M-1]이 0으로 초기화되지 않고 N보다 크거나 같은 경우
        if(dp[M-1]>=N){
            result += dp[M-1]-N+1;
        }

        // 출력
        System.out.print(result);
    }
}
