import java.io.*;
import java.util.*;

public class Main {
    static int[][] horseMove = {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
    static int[][] regularMove = {{-1,0},{0,1},{1,0},{0,-1}};
    static int maxHorseMove = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        maxHorseMove = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int columnNum = Integer.parseInt(st.nextToken());
        int rowNum = Integer.parseInt(st.nextToken());

        int[][] map = new int[rowNum][columnNum];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        boolean[][][] visitCheck = new boolean[rowNum][columnNum][maxHorseMove+1];
        for (int i = 0; i < visitCheck.length; i++) {
            for (int j = 0; j < visitCheck[i].length; j++) {
                Arrays.fill(visitCheck[i][j],false);
            }
        }

        System.out.println(BFSSearch(map,visitCheck));
    }
    static int BFSSearch (int[][] map, boolean[][][] visitCheck){
        // start[0] = row, start[1] = col, start[2] = firstCheck, start[3] =
        int[] start = {0,0,1,0};
        int[] endPoint = {map.length-1, map[0].length-1};

        int count = -1;
        boolean firstCheck = false;
        boolean endCheck = false;

        Queue<int[]> que = new LinkedList<>();
        que.add(start);
        visitCheck[start[0]][start[1]][start[3]] = true;

        while (!que.isEmpty()){
            int[] temp = que.poll();
            // Check if Ended
            if(temp[0]==endPoint[0] && temp[1]==endPoint[1]){
                count = temp[2]==1? count+1:count;
                endCheck = true;
                break;
            }

            // Check if it is the first Element
            if(temp[2]==1){
                count+=1;
                firstCheck = true;
            }

            // Each Moves
            // If Monkey move like Monkey
            for (int j = 0; j < regularMove.length; j++) {
                int[] next = new int[4];
                next[0] = temp[0]+regularMove[j][0];
                next[1] = temp[1]+regularMove[j][1];
                next[2] = 0;
                next[3] = temp[3];
                if(xCheck(next[0],next[1],map) && yCheck(next[0],next[1],map) && ableCheck(next[0],next[1],map) && !visitCheck[next[0]][next[1]][next[3]]){
                    if(firstCheck){
                        next[2] = 1;
                        firstCheck = false;
                    }
                    visitCheck[next[0]][next[1]][next[3]] = true;
                    que.add(next);
                }
            }
            // Monkey tries to move like Horse
            for (int i = 0; i < horseMove.length; i++) {
                // Monkey is out of Moves
                if(temp[3]>=maxHorseMove){
                    break;
                }
                // If Monkey can move like Horse
                int[] next = new int[4];
                next[0] = temp[0]+horseMove[i][0];
                next[1] = temp[1]+horseMove[i][1];
                next[2] = 0;
                next[3] = temp[3]+1;
                if(xCheck(next[0],next[1],map) && yCheck(next[0],next[1],map) && ableCheck(next[0],next[1],map) && !visitCheck[next[0]][next[1]][next[3]]){
                    if(firstCheck){
                        next[2] = 1;
                        firstCheck = false;
                    }
                    visitCheck[next[0]][next[1]][next[3]] = true;
                    que.add(next);
                }
            }
        }
        return endCheck? count:-1;
    }
    static boolean xCheck(int x, int y, int[][] map){
        if(x>=0 && x<map.length){
            return true;
        }
        return false;
    }
    static boolean yCheck(int x, int y, int[][] map){
        if(y>=0 && y<map[0].length){
            return true;
        }
        return false;
    }
    static boolean ableCheck(int x, int y, int[][] map){
        if(map[x][y]!=1){
            return true;
        }
        return false;
    }
}
