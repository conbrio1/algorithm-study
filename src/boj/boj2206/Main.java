package boj.boj2206;
// 벽 부수고 이동하기
// bfs 응용

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int answer = 0;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static class Point {
        int r, c;
        boolean wallFlag;

        public Point(int r, int c, boolean wallFlag) {
            this.r = r;
            this.c = c;
            this.wallFlag = wallFlag;
        }
    }

    static void solution(char[][] map) {
        boolean[][][] visited = new boolean[N][M][2];

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, false));

        while (!queue.isEmpty()) {
            answer++;
            int qSize = queue.size();

            for (int i = 0; i < qSize; i++) {
                Point now = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int nextR = now.r + dr[j];
                    int nextC = now.c + dc[j];

                    if (nextR == N - 1 && nextC == M - 1) {
                        answer++;
                        return;
                    }

                    if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= M)
                        continue;

                    if (map[nextR][nextC] == '1') {
                        if (now.wallFlag)
                            continue;
                        visited[nextR][nextC][1] = true;
                        queue.offer(new Point(nextR, nextC, true));
                    } else {
                        if (!now.wallFlag && visited[nextR][nextC][0] || now.wallFlag && visited[nextR][nextC][1])
                            continue;
                        visited[nextR][nextC][now.wallFlag ? 1 : 0] = true;
                        queue.offer(new Point(nextR, nextC, now.wallFlag));
                    }
                }
            }
        }
        if (N == 1 && M == 1)
            answer = 1;
        else
            answer = -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        solution(map);
        System.out.println(answer);
    }
}
