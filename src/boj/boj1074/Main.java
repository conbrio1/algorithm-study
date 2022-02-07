package boj.boj1074;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int count = 0;
    public static void solve(int N, int r, int c) {
        if (N == 0)
            return;

        int L = (int) Math.pow(2, N) / 2;
        if (r < L && c < L) {
            count += 0;
        } else if (r < L && c >= L) {
            count += L * L;
            c -= L;
        } else if (r >= L && c < L) {
            count += 2 * L * L;
            r -= L;
        } else {
            count += 3 * L * L;
            r -= L;
            c -= L;
        }
        solve(N - 1, r, c);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        solve(N, r, c);

        sb.append(count + "\n");
        bw.write(sb.toString());
        bw.close();
    }
}
