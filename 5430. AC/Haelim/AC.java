import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            String str = sc.next();

            char[]arr = new char[str.length()];

            for (int i = 0; i < str.length(); i++) {
                arr[i] = str.charAt(i);
            }

            int n = sc.nextInt();
            sc.nextLine();
            
            String temp1 = sc.nextLine();
            String temp2 = temp1.substring(1,temp1.length()-1);
            String[] arr2 = temp2.split(",");
            
            int[] nums = new int[n];
            int index = 0;
            for (int i = 0; i < arr2.length; i++) {
                if(!arr2[i].equals("")){
                    nums[index] = Integer.parseInt(arr2[i]);
                    index++;
                }
            }
            
            // StringTokenizer
//            String s = sc.nextLine();
//            if (n == 0) {
//				System.out.println("error");
//				break;
//			}
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            StringTokenizer st = new StringTokenizer(br.readLine(), "[,]");
//            
            Deque<Integer> deque = new ArrayDeque<>();
            
            for (int i = 0; i < nums.length; i++) {
				deque.addLast(nums[i]);
			}

            boolean reverse = false;
            boolean empty = false;
            
            label : for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 'R') { // R일 때
                    reverse = !reverse;
//                	System.out.println(deque);
//                } else if (arr[i] == 'D') {
                } else { // D일 때
                    if (deque.isEmpty()) {
                    	empty = true;
//                    	System.out.println(deque);
                        System.out.println("error");
                        break label;
                    } else {
                    	if (reverse == false) {
							deque.removeFirst();
						} else {
							deque.removeLast();
						}
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            if (!reverse && !empty) {
				int temp = deque.size();
				for (int i = 0; i < temp; i++) {
					sb.append(deque.pollFirst());
					if (i != temp-1) {
						sb.append(",");
					}
				}
			}else if(!empty && reverse){
				int temp = deque.size();
				for (int i = 0; i < temp; i++) {
					sb.append(deque.pollLast());
					if (i != temp-1) {
						sb.append(",");
					}
				}
			}
            sb.append("]");
            if(!empty)
            	System.out.println(sb);
        }
    }
}
