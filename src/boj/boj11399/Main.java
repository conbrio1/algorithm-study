package boj.boj11399;
// ATM
// 그리디
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] P = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(P);

        int result = 0;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j <= i; j++) {
                sum += P[j];
            }
            result += sum;
        }

        System.out.println(result);
    }
}
