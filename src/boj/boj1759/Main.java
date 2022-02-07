package boj.boj1759;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        char[] input = new char[C];
        for (int i = 0; i < C; i++) {
            input[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(input);

        char[] output = new char[L];
        StringBuilder sb = new StringBuilder();
        dfs(input, output, L, C, 0, 0, sb);

        System.out.println(sb.toString());
    }

    private static void dfs(char[] input, char[] output, int L, int C, int start, int count, StringBuilder sb) {
        if (count == L) {
            if(isValid(output)) {
                sb.append(output).append("\n");
            }
            return;
        }

        for (int i = start; i < C; i++) {
            output[count] = input[i];
            dfs(input, output, L, C, i + 1, count + 1, sb);
        }
    }

    private static boolean isValid(char[] output) {
        int vowelCount = 0;
        int consonantCount = 0;
        for (char letter : output) {
            if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u')
                vowelCount++;
            else
                consonantCount++;
        }

        return vowelCount >= 1 && consonantCount >=2;
    }
}
