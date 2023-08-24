import java.io.*;
import java.util.*;

public class Main {
    static int[][][] sharkDirections;
    static int[][][] map;
    static int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    static List<int[]> sharkCoordinate;
    static int lastingTime;
    static int runTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int mapSize = Integer.parseInt(st.nextToken());
        int sharkNum = Integer.parseInt(st.nextToken());
        lastingTime = Integer.parseInt(st.nextToken());

        map = new int[2][mapSize][mapSize];
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        sharkCoordinate = new ArrayList<>();
        findShark();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < sharkCoordinate.size(); i++) {
            for (int j = 0; j < sharkCoordinate.size(); j++) {
                if(sharkCoordinate.get(j)[0]==i){
                    sharkCoordinate.get(j)[3] = Integer.parseInt(st.nextToken())-1;
                    break;
                }
            }
        }

        sharkDirections = new int[sharkNum][4][4];
        for (int i = 0; i < sharkDirections.length; i++) {
            for (int j = 0; j < sharkDirections[i].length; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < sharkDirections[i][0].length; k++) {
                    sharkDirections[i][j][k] = Integer.parseInt(st.nextToken())-1;
                }
            }
        }

        run();
        runTime = runTime>1000? -1:runTime;
        System.out.println(runTime);
    }
    static void findShark(){
        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map[0][0].length; j++) {
                if(map[0][i][j]>0){
                    int[] temp = {map[0][i][j]-1, i, j,-1};
                    sharkCoordinate.add(temp);
                }
            }
        }
    }
    static void run(){
        int time = 0;
        while (true){
            time+=1;
            if(time>1000) {
                break;
            }
            eachSharkSpray();
            List<int[]> nextsharks = eachSharkMove();
            nextsharks = removeShark(nextsharks);
            sharkCoordinate = nextsharks;
            if (sharkCoordinate.size()==1){
                break;
            }
            scentReduce();
        }
        runTime = time;
    }
    static void scentReduce(){
        for (int i = 0; i < map[1].length; i++) {
            for (int j = 0; j < map[1][i].length; j++) {
                if(map[1][i][j]>0){
                    map[1][i][j]-=1;
                }
                if(map[1][i][j]==0){
                    map[0][i][j]=0;
                }
            }
        }
    }
    static void eachSharkSpray(){
        for (int i = 0; i < sharkCoordinate.size(); i++) {
            map[0][sharkCoordinate.get(i)[1]][sharkCoordinate.get(i)[2]] = sharkCoordinate.get(i)[0];
            map[1][sharkCoordinate.get(i)[1]][sharkCoordinate.get(i)[2]] = lastingTime;
        }
    }
    static List<int[]> eachSharkMove(){
        List<int[]> nextSharks = new ArrayList<>();
        for (int i = 0; i < sharkCoordinate.size(); i++) {
            // Check if there is empty space around current shark
            int[] next = checkClear(i);
            // if there is no Empty space
            if(next[1]==-1){
                next = checkNext(i,sharkCoordinate.get(i)[0]);
            }
            nextSharks.add(next);
        }
        // Remove same coordinated Sharks
        return nextSharks;
    }
    static List<int[]> removeShark(List<int[]> nextSharks){
        List<int[]> savedSharks = new ArrayList<>();
        Comparator<int[]> compare = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        };
        Collections.sort(nextSharks,compare);
        List<Integer>[] sameCoorSharks = new List[nextSharks.size()];
        for (int i = 0; i < sameCoorSharks.length; i++) {
            sameCoorSharks[i] = new ArrayList<>();
        }

        for (int i = 0; i < nextSharks.size(); i++) {
            for (int j = 0; j < nextSharks.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (nextSharks.get(i)[1] == nextSharks.get(j)[1] && nextSharks.get(i)[2] == nextSharks.get(j)[2]) {
                    sameCoorSharks[i].add(j);
                }
            }
        }

        for (int i = 0; i < sameCoorSharks.length; i++) {
            if(sameCoorSharks[i].isEmpty()){
               savedSharks.add(nextSharks.get(i));
            }
            else {
                boolean check = true;
                for (int j = 0; j < savedSharks.size(); j++) {
                    if(savedSharks.get(j)[1] == nextSharks.get(sameCoorSharks[i].get(0))[1] && savedSharks.get(j)[2] == nextSharks.get(sameCoorSharks[i].get(0))[2]){
                        check = false;
                        break;
                    }
                }
                if(check){
                    savedSharks.add(nextSharks.get(i));
                }
            }
        }
        return savedSharks;
    }
    static int[] checkClear(int sharkCoordiIndex){
        int[] temp = {sharkCoordinate.get(sharkCoordiIndex)[0],-1,-1,-1};
        for (int i = 0; i < sharkDirections[sharkCoordinate.get(sharkCoordiIndex)[0]][sharkCoordinate.get(sharkCoordiIndex)[3]].length; i++) {
            int curDir = sharkDirections[sharkCoordinate.get(sharkCoordiIndex)[0]][sharkCoordinate.get(sharkCoordiIndex)[3]][i];
            int newX = sharkCoordinate.get(sharkCoordiIndex)[1]+directions[curDir][0];
            int newY = sharkCoordinate.get(sharkCoordiIndex)[2]+directions[curDir][1];
            if(newX>=0 && newX<map[0].length && newY>=0 && newY<map[0][0].length && map[1][newX][newY]==0){
                temp[1] = newX;
                temp[2] = newY;
                temp[3] = curDir;
                break;
            }
        }
        return temp;
    }
    static int[] checkNext(int sharkCoordiIndex, int toFind){
        int[] temp = {sharkCoordinate.get(sharkCoordiIndex)[0],-1,-1,-1};
        for (int i = 0; i < sharkDirections[sharkCoordinate.get(sharkCoordiIndex)[0]][sharkCoordinate.get(sharkCoordiIndex)[3]].length; i++) {
            int curDir = sharkDirections[sharkCoordinate.get(sharkCoordiIndex)[0]][sharkCoordinate.get(sharkCoordiIndex)[3]][i];
            int newX = sharkCoordinate.get(sharkCoordiIndex)[1]+directions[curDir][0];
            int newY = sharkCoordinate.get(sharkCoordiIndex)[2]+directions[curDir][1];
            if(newX>=0 && newX<map[0].length && newY>=0 && newY<map[0][0].length && map[1][newX][newY]>0 && map[0][newX][newY]==toFind){
                temp[1] = newX;
                temp[2] = newY;
                temp[3] = curDir;
                break;
            }
        }
        return temp;
    }

}
