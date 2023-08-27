import com.sun.jdi.request.ClassPrepareRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static long fuel = 0;
    static int[][] map;
    static List<int[]> passengers;
    static int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        int peopleNum = Integer.parseInt(st.nextToken());
        fuel = Long.parseLong(st.nextToken());

        map = new int[size][size];
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken())-1;
        int startY = Integer.parseInt(st.nextToken())-1;

        passengers = new ArrayList<>();
        for (int i = 0; i < peopleNum; i++) {
            st = new StringTokenizer(br.readLine());
            int[] passenger = new int[4];
            for (int j = 0; j < 4; j++) {
                passenger[j] = Integer.parseInt(st.nextToken())-1;
            }
            passengers.add(passenger);
        }

        run(startX,startY);
        System.out.println(fuel);
    }
    static void run(int startX, int startY){
        while (true){
            int[][] currentMap = new int[map.length][map[0].length];
            for (int i = 0; i < map.length; i++) {
                currentMap[i] = map[i].clone();
            }

            checkPassengers(currentMap);
            // [0] : index of passegners [1] : distance
            int[] currentClose = closestPassenger(startX,startY,currentMap);
            if(currentClose[0]==-1 || fuel<currentClose[1]){
                fuel = -1;
                break;
            }

            fuel -= currentClose[1];
            int index = currentClose[0];

            int distance = dropOffDistance(index,currentMap);
            if(distance==-1 || fuel<distance){
                fuel = -1;
                break;
            }

            fuel += distance;
            startX = passengers.get(index)[2];
            startY = passengers.get(index)[3];
            passengers.remove(index);
            if(passengers.isEmpty()){
                break;
            }
        }
    }
    static void checkPassengers(int[][] currentMap){
        for (int i = 0; i < passengers.size(); i++) {
            currentMap[passengers.get(i)[0]][passengers.get(i)[1]] = i+5;
        }
    }
    static int findIndex(int startX, int startY){
        for (int i = 0; i < passengers.size(); i++) {
            if(passengers.get(i)[0] == startX && passengers.get(i)[1] == startY){
                return i;
            }
        }
        return -1;
    }
    static int[] closestPassenger(int startX, int startY, int[][] currentMap){
        if(currentMap[startX][startY]>4){
            return new int[]{currentMap[startX][startY]-5,0};
        }

        List<int[]> closestPassengers = new ArrayList<>();
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{startX,startY,0});
        currentMap[startX][startY] = -1;

        int closeIndex = -1;
        int closeDistance = 0;

        label : while (!que.isEmpty()){
            int[] temp = que.poll();

            for (int i = 0; i < directions.length; i++) {
                int[] next = {temp[0]+directions[i][0], temp[1]+directions[i][1], temp[2]+1};
                if(checkBorders(next,currentMap)){
                    // Met Passenger
                    if(currentMap[next[0]][next[1]]>4){
                        closeIndex = currentMap[next[0]][next[1]]-5;
                        closeDistance = next[2];
                        if(closestPassengers.isEmpty() || closestPassengers.get(closestPassengers.size()-1)[1] >= closeDistance){
                            closestPassengers.add(new int[]{closeIndex,closeDistance});
                        }
                        else {
                            break label;
                        }
                    }
                    // Moving on
                    else if (currentMap[next[0]][next[1]]==0) {
                        currentMap[next[0]][next[1]] = -1;
                        que.add(next);
                    }
                }
            }
        }

        int[] selected = {-1,-1};
        if (!closestPassengers.isEmpty()){
            selected = selectPassenger(closestPassengers);
        }
        return selected;
    }

    static int[] selectPassenger(List<int[]> closestPassengers){
        Comparator<int[]> compare = new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if(passengers.get(o1[0])[0]-passengers.get(o2[0])[0] == 0){
                    return passengers.get(o1[0])[1]-passengers.get(o2[0])[1];
                }
                return passengers.get(o1[0])[0]-passengers.get(o2[0])[0];
            }
        };
        Collections.sort(closestPassengers,compare);
        return closestPassengers.get(0);
    }
    static int dropOffDistance(int index, int[][] currentMap){
        Queue<int[]> que = new LinkedList<>();
        int startX = passengers.get(index)[0];
        int startY = passengers.get(index)[1];
        int lastX = passengers.get(index)[2];
        int lastY = passengers.get(index)[3];
        que.add(new int[]{startX,startY,0});
        currentMap[startX][startY] = -5;

        int distance = -1;

        label : while (!que.isEmpty()){
            int[] temp = que.poll();
            if(temp[0]==lastX && temp[1]==lastY) {
                distance = temp[2];
            }
            for (int i = 0; i < directions.length; i++) {
                int[] next = {temp[0]+directions[i][0], temp[1]+directions[i][1], temp[2]+1};
                if(checkBorders(next,currentMap) && currentMap[next[0]][next[1]]!=-5 && map[next[0]][next[1]]!=1){
                        currentMap[next[0]][next[1]] = -5;
                        que.add(next);
                    }
                }
            }
        return distance;
    }

    static boolean checkBorders(int[] next, int[][] currentMap){
        if(next[0]>=0 && next[0]< currentMap.length && next[1]>=0 && next[1]<currentMap[0].length){
            return true;
        }
        return false;
    }
}
