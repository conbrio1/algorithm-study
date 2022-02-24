package boj.boj9251;
// LCS
// https://www.acmicpc.net/problem/9251
// DP

// 시간초과
// top-down 접근할 때 memoization 빠뜨리지 말기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        memo = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            Arrays.fill(memo[i], 1, s2.length() + 1, -1);
        }
        System.out.println(dp2(s1, s2, s1.length(), s2.length()));
    }

    // top-down
    private static int dp(String s1, String s2, int i1, int i2) {
        // base condition
        if (i1 == 0 || i2 == 0)
            return memo[i1][i2];

        // memoization
        if (memo[i1][i2] != -1)
            return memo[i1][i2];

        // solving
        if (s1.charAt(i1 - 1) == s2.charAt(i2 - 1))
            return memo[i1][i2] = dp(s1, s2, i1 - 1, i2 - 1) + 1;
        return memo[i1][i2] = Math.max(dp(s1, s2, i1 - 1, i2), dp(s1, s2, i1, i2 - 1));
    }

    // bottom-up
    private static int dp2(String s1, String s2, int l1, int l2) {
        // base condition
        if (l1 == 0 || l2 == 0)
            return memo[l1][l2];

        // solving
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1))
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                else
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
            }
        }
        return memo[l1][l2];
    }
}
