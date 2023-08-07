import java.io.*;
import java.util.*;

public class Main {
    static int enemyNum = 0;
    static int range = 0;
    static int enemyKilled = 0;

    public static void main(String[] args) throws IOException {
        // 1. Get Inputs
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        range = D;
        // int array for the map
        int[][] map = new int[N+1][M];
        for (int i = 0; i < map.length-1; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        // list including where enemies are
        List<int[]> enemies = new ArrayList<>();
        enemyLocations(map,enemies);
        enemyNum = enemies.size();
        // Operate
        selectArchers(map,enemies);
        System.out.println(enemyKilled);
    }
    // Figure out where Enemies are
    static void enemyLocations(int[][] map, List<int[]> enemies){
        for (int i = 0; i < map.length-1; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j]==1){
                    enemies.add(new int[] {i,j});
                }
            }
        }
    }

    // Select where Archers are (total 3 Archers)
    static void selectArchers(int[][] map, List<int[]> enemies){
        for (int i = 0; i < map[0].length; i++) {
            for (int j = i+1; j < map[0].length; j++) {
                for (int k = j+1; k < map[0].length ; k++) {

                    List<int[]> archers = new ArrayList<>();
                    archers.add(new int[]{map.length-1,i});
                    archers.add(new int[]{map.length-1,j});
                    archers.add(new int[]{map.length-1,k});

                    List<int[]> enemyTemp = new ArrayList<>();
                    for (int l = 0; l < enemies.size(); l++) {
                        int[] temp = new int[2];
                        temp[0] = enemies.get(l)[0];
                        temp[1] = enemies.get(l)[1];
                        enemyTemp.add(temp);
                    }
                    Comparator <int[]> comparator = new Comparator<int[]>() {
                        @Override
                        public int compare(int[] o1, int[] o2) {
                            return o1[1]-o2[1];
                        }
                    };
                    Collections.sort(enemyTemp,comparator);

                    start(map,enemyTemp,archers);
                }
            }
        }
    }

    // Count Enemy removed
    static void start(int[][] map, List<int[]> enemies, List<int[]> archers){
        int killed = 0;
        int ended = 0;
        while (true){
            // Archers Attack First
            killed+=attack(enemies,archers);
            if(ended+killed==enemyNum){
                break;
            }
            // Left Enemies get down
            ended+=enemyDescend(map,enemies);
            if(ended+killed==enemyNum){
                break;
            }
        }
        enemyKilled = Math.max(enemyKilled,killed);
    }
    // Archers attack
    static int attack(List<int[]> enemies, List<int[]> archers){
        // Each Archer decides where to attack, remove attacked Enemy
        int fallen = 0;

        List<Integer> temp = new ArrayList<>();
        int[] enemyIndex = archersAim(enemies,archers);
        for (int j = 0; j < enemyIndex.length; j++) {
            if(!temp.contains(enemyIndex[j]) && enemyIndex[j]!=-1){
                temp.add(enemyIndex[j]);
            }
        }
        Collections.sort(temp);
        while (!temp.isEmpty() && !enemies.isEmpty()){
            enemies.remove((int)temp.get(0));
            fallen+=1;
            temp.remove(0);
            for (int k = 0; k < temp.size(); k++) {
                temp.set(k,temp.get(k)-1);
            }
        }
        return fallen;
    }
    // Archers decide where to Attack
    static int[] archersAim(List<int[]> enemies, List<int[]> archers){
        int[] index = new int[3];
        Arrays.fill(index,-1);
        for (int i = 0; i < archers.size(); i++) {
            int distance = Integer.MAX_VALUE;
            for (int j = 0; j < enemies.size(); j++) {
                int currentDistance = Math.abs(archers.get(i)[0]-enemies.get(j)[0])+Math.abs(archers.get(i)[1]-enemies.get(j)[1]);
                if(currentDistance<=range && currentDistance<distance){
                    distance = currentDistance;
                    index[i] = j;
                }
            }
        }
        return index;
    }
    // enemies move
    static int enemyDescend(int[][] map, List<int[]> enemies){
        int ended = 0;
        int index = 0;
        while (index<enemies.size()){
         if(enemies.get(index)[0]+1 == map.length-1){
             ended+=1;
             enemies.remove(index);
         }
         else {
             enemies.get(index)[0] += 1;
             index+=1;
         }
        }
        return ended;
    }
}
