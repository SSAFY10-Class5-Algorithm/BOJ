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
			
			// x��ǥ, y��ǥ�� ���������鼭 ���簢���� ��ġ ���踦 Ȯ���� �� �ִ�.
			// x��ǥ�� �������� ����
			
			// Case 1 : arr1 x��ǥ�� arr2 x��ǥ�� range�� ���� ��� or arr2 x��ǥ�� arr1 x��ǥ�� range�� ���� ���
			if((arr1[0]>arr2[0] && arr1[0]<arr2[2]) || (arr1[2]>arr2[0] && arr1[2]<arr2[2]) || 
					(arr2[0]>arr1[0] && arr2[0]<arr1[2]) || (arr2[2]>arr1[0] && arr2[2]<arr1[2])) {
				// arr1 y��ǥ�� arr2 y��ǥ�� range�� ���� ��� or arr2 y��ǥ�� arr1 y��ǥ�� range�� ���� ���
				if((arr1[1]>arr2[1] && arr1[1]<arr2[3]) || (arr1[3]>arr2[1] && arr1[3]<arr2[3]) || 
						(arr2[1]>arr1[1] && arr2[1]<arr1[3]) || (arr2[3]>arr1[1] && arr2[3]<arr1[3])) {
					// ���簢��
					System.out.println("a");
				}
				// arr1 y��ǥ�� arr2 y��ǥ�� �� �� ��ġ�ϴ� ���
				else if(arr1[1]==arr2[1] && arr1[3]==arr2[3]) {
					// ���簢��
					System.out.println("a");
				}
				// arr1 y��ǥ�� arr2 y��ǥ�� �� ���� ��ġ�ϴ� ���
				else if(arr1[1]==arr2[1] || arr1[1]==arr2[3] || arr1[3]==arr2[1] || arr1[3]==arr2[3]) {
					// ����
					System.out.println("b");
				}
				// arr1 y��ǥ�� arr2 y��ǥ�� ��ġ�� �ʴ´ٸ�
				else {
					// ����κ��� ����
					System.out.println("d");
				}
			
			}
			// Case 2: arr1�� arr2 x��ǥ�� �� �� ��ġ�ϴ� ���
			else if(arr1[0]==arr2[0] && arr1[2]==arr2[2]) {
				// arr1 y��ǥ�� arr2 y��ǥ�� range�� ���� ��� or arr2 y��ǥ�� arr1 y��ǥ�� range�� ���� ���
				if((arr1[1]>arr2[1] && arr1[1]<arr2[3]) || (arr1[3]>arr2[1] && arr1[3]<arr2[3]) || 
						(arr2[1]>arr1[1] && arr2[1]<arr1[3]) || (arr2[3]>arr1[1] && arr2[3]<arr1[3])) {
					// ���簢��
					System.out.println("a");
				}
				// arr1 y��ǥ�� arr2 y��ǥ�� �� �� ��ġ�ϴ� ���
				else if(arr1[1]==arr2[1] && arr1[3]==arr2[3]) {
					// ���簢��
					System.out.println("a");
				}
				// arr1 y��ǥ�� arr2 y��ǥ�� �� ���� ��ġ�ϴ� ���
				else if(arr1[1]==arr2[1] || arr1[1]==arr2[3] || arr1[3]==arr2[1] || arr1[3]==arr2[3]) {
					// ����
					System.out.println("b");
				}
				// arr1 y��ǥ�� arr2 y��ǥ�� ��ġ�� �ʴ´ٸ�
				else {
					// ����κ��� ����
					System.out.println("d");
				}
			// Case 3 : arr1 arr2 x��ǥ�� �ϳ��� ��ġ�ϴ� ���
			// ���� �ƴϸ� ��
			}
			else if(arr1[0]==arr2[0] || arr1[0]==arr2[2] || arr1[2]==arr2[0] || arr1[2]==arr2[2]) {
				// arr1 y��ǥ�� arr2 y��ǥ�� range�� ���� ��� or arr2 y��ǥ�� arr1 y��ǥ�� range�� ���� ���
				if((arr1[1]>arr2[1] && arr1[1]<arr2[3]) || (arr1[3]>arr2[1] && arr1[3]<arr2[3]) 
						|| (arr2[1]>arr1[1] && arr2[1]<arr1[3]) || (arr2[3]>arr1[1] && arr2[3]<arr1[3])) {
					// ����
					System.out.println("b");
				}
				// arr1 y��ǥ�� arr2�� �� �� ��ġ�ϴ� ���
				else if(arr1[1]==arr2[1] && arr1[3]==arr2[3]) {
					// ����
					System.out.println("b");
				}
				// arr1 y��ǥ�� arr2�� �ϳ��� ��ġ�ϴ� ���
				else if(arr1[1]==arr2[1] || arr1[1]==arr2[3] || arr1[3]==arr2[1] || arr1[3]==arr2[3]) {
					// ��
					System.out.println("c");
				}
				else {
					// ����κ��� ����
					System.out.println("d");
				}
			}
			// Case 4: arr1 x��ǥ�� arr2 x��ǥ�� ��ġ�� �ʴ� ���
			else {
				// ����κ��� ����
				System.out.println("d");
			}
			
		}
		
			
			
	}

}
