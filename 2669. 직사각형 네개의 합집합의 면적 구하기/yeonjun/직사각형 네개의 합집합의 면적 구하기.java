import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 평면을 나타내는 2차원 배열 생성
		int[][] arr = new int[100][100];

		// 직사각형 개수
		int N = 4;

		// 직사각형 꼭지점의 좌표를 담을 배열 2개 생성
		int[] bPoint = new int[2];
		int[] tPoint = new int[2];

		// 직사각형 면적을 셀 변수 생성
		int cnt = 0;

		// 직사각형 1개씩 반복
		for (int n = 0; n < N; n++) {

			// 꼭지점 좌표 저장
			for (int i = 0; i < 2; i++) {
				bPoint[i] = sc.nextInt() - 1;
			}

			for (int j = 0; j < 2; j++) {
				tPoint[j] = sc.nextInt() - 1;
			}

			// 평면에서 직사각형이 차지하는 부분의 요소를 1로 바꿈
			for (int r = bPoint[1]; r < tPoint[1]; r++) {
				for (int c = bPoint[0]; c < tPoint[0]; c++) {
					arr[r][c] = 1;
				}
			}
		}

		// (직사각형 반복 끝나고!!!) arr에서 1의 개수를 카운트
		for (int r = 0; r < 100; r++) {
			for (int c = 0; c < 100; c++) {
				if (arr[r][c] == 1)
					cnt++;
			}
		}

		System.out.println(cnt);

	}

}