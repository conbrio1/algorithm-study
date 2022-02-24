package boj.boj2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        System.out.println(bfs(map, N - 1, M - 1));
    }

    private static int bfs(char[][] map, int targetRow, int targetCol) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];

        queue.offer(new int[] {0, 0});
        visited[0][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int answer = 0;
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            answer++;
            for (int i = 0; i < qSize; i++) {
                int[] now = queue.poll();

                if (now[0] == targetRow && now[1] == targetCol)
                    return answer;

                for (int j = 0; j < 4; j++) {
                    int nR = now[0] + dr[j];
                    int nC = now[1] + dc[j];

                    if (nR < 0 || nR >= map.length || nC < 0 || nC >= map[0].length)
                        continue;
                    if (visited[nR][nC] || map[nR][nC] == '0')
                        continue;

                    queue.offer(new int[] {nR, nC});
                    visited[nR][nC] = true;
                }
            }
        }
        return -1;
    }
}
