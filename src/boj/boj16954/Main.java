package boj.boj16954;
// 움직이는 미로 탈출

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dr = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
    static int[] dc = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

    static void wallDown(char[][] map) {
        for (int i = 6; i >= 0; i--) {
            map[i + 1] = map[i];
        }
        map[0] = "........".toCharArray();
    }

    static int bfs(char[][] map) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] visited = new int[8][8];
        queue.offer(new int[] { 7, 0 });

        while (!queue.isEmpty()) {
            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                int[] now = queue.poll();

                if (map[now[0]][now[1]] == '#')
                    continue;
                if (now[0] == 0 && now[1] == 7)
                    return 1;

                for (int j = 0; j < 9; j++) {
                    int nextR = now[0] + dr[j];
                    int nextC = now[1] + dc[j];

                    if (nextR < 0 || nextC < 0 || nextR >= 8 || nextC >= 8)
                        continue;
                    if (map[nextR][nextC] == '#')
                        continue;

                    if (visited[nextR][nextC] > 0) {
                        if (dr[j] == 0 && dc[j] == 0 && visited[nextR][nextC] >= 8)
                            continue;
                    }

                    queue.offer(new int[] { nextR, nextC });
                    visited[nextR][nextC]++;
                }
                
            }
            wallDown(map);
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] map = new char[8][8];
        for (int i = 0; i < 8; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs(map));
    }
}
