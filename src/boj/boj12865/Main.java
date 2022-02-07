package boj.boj12865;
// 평범한 배낭

// DP
// 냅색 - 2차원 배열 버전
// https://dudri63.github.io/2019/01/29/algo22/
// https://chanhuiseok.github.io/posts/improve-6/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] weight = new int[N + 1];
        int[] value = new int[N + 1];
        for (int n = 1; n <= N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            weight[n] = Integer.parseInt(st.nextToken());
            value[n] = Integer.parseInt(st.nextToken());
        }
        dp = new int[N + 1][K + 1];

        System.out.println(solution(weight, value, N, K));
    }

    private static int solution(int[] weight, int[] value, int N, int K) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                if (weight[i] > j)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
            }
        }
        return dp[N][K];
    }
}
