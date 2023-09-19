import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
내 주변을 탐색하면서 연합할 수 있는 국가가 있다면 마킹을 하고 queue에 넣음
다시 마킹 안 된 애를 탐색하면서 반복
 */
public class Main {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N;
    static int L;
    static int R;
    static int day;
    static int marking;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());


        int[][] persons = new int[N][N];
        Queue<int[]> queue = new LinkedList<>();
        int[][] mark = new int[N][N];
        int[][] info = new int[N * N + 1][2];
        marking = 1;
        day = 0;

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                persons[n][i] = Integer.parseInt(st.nextToken());
            }
        }

        findUnit(mark, persons, info, queue);
    }


    static void findUnit(int[][] mark, int[][] persons, int[][] info, Queue<int[]> queue) {
        while (true) {
            marking = 1;
            queue.clear();

            for (int i = 0; i <= N * N; i++) {
                if (i < N)
                    Arrays.fill(mark[i], 0);
                Arrays.fill(info[i], 0);
            }

            move(mark, queue, persons, info);

            // 연합이 발생하지 않으면
            if (marking == 1) {
                System.out.println(day);
                return;
            }

            // 국경을 열자
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (mark[i][j] != 0) {
                        persons[i][j] = info[mark[i][j]][0] / info[mark[i][j]][1];
                    }
                }
            }

//            print(persons, mark);
            day++;
        }
    }

    static void print(int[][] persons, int[][] mark) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(persons[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(mark[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    static void move(int[][] mark, Queue<int[]> queue, int[][] persons, int[][] info) {
        // 인구를 이동시키자
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (mark[i][j] != 0) {
                    continue;
                }

                queue.offer(new int[]{i, j});

                while (!queue.isEmpty()) {
                    int[] arr = queue.poll();
                    int y = arr[0];
                    int x = arr[1];

                    for (int k = 0; k < 4; k++) {

                        if (y + dy[k] < 0 || y + dy[k] >= N || x + dx[k] < 0 || x + dx[k] >= N) {
                            continue;
                        }

                        if (mark[y + dy[k]][x + dx[k]] != 0) {
                            continue;
                        }

                        int substract = Math.abs(persons[y][x] - persons[y + dy[k]][x + dx[k]]);
                        if (!(substract >= L && substract <= R)) {
                            continue;
                        }

                        if (mark[y][x] == 0) {
                            mark[y][x] = marking++;
                            info[mark[y][x]][0] = persons[y][x];
                            info[mark[y][x]][1]++;
                        }

                        mark[y + dy[k]][x + dx[k]] = mark[y][x];
                        info[mark[y][x]][0] += persons[y + dy[k]][x + dx[k]];
                        info[mark[y][x]][1]++;
                        queue.offer(new int[]{y + dy[k], x + dx[k]});
                    }
                }
            }
        }
    }

}