import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Get inputs to Array
        Scanner sc = new Scanner(System.in);
        int buildingNumber = sc.nextInt();
        Long[] buildingHeight = new Long[buildingNumber];
        for (int i = 0; i < buildingHeight.length; i++) {
            buildingHeight[i] = sc.nextLong();
        }

        // Stacks
        Queue<Long> currentStreak = new LinkedList<>();
        Stack<long[]> bigBuildings = new Stack<>();

        long sum = 0;

        for (int i = buildingHeight.length-1; i >=0 ; i--) {
            // Streak continues
            if(i!=0 && buildingHeight[i] < buildingHeight[i-1]){
                currentStreak.offer(buildingHeight[i]);
                continue;
            }
            //
            if(i==0 && buildingHeight[i] < buildingHeight[i+1]){
                break;
            }

            // if met Smaller building
            long[] current = new long[2];
            current[0] = buildingHeight[i];
            current[1] = currentStreak.size();
            currentStreak.offer(buildingHeight[i]);

            long tempCount = 0;
            while (!currentStreak.isEmpty()){
                long currentTemp = currentStreak.poll();

                while (!bigBuildings.isEmpty()){
                    if(bigBuildings.peek()[0] < currentTemp){
                        tempCount += bigBuildings.pop()[1];
                    }
                    else {
                        break;
                    }
                }
                sum+=tempCount;
                tempCount+=1;
            }
            current[1] = tempCount;
            bigBuildings.push(current);
        }
        System.out.println(sum);
    }
}
