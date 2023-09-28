import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]), M = Integer.parseInt(in[1]);
        in = br.readLine().split(" ");
        int R = Integer.parseInt(in[0]), C = Integer.parseInt(in[1]);
        Direction D = null;
        switch (Integer.parseInt(in[2])) {
            case 0: D = Direction.NORTH; break;
            case 1: D = Direction.EAST; break;
            case 2: D = Direction.SOUTH; break;
            case 3: D = Direction.WEST; break;
        }

        Status[][] room = new Status[N][M];

        for (int i = 0; i < N; i++) {
            in = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(in[j]) == 0 ? Status.EMPTY : Status.WALL;
            }
        }

        int count = 0;
        int x = R, y = C;
        Direction dir = D;

        loop: while (true) {

            if (room[x][y] == Status.EMPTY) {
                room[x][y] = Status.CLEANED;
                count++;
            }

            for (Direction d = dir.turn(); ; d = d.turn()) {

                int[] delta = d.getDelta();
                int nx = x + delta[0], ny = y + delta[1];

                if (0 <= nx && nx < N && 0 <= ny && ny < M && room[nx][ny] == Status.EMPTY) {
                    x = nx;
                    y = ny;
                    dir = d;
                    continue loop;
                }

                if (d == dir)
                    break;
            }

            int[] delta = dir.turn().turn().getDelta();
            int nx = x + delta[0], ny = y + delta[1];

            if (0 <= nx && nx < N && 0 <= ny && ny < M && room[nx][ny] == Status.CLEANED) {
                x = nx;
                y = ny;
            } else {
                break;
            }
        }


        bw.write(count + "\n");
        bw.close();
    }

    enum Status {EMPTY, CLEANED, WALL}
    enum Direction {
        NORTH() {
            Direction turn() { return Direction.WEST; }
            int[] getDelta() { return new int[]{-1, 0}; }
        },
        EAST {
            Direction turn() { return Direction.NORTH; }
            int[] getDelta() { return new int[]{0, 1}; }
        },
        SOUTH {
            Direction turn() { return Direction.EAST; }
            int[] getDelta() { return new int[]{1, 0}; }
        },
        WEST {
            Direction turn() { return Direction.SOUTH; }
            int[] getDelta() { return new int[]{0, -1}; }
        };

        abstract Direction turn();
        abstract int[] getDelta();
    }
}