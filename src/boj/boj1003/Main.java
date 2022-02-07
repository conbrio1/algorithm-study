package boj.boj1003;
// 피보나치 함수

// DP
/*
f(3)
    f(2)
        f(1) - 1
        f(0) - 0
    f(1) - 1
----
0 : 1, 1 : 2
*/
/*
f(4)
    f(3)
        0: 1, 1 : 2
    f(2)
        f(1)
        f(0)
        0 : 1, 1 : 1
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] mem = new int[41][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        mem[0] = new int[]{1, 0};
        mem[1] = new int[]{0, 1};

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] answer = fibonacci(N);
            System.out.println(answer[0] + " " + answer[1]);
        }
    }

    private static int[] fibonacci(int n) {
        if (n == 0)
            return mem[0];
        if (n == 1)
            return mem[1];

        if (mem[n][0] != 0 || mem[n][1] != 0) {
            return mem[n];
        }

        for (int i = 2; i <= n; i++) {
            mem[i][0] = mem[i - 1][0] + mem[i - 2][0];
            mem[i][1] = mem[i - 1][1] + mem[i - 2][1];
        }
        return mem[n];
    }
}
