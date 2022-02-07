package boj.boj2295;
// 세수의 합

// 이분탐색
// a[i] + a[j] + a[k] = a[l] 을 만족하는 a[l]의 최댓값을 구해야 함
// https://blog.encrypted.gg/985 참고
// 1. O(N^4) -> 그냥 4중 for문
// 2. O(N^3 * logN) -> a[i] + a[j] + a[k] 3중 for문으로 배열 구성하여 이를 만족하는 a[l]을 이분탐색
// 3. O(N^2 * logN) -> a[i] + a[j] 2중 for문으로 배열화하여 이 중 a[l] - a[k]가 존재하는지 이분탐색

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] input = new int[N];
        for (int n = 0; n < N; n++) {
            input[n] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(input);

        int[] firstSecond = new int[N * N]; // input[i] + input[j]
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                firstSecond[count++] = input[i] + input[j];
            }
        }
        Arrays.sort(firstSecond, 0, count - 1);

        for (int l = N - 1; l >= 0; l--) {
            for (int k = l; k >= 0; k--) {
                if (Arrays.binarySearch(firstSecond, 0, count - 1, input[l] - input[k]) >= 0) {
                    System.out.println(input[l]);
                    return;
                }
            }
        }
    }
}
