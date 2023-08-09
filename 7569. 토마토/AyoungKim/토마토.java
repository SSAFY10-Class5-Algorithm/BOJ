import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] directions = {{1,0,0},{-1,0,0},{0,0,1},{0,0,-1},{0,1,0},{0,-1,0}};
    static int smallestDays = Integer.MAX_VALUE;
    static int tomatoTotal = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][][] tomatos = new int[H][N][M];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                tomatos[i][j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
        }

        Queue<int[]> que = new LinkedList<>();
        for (int i = 0; i < tomatos.length; i++) {
            for (int j = 0; j < tomatos[0].length; j++) {
                for (int k = 0; k < tomatos[0][0].length; k++) {
                    if(tomatos[i][j][k]==0){
                        tomatoTotal+=1;
                    }
                    if(tomatos[i][j][k]==1){
                        int[] temp = {i,j,k,0};
                        if(que.isEmpty()){
                            temp[3] = 1;
                        }
                        que.add(temp);
                    }
                }
            }
        }

        int minDays = Integer.MAX_VALUE;
        int days = -1;
        boolean dayPass = false;
        int tomatoNUms = 0;

        while (!que.isEmpty()){
            Queue<int[]> que1 = new LinkedList<>();
            while (!que.isEmpty()){
                int[] currentTomato = que.poll();
                if(currentTomato[3]==1){
                    days+=1;
                    dayPass = true;
                }
                for (int i = 0; i < directions.length; i++) {
                    int[] next = new int[4];
                    next[0] = currentTomato[0]+directions[i][0];
                    next[1] = currentTomato[1]+directions[i][1];
                    next[2] = currentTomato[2]+directions[i][2];
                    if(check(next,tomatos)){
                        tomatoNUms+=1;
                        tomatos[next[0]][next[1]][next[2]] = 5;
                        if(dayPass){
                            next[3] = 1;
                            dayPass=false;
                        }
                        que1.add(next);
                    }
                }
            }
            que = que1;
        }
        if(tomatoNUms==tomatoTotal){
            minDays = Math.min(days,minDays);
        }
        if(minDays==Integer.MAX_VALUE){
            System.out.println(-1);
        } else if (tomatoTotal==0) {
            System.out.println(0);
        } else {
            System.out.println(minDays);
        }
    }
    static boolean check(int[] next, int[][][] tomatos){
        if(next[0]>=0 && next[0]<tomatos.length
                && next[1]>=0 && next[1]<tomatos[0].length
                && next[2]>=0 && next[2]<tomatos[0][0].length
                && tomatos[next[0]][next[1]][next[2]]==0){
            return true;
        }
        return false;
    }
}
