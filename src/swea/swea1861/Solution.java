package swea.swea1861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int[] answer = new int[]{1001, 0};
            solution(map, N, answer);
            sb.append("#").append(t + 1).append(" ").append(answer[0]).append(" ").append(answer[1]).append("\n");
        }
        System.out.println(sb);
    }

    private static void solution(int[][] map, int N, int[] answer) {
        int[][] memo = new int[N][N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (memo[i][j] > 0)
                    continue;
                int temp = bfs(map, memo, N, i, j);
                if (max < temp) {
                    max = temp;
                    answer[0] = map[i][j];
                    answer[1] = temp;
                } else if (max == temp && answer[0] > map[i][j]) {
                    answer[0] = map[i][j];
                }
            }
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    private static int bfs(int[][] map, int[][] memo, int N, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j, 1});

        int res = 0;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            res = now[2];

            for (int dir = 0; dir < 4; dir++) {
                int nR = now[0] + dr[dir];
                int nC = now[1] + dc[dir];

                if (nR < 0 || nC < 0 || nR >= N || nC >= N)
                    continue;
                if (map[now[0]][now[1]] + 1 != map[nR][nC])
                    continue;

                if (memo[nR][nC] != 0)
                    return memo[i][j] = memo[nR][nC] + now[2];
                else
                    queue.offer(new int[]{nR, nC, now[2] + 1});
            }
        }
        return memo[i][j] = res;
    }
}
