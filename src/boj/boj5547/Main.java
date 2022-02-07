package boj.boj5547;
// 일루미네이션
// BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int W, H;

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // (x, y)에서 y가 홀수이면 (x-1, y), (x+1, y), (x, y-1), (x, y+1), (x+1, y-1), (x+1, y+1)
    //              짝수이면 (x-1, y), (x+1, y), (x, y-1), (x, y+1), (x-1, y-1), (x-1, y+1)
    static final int[] dOx = { -1, 1, 0, 0, 1, 1 };
    static final int[] dEx = { -1, 1, 0, 0, -1, -1 };
    static final int[] dy = { 0, 0, -1, 1, -1, 1 };

    static int solve(int[][] map, boolean[][] visit) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0));
        visit[0][0] = true;

        int length = 0;
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int[] dx = (now.y % 2 == 1) ? dOx : dEx;

            for (int i = 0; i < 6; i++) {
                int lineX = now.x + dx[i];
                int lineY = now.y + dy[i];

                if (lineX < 1 || lineX > W || lineY < 1 || lineY > H)
                    continue;
                if (map[lineY][lineX] == 1)
                    length++;
            }

            for (int i = 0; i < 6; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                if (nextX < 0 || nextX > W + 1 || nextY < 0 || nextY > H + 1 || visit[nextY][nextX] || map[nextY][nextX] == 1)
                    continue;

                queue.offer(new Point(nextX, nextY));
                visit[nextY][nextX] = true;
            }
        }

        return length;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        int[][] map = new int[H + 2][W + 2];
        boolean[][] visit = new boolean[H + 2][W + 2];
        for (int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= W; j++) {
                map[i][j] = st.nextToken().charAt(0) - '0';
            }
        }

        System.out.println(solve(map, visit));
    }
}
