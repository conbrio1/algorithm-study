package boj.boj1904;
// 01타일

// DP
/*
N=1 1
1
N=2 11 / 00
1 + 1 = 2
N=3 111 / 100 001
1 + 2 = 3
N=4 1111 / 1100 0011 1001 / 0000
1 + 3 + 1 = 5
N=5 11111 / 11100 11001 10011 00111 / 10000 00100 00001
1 + 4 + 3 = 8
N=6 111111 / 111100 111001 110011 100111 001111 / 110000 100100 100001 001001 000011 001100 / 000000
1 + 5 + 6 + 1 = 13
N=7 ... 1000000 0010000 0000100 0000001
1 + 6 + 7 + 4 = 18

-> 피보나치
 */
// 피보나치 수열은 크기가 매우 커지므로 애초에 15746으로 나누어 배열에 저장하도록 한다.
// 모듈러 연산은 나눗셈을 제외한 사칙연산에 모두 분배법칙이 적용되므로 상관없다.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] mem = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        mem[1] = 1;
        mem[2] = 2;
        System.out.println(solution(N));
    }

    private static int solution(int n) {
        if (n == 1)
            return mem[1];
        if (n == 2)
            return mem[2];

        for (int i = 3; i <= n; i++) {
            mem[i] = (mem[i - 1] + mem[i - 2]) % 15746;
        }

        return mem[n];
    }
}
