package boj.boj1654;
// 랜선 자르기

// 이분 탐색
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean canCut(long[] lines, long cutLength, int N) {
        int count = N;

        for (int i = 0; i < lines.length; i++) {
            count -= lines[i] / cutLength;
        }

        return count <= 0;
    }

    static long solution(long[] lines, int N) {
        long maxLength = 0;
        long left = 0;
        long right = lines[lines.length - 1]; // 제일 긴 랜선을 기준으로 랜선의 최대길이를 이분 탐색

        while (left <= right) {
            long cutLength = (left + right) / 2;
            if (cutLength == 0)
                cutLength = 1;
            if (canCut(lines, cutLength, N)) {
                maxLength = Math.max(maxLength, cutLength);
                left = cutLength + 1; // 더 긴 길이로 시도
            } else {
                right = cutLength - 1; // 더 짧은 길이로 시도
            }
        }

        return maxLength;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] lines = new long[K];
        for (int i = 0; i < K; i++) {
            lines[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(lines);

        System.out.println(solution(lines, N));
    }
}
