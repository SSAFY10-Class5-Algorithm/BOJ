import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int totalCount = 0;
    static int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[][] students  = new String[5][5];
        for (int i = 0; i < students.length; i++) {
            students[i] = br.readLine().split("");
        }

        List<int[]> team = new ArrayList<>();
        recursion(0,0,0,0,team,students);

        System.out.println(totalCount);
    }

    static void recursion (int x, int y, int sTeam, int yTeam, List<int[]> team, String[][] students){

        // End Point.1
        if(team.size()==7){
            if(sTeam>=4 && sideCheck(team)){
                totalCount++;
            }
            return;
        }

        // Stop Point.1
        if(x==students.length || yTeam>3){
            return;
        }

        // Select Team
        int nextSTeam = sTeam;
        int nextYTeam = yTeam;
        if(students[x][y].equals("Y")){
            nextYTeam+=1;
        }
        else {
            nextSTeam+=1;
        }

        int nextX = x;
        int nextY = y+1;
        if(nextY == students.length){
            nextX+=1;
            nextY=0;
        }

        // Case.1 Can include this student
        List<int[]> tempTeam = new ArrayList<>();
        for (int i = 0; i < team.size(); i++) {
            tempTeam.add(team.get(i).clone());
        }
        tempTeam.add(new int[] {x,y});
        recursion(nextX,nextY,nextSTeam,nextYTeam,tempTeam,students);

        // Case.2 Not include this student
        recursion(nextX,nextY,sTeam,yTeam,team,students);
    }
    static boolean sideCheck(List<int[]> team){
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < team.size(); i++) {
            for (int j = 0; j < team.size(); j++) {
                if(i==j){
                    continue;
                }
                if(Math.abs(team.get(j)[0]-team.get(i)[0])+Math.abs(team.get(j)[1]-team.get(i)[1])==1){
                    graph.get(i).add(j);
                }
            }
        }

        return BFSSearch(graph);
    }

    static boolean BFSSearch(List<List<Integer>> graph){
        int total = graph.size();
        int currentCount = 1;
        boolean[] check = new boolean[graph.size()];
        check[0] = true;

        Queue<Integer> que = new LinkedList<>();
        que.add(0);

        while (!que.isEmpty()){
            int temp = que.poll();

            for (int i = 0; i < graph.get(temp).size(); i++) {
                if(!check[graph.get(temp).get(i)]){
                    currentCount+=1;
                    que.add(graph.get(temp).get(i));
                    check[graph.get(temp).get(i)] = true;
                }
            }
        }

        if(currentCount==total){
            return true;
        }
        else {
            return false;
        }
    }
}
