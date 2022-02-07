package boj.boj9205;
// 맥주 마시면서 걸어가기
// bfs 응용

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int x, y;
        int idx;

        public Point(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }

    static int computeDistance(int[][] distances, Point[] path, int aIdx, int bIdx) {
        if (aIdx == bIdx)
            return 0;
        else if (distances[aIdx][bIdx] == 0) {
            Point a = path[aIdx];
            Point b = path[bIdx];
            distances[aIdx][bIdx] = distances[bIdx][aIdx] = Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
        }

        return distances[aIdx][bIdx];
    }

    static StringBuilder solution(Point[] path) {
        StringBuilder sb = new StringBuilder();

        Queue<Point> queue = new LinkedList<>();
        boolean[] visited = new boolean[path.length];

        int[][] distances = new int[path.length][path.length];
        int rest = 0;

        queue.offer(path[0]);
        visited[0] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            if (now.idx == path.length - 1) {
                sb.append("happy");
                return sb;
            }

            for (int i = 0; i < path.length; i++) {
                if (i == now.idx || visited[i])
                    continue;
                if (computeDistance(distances, path, now.idx, i) <= 1000 + rest) {
                    queue.offer(path[i]);
                    visited[i] = true;

                    if (rest >= distances[now.idx][i] % 50)
                        rest = rest - distances[now.idx][i] % 50;
                    else
                        rest = 50 + rest - distances[now.idx][i] % 50;
                }
            }
        }
        sb.append("sad");
        return sb;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        Point[][] paths = new Point[T][];
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            paths[t] = new Point[n + 2];

            for (int i = 0; i < n + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                paths[t][i] = new Point(x, y, i);
            }
        }
        for (int t = 0; t < T; t++) {
            System.out.println(solution(paths[t]).toString());
        }
    }
}
