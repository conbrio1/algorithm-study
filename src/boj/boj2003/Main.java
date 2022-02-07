package boj.boj2003;
// 수들의 합 2

// 투포인터
// TC
// 3 46
// 2 23 23

// 마지막 원소를 제대로 계산하고 빠져나올 수 있도록 전체 배열 길이를 하나 더 크게 잡고,
// sum < M, sum >= M으로 나누는 것이 중요!
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        int sum = 0;
        int count = 0;
        while (right <= N) {
            if (sum < M) {
                sum += A[right++];
            } else {
                sum -= A[left++];
            }

            if (sum == M) {
                count++;
            }
        }

        System.out.println(count);
    }
}

