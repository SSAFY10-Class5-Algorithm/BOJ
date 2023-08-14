import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int R = sc.nextInt(); // 연산횟수

        int[][] arr = new int[N][M];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

//        int num = sc.nextInt(); // 해야하는 연산 번호
        
        for (int r = 0; r < R; r++) {
        	int num = sc.nextInt();
        	if (num == 1) { // 상하 반전
            	int[][] newArr = new int[arr.length][arr[0].length];
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr[0].length; j++) {
                        newArr[i][j] = arr[arr.length-1-i][j];
//                        System.out.print(newArr[i][j] +" ");
                    }
                }
                arr = newArr.clone();          
            }
            
            if (num == 2) { // 좌우 반전
            	int[][] newArr = new int[arr.length][arr[0].length];
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr[0].length; j++) {
                    	newArr[i][j] = arr[i][arr[0].length-1-j];
                    }
                }
                arr = newArr.clone();
            }

            if (num == 3) { // 오른쪽 90도
            	int[][] newArr = new int[arr[0].length][arr.length];
                for (int j = 0; j < arr[0].length; j++) {
                    for (int i = 0; i < arr.length; i++) {
                    	newArr[j][i] = arr[arr.length-1-i][j];
//                    	System.out.print(newArr[j][i] + " ");
                    }
                }
                arr = newArr.clone();
            }

            if (num == 4) { // 왼쪽 90도
            	int[][] newArr = new int[arr[0].length][arr.length];
                for (int j = 0; j < arr[0].length; j++) {
                    for (int i = 0; i < arr.length; i++) {
                    	newArr[j][i] = arr[i][arr[0].length-1-j];
                    }
                }
                arr = newArr.clone();
            }

            if (num == 5) { // 사분면 시계방향
            	// 1사분면 저장
            	int[][] one = new int[arr.length/2][arr[0].length/2];
            	for (int i = 0; i < arr.length/2; i++) {
    				for (int j = 0; j < arr[0].length/2; j++) {
    					one[i][j] = arr[i][j];
    				}
    			}
            	
            	// 2사분면 저장
            	int[][] two = new int[arr.length/2][arr[0].length/2];
            	for (int i = 0; i < arr.length/2; i++) {
    				for (int j = arr[0].length/2; j < arr[0].length; j++) {
    					two[i][j-arr[0].length/2] = arr[i][j];
    				}
    			}
            	
            	// 3사분면 저장
            	int[][] three = new int[arr.length/2][arr[0].length/2];
            	for (int i = arr.length/2; i < arr.length; i++) {
    				for (int j = arr[0].length/2; j < arr[0].length; j++) {
    					three[i-arr.length/2][j-arr[0].length/2] = arr[i][j];
    				}
    			}
            	
            	// 4사분면 저장
            	int[][] four = new int[arr.length/2][arr[0].length/2];
            	for (int i = arr.length/2; i < arr.length; i++) {
    				for (int j = 0; j < arr[0].length/2; j++) {
    					four[i-arr.length/2][j] = arr[i][j];
    				}
    			}
            	// 1사분면 -> 2자리에
            	for (int i = 0; i < arr.length/2; i++) {
    				for (int j = arr[0].length/2; j < arr[0].length; j++) {
    					arr[i][j] = one[i][j-arr[0].length/2];
    				}
    			}
            	// 2사분면 -> 3자리에
            	for (int i = arr.length/2; i < arr.length; i++) {
    				for (int j = arr[0].length/2; j < arr[0].length; j++) {
    					arr[i][j] = two[i-arr.length/2][j-arr[0].length/2];
    				}
    			}
            	// 3사분면 -> 4자리에
            	for (int i = arr.length/2; i < arr.length; i++) {
    				for (int j = 0; j < arr[0].length/2; j++) {
    					arr[i][j] = three[i-arr.length/2][j];
    				}
    			}
            	// 4사분면 -> 1자리에
            	for (int i = 0; i < arr.length/2; i++) {
    				for (int j = 0; j < arr[0].length/2; j++) {
    					arr[i][j] = four[i][j];
    				}
    			}
            	
            }

            if (num == 6) { // 사분면 반시계방향
            	// 1사분면 저장
            	int[][] one = new int[arr.length/2][arr[0].length/2];
            	for (int i = 0; i < arr.length/2; i++) {
    				for (int j = 0; j < arr[0].length/2; j++) {
    					one[i][j] = arr[i][j];
    				}
    			}
            	
            	// 2사분면 저장
            	int[][] two = new int[arr.length/2][arr[0].length/2];
            	for (int i = 0; i < arr.length/2; i++) {
    				for (int j = arr[0].length/2; j < arr[0].length; j++) {
    					two[i][j-arr[0].length/2] = arr[i][j];
    				}
    			}
            	
            	// 3사분면 저장
            	int[][] three = new int[arr.length/2][arr[0].length/2];
            	for (int i = arr.length/2; i < arr.length; i++) {
    				for (int j = arr[0].length/2; j < arr[0].length; j++) {
    					three[i-arr.length/2][j-arr[0].length/2] = arr[i][j];
    				}
    			}
            	
            	// 4사분면 저장
            	int[][] four = new int[arr.length/2][arr[0].length/2];
            	for (int i = arr.length/2; i < arr.length; i++) {
    				for (int j = 0; j < arr[0].length/2; j++) {
    					four[i-arr.length/2][j] = arr[i][j];
    				}
    			}
            	
            	// 1사분면 -> 4자리에
            	for (int i = arr.length/2; i < arr.length; i++) {
    				for (int j = 0; j < arr[0].length/2; j++) {
    					arr[i][j] = one[i-arr.length/2][j];
    				}
    			}
            	// 2사분면 -> 1자리에
            	for (int i = 0; i < arr.length/2; i++) {
    				for (int j = 0; j < arr[0].length/2; j++) {
    					arr[i][j] = two[i][j];
    				}
    			}
            	// 3사분면 -> 2자리에
            	for (int i = 0; i < arr.length/2; i++) {
    				for (int j = arr[0].length/2; j < arr[0].length; j++) {
    					arr[i][j] = three[i][j-arr[0].length/2];
    				}
    			}
            	// 4사분면 -> 3자리에
            	for (int i = arr.length/2; i < arr.length; i++) {
    				for (int j = arr[0].length/2; j < arr[0].length; j++) {
    					arr[i][j] = four[i-arr.length/2][j-arr[0].length/2];
    				}
    			}
            }
		}

        for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
    }
}