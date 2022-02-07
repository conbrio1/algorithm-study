package boj.boj16926;
// 배열 돌리기 1, 2(16927)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;

    static int[][] solution(int[][] map) {
        int[][] output = new int[N][M];

        // 몇 겹인지?
        int layerNum = Math.min(N, M) / 2;
        int[][] layers = new int[layerNum][];

        for (int i = 0 ; i < layerNum; i++) {
            layers[i] = new int[2 * (N - 2 * i) + 2 * (M - 2 * i) - 4];
            int index = 0;
            
            for (int j = i; j < M - i; j++) {
                layers[i][index++] = map[i][j];
            }
            for (int j = i + 1; j < N - i; j++) {
                layers[i][index++] = map[j][M - i - 1];
            }
            for (int j = M - i - 2; j >= i; j--) {
                layers[i][index++] = map[N - i - 1][j];
            }
            for (int j = N - i - 2; j >= i + 1; j--) {
                layers[i][index++] = map[j][i];
            }
        }

        for (int i = 0 ; i < layerNum; i++) {
            int index = R % layers[i].length;

            for (int j = i; j < M - i; j++) {
                output[i][j] = layers[i][index];
                index = (index + 1 > layers[i].length - 1) ? 0 : (index + 1);
            }
            for (int j = i + 1; j < N - i; j++) {
                output[j][M - i - 1] = layers[i][index];
                index = (index + 1 > layers[i].length - 1) ? 0 : (index + 1);
            }
            for (int j = M - i - 2; j >= i; j--) {
                output[N - i - 1][j] = layers[i][index];
                index = (index + 1 > layers[i].length - 1) ? 0 : (index + 1);
            }
            for (int j = N - i - 2; j >= i + 1; j--) {
                output[j][i] = layers[i][index];
                index = (index + 1 > layers[i].length - 1) ? 0 : (index + 1);
            }
        }

        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] output = solution(map);

        StringBuilder sb = new StringBuilder();
        for (int[] row : output) {
            for (int element : row) {
                sb.append(element + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
