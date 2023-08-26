import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dr = {-1,0,0,1}; //상 좌 하 우
    static int[] dc = {0,-1,1,0};
    static int N;
    static  int level = 2;
    static  int ex;
    static int time =0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        boolean[][] visit = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();

        for(int r=0; r<N; r++){
            String[] row = br.readLine().split(" ");
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(row[c]);
                if(map[r][c]==9){
                    map[r][c]=0;
                    q.offer(new int[] {r,c});
                    visit[r][c] = true;
                }
            }
        }
        while (true){
            bfs(q,map,visit);
            if (q.isEmpty()) break; //bfs이 후 q가 비워져 있다면 종료 (더 이상 찾을 물고기가 없다.)
            visit = new boolean[N][N]; //방문배열 초기화
            visit[q.peek()[0]][q.peek()[1]] = true; //먹었던 물고기 위치(재시작 위치) 방문처리
        }
        System.out.println(time);
    }

    static void bfs(Queue<int[]> q, int[][] map, boolean[][] visit){
        int size = q.size();
        int t = 0;
        while(!q.isEmpty()){
            t++;
            int fishR = Integer.MAX_VALUE; //가장 가까운 (+ 가장 위, 가장 왼쪽)에 있는 물고기 좌표를 담기 위해서
            int fishC = Integer.MAX_VALUE;
            boolean findFish = false; //물고기를 발견했을 때만 실행 시킬 구문을 위해 만듦
            for(int i=0; i<size; i++){
                int r = q.peek()[0];
                int c = q.poll()[1];
                for(int d=0; d<4; d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];
                    if(nr<0 || nr>=N || nc<0 || nc>=N || visit[nr][nc] || map[nr][nc]>level) continue;
                    if(map[nr][nc]==0 || map[nr][nc]==level){
                        q.offer(new int[] {nr,nc});
                        visit[nr][nc] = true;
                    } else if (map[nr][nc] < level) { //가장 1.위쪽 2.왼쪽인 물고기 좌표를 선별함 (같은 거리로 이동가능한 모든 좌표에 대해)
                        visit[nr][nc] = true;
                        findFish = true; 
                       if(nr<fishR){
                           fishR = nr;
                           fishC = nc;
                       } else if (nr==fishR && nc<fishC) {
                           fishR = nr;
                           fishC = nc;
                       }
                    }
                }
            }
            if(findFish){ //물고기를 찾았다면 실행
                map[fishR][fishC] = 0; //몰고기를 먹었기 때문에 사라짐
                ex++; //경험치 증가
                if(level == ex){//레벨조정
                    level++;
                    ex=0;
                }
                time += t; //물고기를 찾기까지 걸린시간 총시간에 누적
                q.clear(); //비워주고
                q.offer(new int[] {fishR,fishC}); //먹은 물고기 위치에서 다시 bfs를 하기위해 해당 좌표만 offer 후 종료
                return;
            }//만약 더이상 찾을 물고기가 없다면 while(!q.empty())라 비워진 큐를 만들고 종료 할 것임
            size = q.size();
        }
    }
}