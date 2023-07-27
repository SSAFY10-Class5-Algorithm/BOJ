import java.io.*;
import java.util.*;

public class Main {
	static int[] horizonBefore = {0,-1};
	static int[] verticalBefore = {-1,0};
	static int[] diagonalBefore = {-1,-1};
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 현황을 입력받을 내용
		int houseSize = Integer.parseInt(br.readLine());
		long[][] house = new long[houseSize][houseSize];
		for(int i=0; i<house.length; i++) {
			house[i] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		}
		
		// 경우의 수를 counting 할 3차원 배열
		// house의 각 위치에 도달하는 경우 위치마다 기입
		// 0: 가로, 1: 세로, 2: 대각선
		long[][][] cases = new long[houseSize][houseSize][3];
		
		// DP Bottom-up 방식 
		for(int x=0; x<house.length; x++) {
			for(int y=0; y<house[x].length; y++) {
				// 현재 상황이 벽인 경우, 또는 0,0인 경우 아예 제외
				if(house[x][y]==1) {
					cases[x][y][0] = 0;
					cases[x][y][1] = 0;
					cases[x][y][2] = 0;
					continue;
				}
				
				// base case
				// 1. 0,0 좌표
				if(x==0 && y==0) {
					cases[x][y][0] = 0;
					cases[x][y][1] = 0;
					cases[x][y][2] = 0;
					continue;
				}
				// 2. x=0, y=1 좌표
				if(x==0 && y==1) {
					cases[x][y][0] = 1;
					cases[x][y][1] = 0;
					cases[x][y][2] = 0;
					continue;
				}
				
				// 3. y=0 좌표들
				if(y==0) {
					cases[x][y][0] = 0;
					cases[x][y][1] = 0;
					cases[x][y][2] = 0;
					continue;
				}
				
				// real case
				// 1. 가로로 도착 경우 (이전 가로로 끝난 칸에서 오는 경우 + 이전 대각전으로 끝난 칸에서 오는 경우)
				long horizontal = 0;
				if(x+horizonBefore[0]>=0 && x+horizonBefore[0]<cases.length && y+horizonBefore[1]>=0 && y+horizonBefore[1]<cases.length) {
					horizontal = horizontal+cases[x+horizonBefore[0]][y+horizonBefore[1]][0]+cases[x+horizonBefore[0]][y+horizonBefore[1]][2];
				}
				cases[x][y][0] =  horizontal;
				
				// 2. 세로로 도착 경우 (이전 세로로 끝난 칸에서 오는 경우 + 이전 대각전에서 끝난 칸에서 오는 경우)
				long vertical = 0;
				if(x+verticalBefore[0]>=0 && x+verticalBefore[0]<cases.length && y+verticalBefore[1]>=0 && y+verticalBefore[1]<cases.length) {
					vertical = vertical+cases[x+verticalBefore[0]][y+verticalBefore[1]][1] +cases[x+verticalBefore[0]][y+verticalBefore[1]][2];
				}
				cases[x][y][1] = vertical;
				
				// 3. 대각선으로 도착 경우 (이전 가로로 끝난 칸에서 오는 경우 + 이전 세로로 끝난 칸에서 오는 경우+ 이전 대각전으로 끝난 칸에서 오는 경우)
				// 도착 칸의 바로 윗칸, 바로 왼쪽 칸이 비어 있어야 함
				long diagonal =0;
				if( x-1>=0 && house[x-1][y]!=1
					&& y-1>=0 && house[x][y-1]!=1
					&& x+diagonalBefore[0]>=0 && x+diagonalBefore[0]<cases.length 
					&& y+diagonalBefore[1]>=0 && y+diagonalBefore[1]<cases.length) {
					diagonal = diagonal+cases[x+diagonalBefore[0]][y+diagonalBefore[1]][0] + cases[x+diagonalBefore[0]][y+diagonalBefore[1]][1] + cases[x+diagonalBefore[0]][y+diagonalBefore[1]][2];
				}
				cases[x][y][2] = diagonal;
			}
		}
		
//		for(int x=0; x<house.length; x++) {
//			for(int y=0; y<house.length; y++) {
//				System.out.print(Arrays.toString(cases[x][y]));
//				System.out.print(" ");
//			}
//			System.out.println();
//		}
		
		System.out.println(cases[house.length-1][house.length-1][0]+cases[house.length-1][house.length-1][1]+cases[house.length-1][house.length-1][2]);
	}
}

























