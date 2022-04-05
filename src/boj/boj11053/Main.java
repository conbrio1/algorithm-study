package boj.boj11053;
// LIS
// DP
// https://chanhuiseok.github.io/posts/algo-49/
// https://hongjw1938.tistory.com/58
// https://rebro.kr/33

// DP 를 사용한 풀이는 시간복잡도 O(N^2)
// O(nlogn)의 시간복잡도를 가지는 풀이도 존재 - 이분탐색 활용(lowerBound)
// --> 다만 이 방법의 경우 LIS 의 길이만 구할 뿐, 실제 LIS 수열은 구하지 못한다. --> https://rebro.kr/33 에 방법이 있음

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // System.out.println(solution1(arr, N));
        // System.out.println(solution2(arr, N));
        System.out.println(solution3(arr, N));
    }

    // Top-down
    private static int solution1(int[] arr, int N) {
        // Memoization
        // memo[i]: arr 의 i 번째 index 에서 끝나는 LIS 의 길이
        int[] memo = new int[N];

        int answer = 0;
        // 주어진 arr 의 각 index 까지의 LIS 의 길이를 계산하여 그 최댓값을 구한다.
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, dp(arr, memo, i));
        }
        return answer;
    }

    // Top-down
    private static int dp(int[] arr, int[] memo, int n) {
        // base condition
        if (n == 0)
            return 1;

        // memoization
        // 이미 계산된 결과가 있는 경우
        if (memo[n] != 0)
            return memo[n];

        // solving
        // n 보다 작은 index i 중에서 arr[i] < arr[n]인 i를 구한다.
        // n 번째 index 에서 끝나는 LIS 의 길이는 i 번째 index 에서 끝나는 LIS 의 길이 + 1이 된다.
        int ret = 1;
        for (int i = 0; i < n; i++) {
            if (arr[i] < arr[n]) {
                ret = Math.max(ret, dp(arr, memo, i) + 1);
            }
        }
        return memo[n] = ret;
    }

    // Bottom-up
    private static int solution2(int[] arr, int N) {
        int[] memo = new int[N];
        // memo[i]: i 번째 index 를 마지막으로 하는 LIS 의 길이
        for (int i = 0; i < N; i++) {
            memo[i] = 1;
            // j < i, arr[j] < arr[i] 를 만족한다면,
            // memo[i] = memo[j] + 1로 하여 i를 순차적으로 늘려가며 계산해나간다.
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i])
                    memo[i] = Math.max(memo[i], memo[j] + 1);
            }
        }

        // 계산한 LIS 길이 중 최댓값을 구한다.
        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, memo[i]);
        }
        return answer;
    }

    // nlogn 시간복잡도 방법 - 이분탐색 활용
    private static int solution3(int[] arr, int N) {
        int[] lis = new int[N + 1]; // N 으로 하면 99% 에서 ArrayOutOfIndexException 에러 발생 - 이유는 잘 모르겠음
        int idx = 0;
        for (int element : arr) {
            if (idx == 0)
                lis[idx++] = element;
            else {
                if (lis[idx - 1] < element)
                    lis[idx++] = element;
                else
                    lis[lowerBound(lis, 0, idx - 1, element)] = element;
            }
        }
        return idx;
    }

    // 이분탐색
    // target 이상의 값이 처음으로 나오는 index 반환
    private static int lowerBound(int[] lis, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (lis[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}
