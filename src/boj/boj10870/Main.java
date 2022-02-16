package boj.boj10870;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());
        memo = new int[input + 1];
        System.out.println(solution(input));
    }

    public static int solution(int input) {
        if (input < 2)
            return input;
        
        if (memo[input] != 0)
            return memo[input];
        
        return memo[input] = solution(input - 1) + solution(input - 2);
    }
}
