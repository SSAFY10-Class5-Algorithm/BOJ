import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N+1][N+1];

        int K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            arr[i][j] = 1; // 사과 위치
        }

        int L = Integer.parseInt(br.readLine());
        String [][] move = new String[L][2];
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            move[i][0] = st.nextToken();
            move[i][1] = st.nextToken();
        }

        int dir = 0; // 0:오른쪽 1:아래 2:왼쪽 3:위
        int time = 0;
        int r = 1;
        int c = 1;
        int[] snake = {r,c};
        int len = 1;
        boolean check = true;
        int idx = 0; // move 첫 idx
        
        Queue<int[]> q = new LinkedList<>();
        
        q.add(snake);

        label : while (true) {
        	Queue<int[]> temp = new LinkedList<>();
        	
        	time++;
        	
        	if (r>=1 && r<=N && c>=1 && c+1<=N && dir == 0) {
                c++;
            } else if (r>=1 && r+1<=N && c>=1 && c<=N && dir == 1) {
                r++;
            } else if (r>=1 && r<=N && c-1>=1 && c<=N && dir == 2) {
                c--;
            } else if (r-1>=1 && r<=N && c>=1 && c<=N && dir == 3) {
                r--;
            } else {
				check = false;
            	break label;
            }
        	
        	while (!q.isEmpty()) {
				if ( q.peek()[0]==r && q.peek()[1] == c) {
					check = false;
					break label;
				}
				else {
					temp.add(q.poll());
				}
			}
        		
        	 if (arr[r][c] != 1) {
        		 
             	temp.poll();
             } else {
            	 arr[r][c] = 0;
             }
        	 
             temp.add(new int[] {r,c});
			 q = temp;
			
        	if (idx < L && time == Integer.parseInt(move[idx][0])) {
				if (move[idx][1].equals("D")) {
	                dir = (dir+1) % 4;
	            } else {
	                dir = (dir+3) % 4;
	            }
				idx++;
			}
            
        }
        System.out.println(time);


    }
}