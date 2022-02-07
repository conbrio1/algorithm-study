package boj.boj2839;
// 설탕 배달

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // dp
    static int solution(int N) {
        int[] dp = new int[N + 1];
        if (N >= 3)
            dp[3] = 1;
        if (N >= 5)
            dp[5] = 1;

        for (int i = 6; i <= N; i++) {
            if (i % 5 == 0)
                dp[i] = dp[i - 5] + 1;
            else if (i % 3 == 0)
                dp[i] = dp[i - 3] + 1;
            else {
                if (dp[i - 3] != 0 && dp[i - 5] != 0)
                    dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
            }
        }

        if (dp[N] == 0)
            return -1;
        else
            return dp[N];
    }

    // greedy
    /*
    static int solution(int N) {
        for (int i = N / 5; i >= 0; i--) {
            for (int j = 0; 5 * i + 3 * j <= N; j++) {
                if (5 * i + 3 * j == N)
                    return i + j;
            }
        }
        return -1;
    }
    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(solution(N));
    }
}
