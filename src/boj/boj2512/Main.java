package boj.boj2512;
// 예산
// 이분탐색
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] inputs = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            inputs[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(inputs);
        int M = Integer.parseInt(br.readLine());

        int max = solution(N, M, inputs);
        System.out.println(max);
    }

    private static int solution(int N, int M, int[] inputs) {
        int left = 0;
        int right = inputs[N - 1];
        int max = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(M, mid, inputs)) {
                left = mid + 1;
                max = Math.max(max, mid);
            } else {
                right = mid - 1;
            }
        }

        return max;
    }

    private static boolean check(int M, int mid, int[] inputs) {
        int sum = 0;
        for (int input : inputs) {
            sum += Math.min(input, mid);
        }
        return sum <= M;
    }
}
