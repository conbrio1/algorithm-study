package boj.boj9461;
// 결과값이 이렇게 크게 나올 줄 몰랐음
// long 주의

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /**
     * 1 1 1 2 2 3 4 5 7 9
     * P(N) = P(N-1) + P(N-5) (N > 5)
     */
    static long[] memo = new long[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        long[] base = new long[]{0, 1, 1, 1, 2, 2};
        System.arraycopy(base, 0, memo, 0, base.length);

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(solution(N)).append("\n");
        }

        System.out.println(sb);
    }

    private static long solution(int N) {
        if (N <= 5)
            return memo[N];

        if (memo[N] != 0)
            return memo[N];

        return memo[N] = solution(N - 1) + solution(N - 5);
    }
}
