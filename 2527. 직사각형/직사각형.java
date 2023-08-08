import java.util.Scanner;


class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int[] arrInput = new int[4*8];
		for(int i=0;i<4*8;i++) {
			arrInput[i] = sc.nextInt();
		}
		for(int test_case=0;test_case<4;test_case++) {
		
			int[] arr1 = new int[4];
			int[] arr2 = new int[4];
			
			for(int i=0;i<4;i++) {
				arr1[i] = arrInput[test_case*8+i];
			}
			for(int i=4;i<8;i++) {
				arr2[i-4] = arrInput[test_case*8+i];
			}
			
			// x좌표, y좌표를 움직여보면서 직사각형의 위치 관계를 확인할 수 있다.
			// x좌표를 기준으로 시작
						
			// Case 1 : arr1 x좌표가 arr2 x좌표의 range에 들어가는 경우 or arr2 x좌표가 arr1 x좌표의 range에 들어가는 경우
						
			if((arr1[0]>arr2[0] && arr1[0]<arr2[2]) || (arr1[2]>arr2[0] && arr1[2]<arr2[2]) || 
					(arr2[0]>arr1[0] && arr2[0]<arr1[2]) || (arr2[2]>arr1[0] && arr2[2]<arr1[2])) {
				// arr1 y좌표가 arr2 y좌표의 range에 들어가는 경우 or arr2 y좌표가 arr1 y좌표의 range에 들어가는 경우
				if((arr1[1]>arr2[1] && arr1[1]<arr2[3]) || (arr1[3]>arr2[1] && arr1[3]<arr2[3]) || 
						(arr2[1]>arr1[1] && arr2[1]<arr1[3]) || (arr2[3]>arr1[1] && arr2[3]<arr1[3])) {
					// 직사각형
					System.out.println("a");
				}
				// arr1 y좌표가 arr2 y좌표와 둘 다 일치하는 경우
				else if(arr1[1]==arr2[1] && arr1[3]==arr2[3]) {
					// 직사각형
					System.out.println("a");
				}
				// arr1 y좌표가 arr2 y좌표와 한 개만 일치하는 경우
				else if(arr1[1]==arr2[1] || arr1[1]==arr2[3] || arr1[3]==arr2[1] || arr1[3]==arr2[3]) {
					// 선분
					System.out.println("b");
				}
				// arr1 y좌표와 arr2 y좌표가 겹치지 않는다면
				else {
					// 공통부분이 없음
					System.out.println("d");
				}
			
			}
			// Case 2: arr1과 arr2 x좌표가 둘 다 일치하는 경우
			else if(arr1[0]==arr2[0] && arr1[2]==arr2[2]) {
				// arr1 y좌표가 arr2 y좌표의 range에 들어가는 경우 or arr2 y좌표가 arr1 y좌표의 range에 들어가는 경우
				if((arr1[1]>arr2[1] && arr1[1]<arr2[3]) || (arr1[3]>arr2[1] && arr1[3]<arr2[3]) || 
						(arr2[1]>arr1[1] && arr2[1]<arr1[3]) || (arr2[3]>arr1[1] && arr2[3]<arr1[3])) {
					// 직사각형
					System.out.println("a");
				}
				// arr1 y좌표가 arr2 y좌표와 둘 다 일치하는 경우
				else if(arr1[1]==arr2[1] && arr1[3]==arr2[3]) {
					// 직사각형
					System.out.println("a");
				}
				// arr1 y좌표가 arr2 y좌표와 한 개만 일치하는 경우
				else if(arr1[1]==arr2[1] || arr1[1]==arr2[3] || arr1[3]==arr2[1] || arr1[3]==arr2[3]) {
					// 직사각형
					System.out.println("b");
				}
				// arr1 y좌표와 arr2 y좌표가 겹치지 않는다면
				else {
					// 공통부분이 없음
					System.out.println("d");
				}
			}
			// Case 3 : arr1 arr2 x좌표가 하나만 일치하는 경우
			// 선분 아니면 점
			else if(arr1[0]==arr2[0] || arr1[0]==arr2[2] || arr1[2]==arr2[0] || arr1[2]==arr2[2]) {
				// arr1 y좌표가 arr2 y좌표의 range에 들어가는 경우 or arr2 y좌표가 arr1 y좌표의 range에 들어가는 경우
				if((arr1[1]>arr2[1] && arr1[1]<arr2[3]) || (arr1[3]>arr2[1] && arr1[3]<arr2[3]) 
						|| (arr2[1]>arr1[1] && arr2[1]<arr1[3]) || (arr2[3]>arr1[1] && arr2[3]<arr1[3])) {
					// 선분
					System.out.println("b");
				}
				// arr1 y좌표가 arr2와 둘 다 일치하는 경우
				else if(arr1[1]==arr2[1] && arr1[3]==arr2[3]) {
					// 선분
					System.out.println("b");
				}
				// arr1 y좌표가 arr2와 하나만 일치하는 경우
				else if(arr1[1]==arr2[1] || arr1[1]==arr2[3] || arr1[3]==arr2[1] || arr1[3]==arr2[3]) {
					// 점
					System.out.println("c");
				}
				else {
					// 공통부분이 없음
					System.out.println("d");
				}
			}
			// Case 4: arr1 x좌표가 arr2 x좌표와 겹치지 않는 경우
			else {
				// 공통부분이 없음
				System.out.println("d");
			}
			
		}
		
			
			
	}

}
