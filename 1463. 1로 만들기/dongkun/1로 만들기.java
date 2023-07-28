import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		ArrayList<Integer> dp = new ArrayList<>();
		int num = Integer.parseInt(br.readLine());
		int cur = 4;
		int count2 = 2;
		int count3 = 2;
		dp.add(0);
		dp.add(0);
		dp.add(1);
		dp.add(1);
		
		
		while(num >= cur) {
			ArrayList<Integer> tempArray = new ArrayList<>();			
			
			tempArray.add(1 + dp.get(cur-1));
			if(cur % 2 == 0)tempArray.add(1+dp.get(cur/2));
			if(cur % 3 == 0)tempArray.add(1+dp.get(cur/3));
			if(Math.pow(2, count2) == cur) {
				tempArray.add(count2);
				count2++;
			}
			if(Math.pow(3, count3) == cur) {
				tempArray.add(count3);
				count3++;
			}
			int[] temp = new int[tempArray.size()];
			for(int i = 0; i < tempArray.size(); i++) {
				temp[i] = tempArray.get(i);
			}
//			System.out.println("======");
//			System.out.println(Arrays.stream(temp).min().getAsInt());
			dp.add(Arrays.stream(temp).min().getAsInt());
			cur++;
		}
		
//		for(int i = 0; i < dp.size(); i++) {
//			System.out.println(dp.get(i));
//		}
		System.out.println(dp.get(num));
	}
}


