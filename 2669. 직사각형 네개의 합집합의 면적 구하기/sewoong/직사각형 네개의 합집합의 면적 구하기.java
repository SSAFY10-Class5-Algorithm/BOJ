import java.util.*;

class Main{
    public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int[][] arrayInput = new int[4][4];
		
		for(int iter=0; iter<4; iter++) {
			arrayInput[iter][0] = sc.nextInt();
			arrayInput[iter][1] = sc.nextInt();
			arrayInput[iter][2] = sc.nextInt();
			arrayInput[iter][3] = sc.nextInt();
		}
		
		int[][] arrayResult = new int[100][100]; 
		for(int[] row : arrayInput) {
			for(int i=row[0];i<row[2];i++) {
				for(int j=row[1];j<row[3];j++) {
					arrayResult[i][j] = 1;
				}
			}
		}
		int result = 0;
		for(int i=0;i<arrayResult.length;i++) {
			for(int j=0;j<arrayResult[i].length;j++) {
				result += arrayResult[i][j];
			}
		}
		System.out.println(result);
        
	}   
}