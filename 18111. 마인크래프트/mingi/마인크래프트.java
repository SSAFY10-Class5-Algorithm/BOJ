import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int r=0; r<N; r++){
            StringTokenizer row = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++){
                int value = Integer.parseInt(row.nextToken());
                map[r][c] = value;
                if(max < value) max = value;
                if(value < min) min = value;
            }
        }
        int minTime = Integer.MAX_VALUE;
        int heigth = 0;
        // 블록 제거 2초, 블록 쌓기 1초
        for(int t=min; t<=max; t++){
            int time = 0; //시간
            int block = B; //가지고 있는 블록
            for(int r=0; r<N; r++){
                for(int c=0; c<M; c++){
                    if(map[r][c] > t){
                        time += 2*(map[r][c] - t);
                        block += map[r][c] - t;
                        continue;
                    }
                    if(map[r][c] < t){
                        time += (t - map[r][c]);
                        block -= (t - map[r][c]);
                    }
                }
            }
            if(block < 0) continue;
            if(minTime >= time){
                minTime=time;
                heigth=t;
            }
        }
        System.out.println(minTime + " " + heigth);
    }
}