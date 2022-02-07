package boj.boj2473;
// 세 용액

// https://velog.io/@adorno10/%ED%88%AC-%ED%8F%AC%EC%9D%B8%ED%84%B0-Two-Pointer
// https://velog.io/@woo0_hooo/BOJ-2473-세-용액
// 투 포인터의 응용
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 세 용액
public class Main {
    static long min = Long.MAX_VALUE;
    static long[] res = new long[3];

    static void solution(long[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            long pivot = arr[i];
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                long sum = pivot + arr[left] + arr[right];
                if (min > Math.abs(sum)) {
                    min = Math.abs(sum);
                    res[0] = pivot;
                    res[1] = arr[left];
                    res[2] = arr[right];
                }
                if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        solution(arr);
        System.out.println(res[0] + " " + res[1] + " " + res[2]);
    }
}
