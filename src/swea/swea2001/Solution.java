package swea.swea2001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append("#").append(t + 1).append(" ").append(solution(map, N, M)).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int solution(int[][] map, int N, int M) {
        int answer = 0;
        for (int i = 0; i <= N - M; i++) {
            for (int j = 0; j <= N - M; j++) {
                int temp = 0;
                for (int k = i; k < i + M; k++) {
                    for (int l = j; l < j + M; l++) {
                        temp += map[k][l];
                    }
                }
                answer = Math.max(answer, temp);
            }
        }
        return answer;
    }
}
