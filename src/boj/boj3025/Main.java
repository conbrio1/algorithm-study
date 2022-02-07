package boj.boj3025;
// 돌 던지기

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Path {
    public int row;
    public int[] col;

    public Path(int rowNum, int startCol) {
        this.row = 0;
        this.col = new int[rowNum];
        this.col[0] = startCol;
    }
}

public class Main {
    public static int R, C, N;
    public static char[][] map;
    public static int[] input;

    public static Path[] paths;

    public static boolean check(int row, int col, int direction) {
        // direction = -1: 왼쪽 확인, direction = 1: 오른쪽 확인
        if ((direction == -1 && col - 1 < 0) || (direction == 1 && col + 1 > C - 1))
            return false;

        if (map[row][col + direction] == '.' && map[row + 1][col + direction] == '.')
            return true;
        else
            return false;
    }

    public static void insert(int col) {
        int r = paths[col].row;
        int c = paths[col].col[r];
        map[r][c] = 'O';
    }

    public static void update(int col) {
        Path path = paths[col];
        while (true) {
            int r = path.row;
            int c = path.col[r];

            if (r > 0 && map[r][c] == 'O') { // 경로에 'O'가 생겼다면 '.'이 될때까지 되돌아 간다.
                path.row--;
                continue;
            }

            if (r == R - 1) { // 끝에 도달
                break;
            }

            if (map[r + 1][c] == 'X') {
                break; // 다음 행이 'X'라면 STOP
            } else if (map[r + 1][c] == '.') {
                path.col[++path.row] = c; // 다음 행이 '.'라면 같은 열로 간다.
            } else {
                if (check(r, c, -1)) {
                    path.col[++path.row] = c - 1; // 다음 행에는 한 칸 왼쪽의 열로 간다.
                } else if (check(r, c, 1)) {
                    path.col[++path.row] = c + 1; // 다음 행에는 한 칸 오른쪽의 열로 간다.
                } else {
                    break; // 왼쪽, 오른쪽 둘 다 불가하면 STOP
                }
            }
        }
    }

    public static void solve() {
        for (int i = 0; i < C; i++) {
            update(i);
        }

        for (int i = 0; i < N; i++) {
            insert(input[i] - 1);
            for (int j = 0; j < C; j++) {
                update(j);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        paths = new Path[C];
        for (int i = 0; i < C; i++)
            paths[i] = new Path(R, i);

        for (int i = 0; i < R; i++)
            map[i] = br.readLine().toCharArray();

        N = Integer.parseInt(br.readLine());
        input = new int[N];
        for (int i = 0; i < N; i++)
            input[i] = Integer.parseInt(br.readLine());

        solve();

        for (int i = 0; i < R; i++)
            sb.append(map[i]).append("\n");

        bw.write(sb.toString());
        bw.close();
    }
}