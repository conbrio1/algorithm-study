package boj.boj4485;
// 녹색 옷 입은 애가 젤다지?

// 다익스트라 + bfs
// 비용 배열에 저장하는 것이 관건
// 이때 비용 배열은 반드시 최대값으로 초기화하여야 한다.
// 임의의 칸의 비용이 0인 경우 오류 발생 가능성
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 상, 하, 좌, 우
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int solution(int[][] map, int N) {
        Queue<int[]> queue = new LinkedList<>();
        // 방문 배열 대신 사용하는 count 배열
        // 방문여부 대신 해당 위치까지 오는 비용의 최솟값을 저장
        int[][] count = new int[N][N];
        for (int i = 0; i < N; i++)
            Arrays.fill(count[i], Integer.MAX_VALUE);

        queue.offer(new int[] { 0, 0 });
        count[0][0] = map[0][0];

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curR = current[0];
            int curC = current[1];

            for (int i = 0; i < 4; i++) {
                int nextR = curR + dr[i];
                int nextC = curC + dc[i];

                if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N)
                    continue;
                // 현재 위치에서 다음 위치로 가는 비용이 기존에 저장된 비용보다 크면 skip
                int nextCost = count[curR][curC] + map[nextR][nextC];
                if (nextCost >= count[nextR][nextC])
                    continue;

                queue.offer(new int[] { nextR, nextC });
                count[nextR][nextC] = nextCost;
            }
        }

        return count[N - 1][N - 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0)
                break;
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.printf("Problem %d: %d\n", T++, solution(map, N));
        }
    }
}
