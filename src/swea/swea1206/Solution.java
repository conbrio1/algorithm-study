package swea.swea1206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static int solve(int[] aparts) {
        int sum = 0;
        int left, right;
        for(int i = 2; i < aparts.length - 2; i++) {
            if(aparts[i] == 0)
                continue;

            if(aparts[i] > aparts[i-1] && aparts[i] > aparts[i-2]) {
                left = (aparts[i-1] > aparts[i-2]) ? aparts[i-1] : aparts[i-2];

                if(aparts[i] > aparts[i+1] && aparts[i] > aparts[i+2]) {
                    right = (aparts[i+1] > aparts[i+2]) ? aparts[i+1] : aparts[i+2];
                }
                else
                    continue;

                sum += aparts[i] - ((left > right) ? left : right);
            }
        }
        return sum;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 10; i++) {
            sb.append("#").append(i+1).append(" ");

            int T = Integer.parseInt(in.readLine());
            int[] aparts = new int[T+4];

            StringTokenizer st = new StringTokenizer(in.readLine(), " ");

            for(int j = 0; j < T; j++) {
                aparts[j+2] = Integer.parseInt(st.nextToken());
            }

            int result = solve(aparts);
            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());
    }
}
