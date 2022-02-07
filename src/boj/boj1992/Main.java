/*
 * 개선 가능한 부분
 * 1. zero, one flag가 아닌 map의 요소들을 더하여 count를 이용하여 할 수 있다.
 * 2. row, column의 start, end가 아닌 x, y, length로 할 수 있다(정사각형이니까)
 */

package boj.boj1992;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void solve(int rStart, int rEnd, int cStart, int cEnd, char[][] map, StringBuilder sb) {
        boolean zero = true;
        boolean one = true;
        for (int r = rStart; r < rEnd; r++) {
            for (int c = cStart; c < cEnd; c++) {
                if (map[r][c] == '0')
                    one = false;
                if (map[r][c] == '1')
                    zero = false;
            }
        }
        if (zero) {
            sb.append('0');
        } else if (one) {
            sb.append('1');
        } else {
            sb.append('(');
            // left up
            solve(rStart, (rStart + rEnd)/2, cStart, (cStart + cEnd)/2 , map, sb);
            // right up
            solve(rStart, (rStart + rEnd)/2, (cStart + cEnd)/2, cEnd, map, sb);
            // left down
            solve((rStart + rEnd)/2, rEnd, cStart, (cStart + cEnd)/2, map, sb);
            // right down
            solve((rStart + rEnd)/2, rEnd, (cStart + cEnd)/2, cEnd, map, sb);
            sb.append(')');
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        solve(0, N, 0, N, map, sb);
        sb.append('\n');

        bw.write(sb.toString());
        bw.close();
    }
}
