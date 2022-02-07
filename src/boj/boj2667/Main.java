package boj.boj2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    static int bfs(char[][] map, boolean[][] visited, int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { r, c });
        visited[r][c] = true;
        int size = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            size++;
            for (int i = 0; i < dir.length; i++) {
                int nextR = cur[0] + dir[i][0];
                int nextC = cur[1] + dir[i][1];

                if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N)
                    continue;
                if (visited[nextR][nextC] || map[nextR][nextC] == '0')
                    continue;

                queue.offer(new int[] { nextR, nextC });
                visited[nextR][nextC] = true;
            }
        }

        return size;
    }

    static void solution(char[][] map) {
        boolean[][] visited = new boolean[N][N];
        LinkedList<Integer> answer = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '1' && !visited[i][j])
                    answer.offer(bfs(map, visited, i, j));
            }
        }

        Collections.sort(answer);
        StringBuilder sb = new StringBuilder();
        sb.append(answer.size() + "\n");
        for (int element : answer) {
            sb.append(element + "\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        solution(map);
    }
}
