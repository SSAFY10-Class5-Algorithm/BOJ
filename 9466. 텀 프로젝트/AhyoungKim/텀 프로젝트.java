import java.io.*;
import java.util.*;

public class Main{
    static int count = 0;
    static int[] peopleTeams;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= testCase; tc++) {
            int people = Integer.parseInt(br.readLine());

            List<Integer> peopleChoose = new ArrayList<>();
            peopleChoose.add(-2);

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= people; i++) {
                peopleChoose.add(Integer.parseInt(st.nextToken()));
            }

            count = 0;
            peopleTeams = new int[100001];

            for (int i = 1; i < peopleChoose.size(); i++) {
                List<Integer> teamed = new ArrayList<>();
                if(peopleChoose.get(i)>0){
                    recursion(i, peopleChoose,0,i);
                }
            }

            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
    static void recursion (int index, List<Integer> peopleChoose, int countCurrent, int front){
        countCurrent+=1;
        int temp = peopleChoose.get(index);
        peopleChoose.set(index, -front);
        peopleTeams[index] = countCurrent;

        // End Point 0. Pointing Self
        if(temp==index){
            count+=countCurrent-1;
            return;
        }

        // End Point 1. there's nowhere to go
        if(peopleChoose.get(temp)<0 && peopleChoose.get(temp)!=-front){
            count+=countCurrent;
            return;
        }

        // End Point 2. People's choice made a circle : one Team
        if(peopleChoose.get(temp)==-front){
            count+=peopleTeams[temp]-1;
            return;
        }

        // Continue. Can continue making this team
        recursion(temp,peopleChoose,countCurrent, front);
    }
}
