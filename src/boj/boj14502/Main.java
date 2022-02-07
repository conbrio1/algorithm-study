package boj.boj14502;
// 연구소

// dfs + bfs

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        LinkedList<int[]> emptyList = new LinkedList<>();
        LinkedList<int[]> virusList = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 0) emptyList.offer(new int[]{i, j});
                else if (map[i][j] == 2) virusList.offer(new int[]{i, j});
            }
        }

        // 가능한 3개 벽 설치의 조합을 구하여 안전 영역 크기를 계산
        solution(map, new int[3][2], emptyList, virusList, 0, 0);
        System.out.println(answer);
    }

    private static void solution(int[][] map, int[][] selectedWalls, LinkedList<int[]> emptyList, LinkedList<int[]> virusList, int start, int count) {
        // 3개의 벽 선택 완료
        if (count == 3) {
            // 구한 각 벽 조합마다 안전 영역 크기 구하기
            answer = Math.max(answer, bfs(map, selectedWalls, emptyList, virusList));
            return;
        }

        for (int i = start; i < emptyList.size(); i++) {
            selectedWalls[count][0] = emptyList.get(i)[0];
            selectedWalls[count][1] = emptyList.get(i)[1];
            solution(map, selectedWalls, emptyList, virusList, i + 1, count + 1);
        }
    }

    private static int bfs(int[][] map, int[][] walls, LinkedList<int[]> emptyList, LinkedList<int[]> virusList) {
        Queue<int[]> vQueue = new LinkedList<>(virusList);
        boolean[][] visited = new boolean[map.length][map[0].length];
        int safeArea = emptyList.size() - 3; // 벽 3개 빼줌

        for (int[] virus : virusList)
            visited[virus[0]][virus[1]] = true;
        for (int[] wall : walls)
            visited[wall[0]][wall[1]] = true;

        while (!vQueue.isEmpty()) {
            int[] now = vQueue.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = now[0] + dr[i];
                int nextC = now[1] + dc[i];

                if (nextR < 0 || nextR >= map.length || nextC < 0 || nextC >= map[0].length)
                    continue;
                if (map[nextR][nextC] == 1 || visited[nextR][nextC])
                    continue;

                vQueue.offer(new int[]{nextR, nextC});
                visited[nextR][nextC] = true;
                safeArea--;
            }
        }

        return safeArea;
    }
}
