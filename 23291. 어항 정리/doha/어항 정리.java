import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
1. 물고기 수가 가장 적은 어항에 물고기 넣기(여러 개면 다 넣기)
2. 어항 쌓기(왼쪽에 있는 어항을 그 어항 오른쪽에 있는 어항 위에 올리기)
3. 2개 이상 쌓여 있는 어항을 모두 공중부양 시킨 다음 90도로 회전시키기(안 튀어나올 때까지)
4. 인접한 어항 물고기 차이 구하기
5. 일렬로 놓기
6. 다시 접기
7. 물고기 차이 구하기
8. 일렬로 놓기
9. 이게 한 번임...ㅋㅋㅋㅋㅋㅋ
 */
public class Main {
    static ArrayList<Integer>[] fishbowlContainer;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int N;
    static int K;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 입력

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fishbowlContainer = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            fishbowlContainer[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fishbowlContainer[N - 1].add(Integer.parseInt(st.nextToken()));
        }

        cleaningFishbowl();
    }

    static void cleaningFishbowl() {
        int substract = 0;
        int turn = 1;
        while (true) {
            // 물고기 적은 어항에 물고기 넣기
            insertFish();

            // 어항 굴리기
            rolling();

            // 물고기 조절하고 펼치기
            addAndsubstract();

            // 다시 공중부양 작업 2번
            rolling2();

            // 다시 조절하고 펼치기
            addAndsubstract();


            // 물고기 많은 어항과 적은 어항 차이 구하기
            substract = substractMaxAndMin();
            if (substract <= K) {
                System.out.println(turn);
                return;
            }

            turn++;
        }
    }

    static void addAndsubstract() {
        // 물고기 조절 작업
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            if (!fishbowlContainer[i].isEmpty()) {
                for (int j = 0; j < fishbowlContainer[i].size(); j++) {
                    for (int k = 0; k < 4; k++) {
                        int y = i + dy[k];
                        int x = j + dx[k];

                        if (y < 0 || x < 0 || y >= N || x >= N || fishbowlContainer[y].isEmpty() || x >= fishbowlContainer[y].size()) {
                            continue;
                        }

                        int d = Math.abs(fishbowlContainer[i].get(j) - fishbowlContainer[y].get(x)) / 5;

                        if (d > 0) {
                            if (fishbowlContainer[i].get(j) > fishbowlContainer[y].get(x)) {
                                temp[i][j] -= d;
                            } else if (fishbowlContainer[i].get(j) < fishbowlContainer[y].get(x)) {
                                temp[i][j] += d;
                            }
                        }
                    }
                }
            }
        }

        // 값 더해주기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (temp[i][j] != 0) {
                    fishbowlContainer[i].set(j, fishbowlContainer[i].get(j) + temp[i][j]);
                }
            }
        }


        spread();
    }

    static void spread() {
        // 다시 어항 펼치기
        ArrayList<Integer>[] newList = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            newList[i] = new ArrayList<>();
        }

        for (int j = 0; j < N; j++) {
            for (int i = N - 1; i >= 0; i--) {
                if (!fishbowlContainer[i].isEmpty() && j < fishbowlContainer[i].size()) {
                    newList[N - 1].add(fishbowlContainer[i].get(j));
                }
            }
        }

        fishbowlContainer = newList;
    }

    static void rolling() {
        int ylength = 0;
        int xlength = 0;
        int y = 2;
        int x = 1;

        // 접는 면적 구하기
        while (true) {
            int size = y * x;
            if (size > N) {
                break;
            }

            ylength = y;
            xlength = x;

            if (y == x) {
                y++;
            } else {
                x++;
            }
        }

        // 접기
        int ny = N - 1;
        int nx = ylength * xlength - xlength - 1;

        y = ylength - 2;
        x = 0;
        int dir = 0;

        int[][] temp = new int[ylength - 1][xlength];
        for (int k = nx; k >= 0; k--) {
            temp[y][x] = fishbowlContainer[ny].get(k);

            if (y + dy[dir] < 0 || y + dy[dir] >= ylength - 1 || x + dx[dir] < 0 || x + dx[dir] >= xlength || temp[y + dy[dir]][x + dx[dir]] != 0) {
                dir = dir + 1 == 4 ? 0 : dir + 1;
            }

            y += dy[dir];
            x += dx[dir];
        }

        // N-1 삭제
        for (int i = 0; i <= nx; i++) {
            fishbowlContainer[N - 1].remove(0);
        }

        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                fishbowlContainer[N - 1 - temp.length + i].add(temp[i][j]);
            }
        }
    }


    static void insertFish() {

        // 물고기 수가 가장 적은 어항에 물고기 한 마리 넣기
        int min = Integer.MAX_VALUE;
        for (int num : fishbowlContainer[N - 1]) {
            if (num < min) {
                min = num;
            }
        }

        for (int i = 0; i < N; i++) {
            if (min == fishbowlContainer[N - 1].get(i)) {
                fishbowlContainer[N - 1].set(i, fishbowlContainer[N - 1].get(i) + 1);
            }
        }
    }

    static void rolling2() {
        for (int k = 1; k <= 2; k++) {
            for (int j = 0; j < k; j++) {
                // 반복 횟수
                for (int i = 0; i < N / 2 / k; i++) {
                    fishbowlContainer[N - 1 - k - j].add(0, fishbowlContainer[N - k + j].remove(0));
                }
            }
        }
    }

    static int substractMaxAndMin() {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int num : fishbowlContainer[N - 1]) {
            if (num > max) {
                max = num;
            }

            if (num < min) {
                min = num;
            }
        }

        return max - min;
    }
}