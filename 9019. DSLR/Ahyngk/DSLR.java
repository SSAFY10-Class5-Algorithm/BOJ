import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int MOD = 10000;
    static String[] processes = {"D","S","L","R"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < testCase; tc++) {
            st = new StringTokenizer(br.readLine());
            int register = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            System.out.println(run(register,target));
        }
    }

    static String run(int register, int target){
        boolean[] visited = new boolean[10000];
        visited[register] = true;

        Queue<Integer> que = new LinkedList<>();
        Queue<String> processQue = new LinkedList<>();
        que.add(register);
        processQue.add("");

        String answer = "";
        label : while (!que.isEmpty()){
            Queue<Integer> tempQue = new LinkedList<>();
            Queue<String> processTempQue = new LinkedList<>();
            while (!que.isEmpty()){
                int current = que.poll();
                String process = processQue.poll();
                if(current == target){
                    answer = process;
                    break label;
                }
                for (int i = 0; i < processes.length; i++) {
                    int next = 0;
                    String processTemp = process;
                    switch (i){
                        case 0 :
                            next = processD(current);
                            processTemp+="D";
                            break;
                        case 1 :
                            next = processS(current);
                            processTemp+="S";
                            break;
                        case 2 :
                            next = processL(current);
                            processTemp+="L";
                            break;
                        case 3 :
                            next = processR(current);
                            processTemp+="R";
                            break;
                    }
                    if(!visited[next]){
                        visited[next] = true;
                        tempQue.add(next);
                        processTempQue.add(processTemp);
                    }
                }
            }
            que = tempQue;
            processQue = processTempQue;
        }
        return answer;
    }
    static int processD(int register){
        int next = register * 2 % MOD;
        return next;
    }
    static int processS(int register){
        int next = register == 0 ? 9999 : register-1;
        return next;
    }
    static int processL(int register){
        int one = register/1000;
        register = (int) ((register-Math.pow(10,3)*one)*10+one);
        return register;
    }
    static int processR(int register){
        int one = register%10;
        register = (int) (register/10+one*Math.pow(10,3));
        return register;
    }
}