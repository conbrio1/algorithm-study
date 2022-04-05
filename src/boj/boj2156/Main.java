package boj.boj2156;
// 포도주 시식
// DP
// bottom-up, top-down 두가지 방법 모두 사용해보기
// 입력값 제한이 '음이 아닌 정수' 이기 때문에 모든 입력값이 0인 경우 시간 초과 발생 가능
// --> 확인하여 -1로 cache 초기화

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] p = new int[n + 1];
        int[] max = new int[n + 1];
        Arrays.fill(max, 1, n + 1, -1);
        for (int i = 1; i <= n; i++) {
            p[i] = Integer.parseInt(br.readLine());
        }
        max[1] = p[1];
        if (n > 1)
            max[2] = p[1] + p[2]; // ArrayIndexOutOfBoundsException 주의
        System.out.println(dp2(p, max, n));
    }

    // top-down
    private static int dp1(int[] p, int[] max, int n) {
        if (n <= 2)
            return max[n];
        if (max[n] != -1)
            return max[n];
        return max[n] = Math.max(Math.max(dp1(p, max, n - 1), dp1(p, max, n - 2) + p[n]),
                dp1(p, max, n - 3) + p[n - 1] + p[n]);
    }

    // bottom-up
    private static int dp2(int[] p, int[] max, int n) {
        if (n <= 2)
            return max[n];
        for (int i = 3; i <= n; i++) {
            max[i] = Math.max(Math.max(max[i - 1], max[i - 2] + p[i]), max[i - 3] + p[i - 1] + p[i]);
        }
        return max[n];
    }
}
