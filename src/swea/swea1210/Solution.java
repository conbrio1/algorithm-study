package swea.swea1210;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < 10; t++) {
            int T = Integer.parseInt(br.readLine());
            sb.append("#").append(T).append(" ");

            int[][] map = new int[100][100];
            int end = 0;
            for (int i = 0; i < 100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 100; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (i == 99 && map[i][j] == 2)
                        end = j;
                }
            }
            sb.append(solution(map, end)).append("\n");
        }
        System.out.println(sb);
    }

    private static int solution(int[][] map, int end) {
        int row = 98;
        int col = end;
        while (row > 0) {
            if (col + 1 <= 99 && map[row][col + 1] == 1) {
                while (col + 1 <= 99 && map[row][col + 1] == 1)
                    col++;
                row--;
            } else if (col - 1 >= 0 && map[row][col - 1] == 1) {
                while (col - 1 >= 0 && map[row][col - 1] == 1)
                    col--;
                row--;
            } else
                row--;
        }
        return col;
    }
}
