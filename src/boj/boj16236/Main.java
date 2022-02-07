package boj.boj16236;
// 아기 상어
// BFS를 처음에 방향별로 queue에 쌓아 처리하니 실패
// BFS를 각 거리 별로 모두 queue에 쌓아 처리할 것

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;

    static class Point {
        int r, c;
        int distance;

        public Point(int r, int c, int distance) {
            this.r = r;
            this.c = c;
            this.distance = distance;
        }
    }

    static class Shark {
        private int r, c;
        private int size;
        private int eatCount;

        private static final int[] dr = { -1, 1, 0, 0 }; // 상, 하, 좌, 우
        private static final int[] dc = { 0, 0, -1, 1 };

        public Shark(int r, int c) {
            this.r = r;
            this.c = c;
            this.size = 2;
            this.eatCount = 0;
        }

        public void eat(int[][] map, Point target) {
            map[this.r][this.c] = 0;
            map[target.r][target.c] = 9;
            this.r = target.r;
            this.c = target.c;

            this.eatCount++;
            if (this.eatCount == this.size) {
                this.size++;
                this.eatCount = 0;
            }
        }

        public Point nextTarget(int[][] map) {
            // BFS
            Queue<Point> queue = new LinkedList<>();

            boolean[][] visit = new boolean[N][N];

            queue.add(new Point(this.r, this.c, 0));
            visit[r][c] = true;

            while (!queue.isEmpty()) {
                Point target = null;

                // distance별 탐색
                int qSize = queue.size();
                for (int i = 0; i < qSize; i++) {
                    Point searching = queue.poll();

                    for (int j = 0; j < 4; j++) {
                        int nextR = searching.r + dr[j];
                        int nextC = searching.c + dc[j];

                        if (nextR >= 0 && nextC >= 0 && nextR < N && nextC < N && !visit[nextR][nextC] && map[nextR][nextC] <= this.size) {
                            Point nextPoint = new Point(nextR, nextC, searching.distance + 1);
                            queue.add(nextPoint);
                            visit[nextR][nextC] = true;

                            // 먹을 수 있는 물고기 발견
                            if (map[nextR][nextC] >= 1 && map[nextR][nextC] <= 6 && map[nextR][nextC] < this.size) {
                                if (target == null)
                                    target = nextPoint;
                                else if (nextR < target.r || (nextR == target.r && nextC < target.c))
                                    target = nextPoint;
                            }
                        }
                    }
                }
                if (target != null)
                    return target;
            }
            return null;
        }

    }

    static int solve(int[][] map, int r, int c) {
        Shark babyShark = new Shark(r, c);
        int time = 0;
        Point target = babyShark.nextTarget(map);
        while (target != null) {
            time += target.distance;
            babyShark.eat(map, target);
            target = babyShark.nextTarget(map);
        }
        return time;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int sharkR = 0, sharkC = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0) - '0';
                if (map[i][j] == 9) {
                    sharkR = i;
                    sharkC = j;
                }
            }
        }
        sb.append(solve(map, sharkR, sharkC) + "\n");
        bw.write(sb.toString());
        bw.close();
    }
}
