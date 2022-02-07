package boj.boj17135;
// 캐슬 디펜스
// https://www.acmicpc.net/source/21720167 참고하여 공부하자.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Archer {
    public int[] position;
    public int[] target;
    public int minDistance;

    public Archer(int row, int col) {
        position = new int[] { row, col };
        target = new int[] { -1, -1 };
        minDistance = 11;
    }

    public void setTarget(int row, int col) {
        target = new int[] { row, col };
    }
}

public class Main {
    static int N, M, D;
    static int[][] grid, gridCopy;
    static int maxKill, killCount;

    static final int ARCHER_NUM = 3;
    static Archer[] archer = new Archer[ARCHER_NUM];

    static void nextTurn() {
        // killCount 계산
        for (int i = 0; i < ARCHER_NUM; i++) {
            if (archer[i].target[0] == -1 && archer[i].target[1] == -1)
                continue;

            boolean invalid = false;
            for (int j = i - 1; j >= 0; j--) {
                // 중복 확인
                if (archer[j].target[0] == archer[i].target[0] && archer[j].target[1] == archer[i].target[1]) {
                    invalid = true;
                    break;
                }
            }
            if (!invalid) {
                killCount++;
                gridCopy[archer[i].target[0]][archer[i].target[1]] = 0;
            }
        }

        // 초기화
        for (int i = 0; i < ARCHER_NUM; i++) {
            archer[i].target[0] = -1;
            archer[i].target[1] = -1;
            archer[i].minDistance = 11;
        }

        // 전진
        for (int r = N - 2; r >= 0; r--) {
            gridCopy[r + 1] = gridCopy[r];
        }
        gridCopy[0] = new int[M];
    }

    static boolean isFinished() {
        for (int[] row : gridCopy) {
            for (int cell : row) {
                if (cell == 1)
                    return false;
            }
        }
        return true;
    }

    static int checkDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    static void find() {
        int reach = (D > N) ? N : D;
        for (int k = 0; k < ARCHER_NUM; k++) {
            for (int i = N - 1; i >= N - reach; i--) {
                for (int j = 0; j < M; j++) {
                    if (gridCopy[i][j] == 1) { // enemy is here
                        int distance = checkDistance(archer[k].position[0], archer[k].position[1], i, j);
                        if (distance <= D) {
                            if (archer[k].minDistance > distance) {
                                archer[k].minDistance = distance;
                                archer[k].target[0] = i;
                                archer[k].target[1] = j;
                            } else if (archer[k].minDistance == distance && archer[k].target[1] > j) {
                                archer[k].target[0] = i;
                                archer[k].target[1] = j;
                            }
                        }
                    }
                }
            }
        }
        nextTurn();
        if (!isFinished())
            find();
    }

    static void solve(int count, int index) {
        if (count == ARCHER_NUM) {
            killCount = 0;
            gridCopy = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++)
                    gridCopy[i][j] = grid[i][j];
            }
            find();
            maxKill = Math.max(killCount, maxKill);
            return;
        }

        for (int i = index; i < M; i++) {
            archer[count] = new Archer(N, i);
            solve(count + 1, i + 1);
            archer[count] = null;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve(0, 0);

        sb.append(maxKill + "\n");
        bw.write(sb.toString());
        bw.close();
    }
}
