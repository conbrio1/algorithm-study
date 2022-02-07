package boj.boj2636;
// 치즈
// bfs

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int W, H;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static void search(char[][] map, boolean[][] visited, Queue<Point> queue, Queue<Point> cQueue) {
        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = now.row + dr[i];
                int nextC = now.col + dc[i];

                if (nextR < 0 || nextC < 0 || nextR >= H || nextC >= W || visited[nextR][nextC])
                    continue;
                
                visited[nextR][nextC] = true;
                if (map[nextR][nextC] == '0')
                    queue.offer(new Point(nextR, nextC));
                else
                    cQueue.offer(new Point(nextR, nextC));
            }
        }
    }

    static int melt(char[][] map, boolean[][] visited, Queue<Point> queue, Queue<Point> cQueue) {
        int qSize = cQueue.size();
        while (!cQueue.isEmpty()) {
            Point cheese = cQueue.poll();
            int cheeseR = cheese.row;
            int cheeseC = cheese.col;

            map[cheeseR][cheeseC] = '0';
            queue.offer(new Point(cheeseR, cheeseC));
        }
        return qSize;
    }

    static void solution(char[][] map) {
        StringBuilder sb = new StringBuilder();

        boolean[][] visited = new boolean[H][W];
        Queue<Point> queue = new LinkedList<>();
        Queue<Point> cQueue = new LinkedList<>();

        queue.offer(new Point(0, 0));
        visited[0][0] = true;

        int totalTime = 0;
        int lastCheese = 0;
        while (!queue.isEmpty()) {
            search(map, visited, queue, cQueue);
            int tmp = melt(map, visited, queue, cQueue);

            if (tmp != 0) {
                lastCheese = tmp;
                totalTime++;
            }
        }
        sb.append(totalTime + "\n" + lastCheese);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        char[][] map = new char[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }
        solution(map);
    }
}
