package boj.boj15652;
// N과 M(4)

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

        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = i + 1;
        }

        StringBuilder sb = new StringBuilder();
        int[] output = new int[M];
        dfs(nums, output, M, 0, 0, sb);

        System.out.println(sb.toString());
    }

    private static void dfs(int[] nums, int[] output, int M, int start, int count, StringBuilder sb) {
        if (count == M) {
            for (int num : output)
                sb.append(num).append(' ');
            sb.deleteCharAt(sb.length() - 1).append('\n');
            return;
        }

        for (int i = start; i < nums.length; i++) {
            output[count] = nums[i];
            dfs(nums, output, M, i, count + 1, sb);
        }
    }
}
