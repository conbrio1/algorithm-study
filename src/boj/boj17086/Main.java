package boj.boj17086;
// 아기 상어2
// level별 BFS

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        public int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M;
    static final int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
    static final int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };

    public static boolean check(boolean[][] visit) {
        for (boolean[] rows : visit) {
            for (boolean element : rows) {
                if (!element)
                    return false;
            }
        }
        return true;
    }

    public static int solve(int[][] map, List<Point> sharkList) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];

        for (Point sharkPoint : sharkList) {
            queue.offer(sharkPoint);
            visit[sharkPoint.r][sharkPoint.c] = true;
        }

        int maxSafeDist = 0;
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                Point searching = queue.poll();
                for (int j = 0; j < 8; j++) {
                    int nextR = searching.r + dr[j];
                    int nextC = searching.c + dc[j];

                    if (nextR >= 0 && nextC >= 0 && nextR < N && nextC < M && !visit[nextR][nextC]) {
                        queue.offer(new Point(nextR, nextC));
                        visit[nextR][nextC] = true;
                    }
                }
            }
            maxSafeDist++;
            if (check(visit))
                break;
        }
        return maxSafeDist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        List<Point> sharkList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = st.nextToken().charAt(0) - '0';

                if (map[i][j] == 1) {
                    sharkList.add(new Point(i, j));
                }
            }
        }

        sb.append(solve(map, sharkList));
        System.out.println(sb.toString());
    }
}
