import java.util.Arrays;
import java.util.Scanner;

public class BJ2448_01 {
    static int[][] box;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // 입력 받은 세로 행의 크기에 따른 가로 열의 크기 계산
        int size = N/3*5 + (N/3-1);
        // 행, 열 사이즈를 바탕으로 이차원 배열 생성
        box = new int[N][size];
        // 재귀 함수 호출, *과 " " 위치에 따라 1 또는 0으로 이차원 배열을 채움
        recursion(0,0,size-1, N);
        // 출력을 저장할 StringBuilder
        StringBuilder sb = new StringBuilder();
        // 이차원 배열을 순회하며 1인 경우에만 * 출력에 추가
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                if(box[i][j]==1){
                    sb.append("*");
                }
                else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    // 한번의 재귀 호출을 할 때, 삼각형의 세 꼭짓점과 그 높이를 매개변수로 받음
    // upCenter : 삼각형의 맨 위 꼭짓점의 i열 (row)
    // leftBottom : 삼각형의 맨 왼쪽 꼭짓점의 j열 (column)
    // rightBottom : 삼각형의 맨 오른 꼭짓점의 j열 (column)
    static void recursion (int upCenter, int leftBottom, int rightBottom, int height){
        // 기저 사례 : 높이가 3인 경우 가장 작은 삼각형이므로 출력
        if(height==3){
            // 현재 입력받은 좌표에서 상대값으로 이차원 배열 box의 별 자리에 1 대입
            box[upCenter][(rightBottom-leftBottom)/2+leftBottom] = 1;
            box[upCenter+1][(rightBottom-leftBottom)/2-1+leftBottom] = 1;
            box[upCenter+1][(rightBottom-leftBottom)/2+1+leftBottom] = 1;
            for (int i = leftBottom; i <= rightBottom ; i++) {
                box[upCenter+2][i] = 1;
            }
            return;
        }
        // 삼각형은 더 작은 삼각형 3개로 나눠질 수 있음, 각 삼각형에 대해 각 꼭짓접의 상대좌표를 계산해 재귀호출
        // 1. 위쪽 삼각형 (upCenter: 맨 위 삼각형은 기존과 같은 맨 위 꼭짓점을 가짐 /  leftBottom, rightBottom : 현재 leftBottom, rightBottom에서 좌표를 계산식으로 도출)
        recursion(upCenter,(leftBottom+leftBottom+(rightBottom-leftBottom)/2-1)/2+1,(leftBottom+(rightBottom-leftBottom)/2+1+rightBottom)/2-1,height/2);
        // 2. 왼쪽 아래 삼각형
        recursion(upCenter+height/2,leftBottom,leftBottom+(rightBottom-leftBottom)/2-1, height/2);
        // 3. 오른쪽 아래 삼각형
        recursion(upCenter+height/2,leftBottom+(rightBottom-leftBottom)/2+1,rightBottom,height/2);
    }
}
