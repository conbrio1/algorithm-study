package boj.boj17406;
// 배열 돌리기 4
// 구현 + 순열
// https://www.acmicpc.net/source/33652262
// https://www.acmicpc.net/source/33360787
// 회전시에 한칸씩만 움직이므로 boj16926처럼 Queue나 layer 배열로 한겹씩 벗겨내어 저장했다가 다시 넣어주는 것은 다소 비효율적임
// 대신 그냥 한칸 옮겨주도록 하자.
// -->
// 이처럼 순환 시에 씹히는 원소가 없도록 index 지정에 주의 또 주의하자.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int answer = 5000;

    static int rotateAndMin(int[][] rotationMap, int r, int c, int s) {
        int layerNum = s;
        r = r - 1;
        c = c - 1;

        for (int i = 1; i <= layerNum; i++) {
            int temp = rotationMap[r - i][c - i];
            for (int j = r - i + 1; j <= r + i; j++)
                rotationMap[j - 1][c - i] = rotationMap[j][c - i];
            for (int j = c - i + 1; j <= c + i; j++)
                rotationMap[r + i][j - 1] = rotationMap[r + i][j];
            for (int j = r + i - 1; j >= r - i; j--)
                rotationMap[j + 1][c + i] = rotationMap[j][c + i];
            for (int j = c + i - 1; j > c - i; j--)
                rotationMap[r - i][j + 1] = rotationMap[r - i][j];
            rotationMap[r - i][c - i + 1] = temp;
        }

        int min = 5000;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += rotationMap[i][j];
            }
            if (sum < min)
                min = sum;
        }
        return min;
    }

    static void solution(int[][] map, int[][] infos, boolean[] visited, ArrayList<int[]> rotations) {
        if (rotations.size() == K) {
            int[][] temp = new int[N][M];
            for (int i = 0; i < N; i++)
                System.arraycopy(map[i], 0, temp[i], 0, M);

            int result = 0;
            for (int[] rotation : rotations) {
                result = rotateAndMin(temp, rotation[0], rotation[1], rotation[2]);
            }
            answer = Math.min(answer, result);
            return;
        }

        for (int i = 0; i < infos.length; i++) {
            if (visited[i])
                continue;
            rotations.add(infos[i]);
            visited[i] = true;
            solution(map, infos, visited, rotations);
            rotations.remove(infos[i]);
            visited[i] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] infos = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            infos[i][0] = Integer.parseInt(st.nextToken());
            infos[i][1] = Integer.parseInt(st.nextToken());
            infos[i][2] = Integer.parseInt(st.nextToken());
        }
        boolean[] visited = new boolean[K];
        solution(map, infos, visited, new ArrayList<>());

        System.out.println(answer);
    }
}
