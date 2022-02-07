package boj.boj1182;
// 부분수열의 합

// 부분집합

// 투포인터로 풀었더니.. 모든 원소가 양수인 경우에만 start = 0, end = 0으로 할 수 있다.
// 부분수열은 연속으로 고르지 않아도 됨. 원래의 순서만 유지되면 됨

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] input = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        find(input, S, 0, 0);

        if (S == 0) answer--;
        System.out.println(answer);
    }

    private static void find(int[] input, int target, int count, int sum) {
        if (count == input.length) {
            if (sum == target)
                answer++;
            return;
        }

        find(input, target, count + 1, sum + input[count]);
        find(input, target, count + 1, sum);
    }
}
