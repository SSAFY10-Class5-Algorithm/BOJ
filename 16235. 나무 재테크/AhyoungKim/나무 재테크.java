import java.io.*;
import java.util.*;

public class Main {
    static int[][] directions = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int mapSize = Integer.parseInt(st.nextToken());
        int treesTotal = Integer.parseInt(st.nextToken());
        int yearTotal = Integer.parseInt(st.nextToken());

        // Get Inputs
        List<Integer>[][] map = new ArrayList[mapSize][mapSize];
        int[][] currentFood = new int[mapSize][mapSize];
        for (int i = 0; i < currentFood.length; i++) {
            Arrays.fill(currentFood[i],5);
        }
        int[][] nutrition = new int[mapSize][mapSize];
        for (int i = 0; i < nutrition.length; i++) {
            nutrition[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 0; i < treesTotal; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int z = Integer.parseInt(st.nextToken());
            makeNewList(map,x,y);
            map[x][y].add(z);
        }

        for (int i = 0; i < yearTotal; i++) {
            springAndSummer(map, currentFood);
            fall(map);
            winter(currentFood,nutrition);

        }

        System.out.println(treeNum(map));
    }
    static void springAndSummer(List<Integer>[][] map, int[][] currentFood){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j]==null){
                    makeNewList(map,i,j);
                    continue;
                }
                // Copy Trees and sort
                Collections.sort(map[i][j]);
                // Map Dead trees
                int deadSum = 0;

                // Trees get fed
                for (int k = 0; k < map[i][j].size(); k++) {
                    if(currentFood[i][j]<map[i][j].get(k)){
                        deadSum+=map[i][j].get(k)/2;
                        map[i][j].remove(k);
                        k--;
                    }
                    else {
                        currentFood[i][j]-= map[i][j].get(k);
                        map[i][j].set(k,map[i][j].get(k)+1);
                    }
                }
                currentFood[i][j]+=deadSum;
            }
        }
    }
    static void fall(List<Integer>[][] map){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                for (int k = 0; k < map[i][j].size(); k++) {
                    if(map[i][j].get(k)%5==0){
                        for (int l = 0; l < directions.length; l++) {
                            int nextX = i+directions[l][0];
                            int nextY = j+directions[l][1];
                            if(nextX>=0 && nextX< map.length && nextY>=0 && nextY< map.length){
                                map[nextX][nextY].add(1);
                            }
                        }
                    }
                }
            }
        }
    }
    static void winter(int[][] currentFood, int[][] nutrition){
        for (int i = 0; i < currentFood.length; i++) {
            for (int j = 0; j < currentFood[0].length; j++) {
                currentFood[i][j]+=nutrition[i][j];
            }
        }
    }

    static int treeNum(List<Integer>[][] map){
        int answer = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                answer+=map[i][j].size();
            }
        }
        return answer;
    }
    static void makeNewList(List<Integer>[][] map, int x, int y){
        if(map[x][y]==null){
            map[x][y] = new ArrayList<>();
        }
    }
}
