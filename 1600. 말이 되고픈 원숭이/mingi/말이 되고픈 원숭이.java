import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dr = {-1,1,0,0}; //원숭이 움직임
    static int[] dc = {0,0,-1,1};
    static int[] kr = {-1,-2,-2,-1,1,2,2,1}; //나이트 움직임
    static int[] kc = {-2,-1,1,2,-2,-1,1,2};
    static int N;
    static int M;
    static int C;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        C = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[1]);
        M = Integer.parseInt(input[0]);
        int[][][] map = new int[N][M][C+1]; //나이트 처럼 움직인 횟수에 따라 맵을 나눈다.
        Queue<int[]> q = new LinkedList<>(); //세개의 좌표를 받는다.

        for(int r=0; r<N; r++){
            String[] row = br.readLine().split(" ");
            for(int c=0; c<M; c++) {
                int element = Integer.parseInt(row[c]);
                for (int h = 0; h <= C; h++) {
                    map[r][c][h] = element;
                }
            }
        }
        q.offer(new int[] {0,0,0});
        map[0][0][0]=1;
        System.out.println(bfs(q,map));

    }
    static int bfs(Queue<int[]> q, int[][][] map){
        int size = q.size();
        while (!q.isEmpty()){
            for (int i=0; i<size; i++){
                int r = q.peek()[0];
                int c = q.peek()[1];
                int m = q.poll()[2];
                if(r==N-1 && c==M-1) return map[r][c][m]-1;

                for(int d=0; d<4; d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];
                    if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
                    if(map[nr][nc][m]==0){
                        map[nr][nc][m] = map[r][c][m]+1;
                        q.offer(new int[] {nr,nc,m});
                    }
                }
                for (int d=0; d<8; d++){
                    int nr = r+kr[d];
                    int nc = c+kc[d];
                    if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
                    if(m<C && map[nr][nc][m+1]==0){
                        map[nr][nc][m+1] = map[r][c][m]+1;
                        q.offer(new int[] {nr,nc,m+1});
                    }
                }
            }
            size = q.size();
        }
        return -1;
    }
}