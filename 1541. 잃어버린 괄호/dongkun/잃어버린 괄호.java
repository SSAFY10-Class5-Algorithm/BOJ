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
		
		String[] temp = br.readLine().split("");
		ArrayList<String> arr = new ArrayList<>();
		
		String num = "";
		
		int answer = 0;
		int tempNum = 0;
		boolean t = false; 
		
		for(int i = 0; i < temp.length; i++) {
			if(temp[i].equals("+") || temp[i].equals("-")) {
				arr.add(num);
				arr.add(temp[i]);
				
				num = "";
			}else {
				num += temp[i];
			}
		}
		arr.add(num);
		
		answer += Integer.parseInt(arr.get(0));
		
		for(int i = 1; i < arr.size(); i++) {
//			System.out.println("-----------");
//			System.out.println(arr.get(i));
//			System.out.println(answer);
//			System.out.println(tempNum);
//			System.out.println(t);
			if(arr.get(i).equals("+")) {
				if(t) {
					tempNum += Integer.parseInt(arr.get(i+1));
				}else {
					answer += Integer.parseInt(arr.get(i+1));
				}
				
			}else if(arr.get(i).equals("-")) {
				if(t) {
					answer -= tempNum;
					tempNum = Integer.parseInt(arr.get(i+1));
				}else {
					t = true;
					tempNum = Integer.parseInt(arr.get(i+1));
				}
			}
			else {
				continue;
			}
			
		}
		
		System.out.println(answer - tempNum);
	}
}
