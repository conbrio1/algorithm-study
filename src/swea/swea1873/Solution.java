/*
 * 처음에 int[] tankPosition = UP; 하고
 * 매개변수로 넘겨서 tankPosition = direction; 이런 식으로 했다.
 * 그런데 함수 끝나니 tankPosition이 원상복구 돼서 왜 그런지 파악 필요!
 * 결국 System.arrayCopy로 해결하였다.
 */
package swea.swea1873;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
    static char[][] map;
    static char[] userInput;

    static final int[] UP = { -1, 0, '^' };
    static final int[] DOWN = { 1, 0, 'v' };
    static final int[] LEFT = { 0, -1, '<' };
    static final int[] RIGHT = { 0, 1, '>' };

    public static void move(int[] tankPosition, int[] tankHeading, int[] direction) {
        // change tank Heading
        System.arraycopy(direction, 0, tankHeading, 0, 3);

        int nextRow = tankPosition[0] + direction[0];
        int nextCol = tankPosition[1] + direction[1];
        if (nextRow < 0 || nextCol < 0 || nextRow >= map.length || nextCol >= map[0].length)
            return;

        switch (map[nextRow][nextCol]) {
            // move tank position
            case '.':
                tankPosition[0] = nextRow;
                tankPosition[1] = nextCol;
                break;
            default:
                break;
        }
    }

    public static void shoot(int[] tankPosition, int[] tankHeading) {
        int row = tankPosition[0] + tankHeading[0];
        int col = tankPosition[1] + tankHeading[1];
        while (row >= 0 && col >= 0 && row < map.length && col < map[0].length) {
            boolean boom = false;
            switch (map[row][col]) {
                case '*':
                    boom = true;
                    map[row][col] = '.';
                    break;
                case '#':
                    boom = true;
                    break;
                default:
                    break;
            }
            if (boom)
                break;

            row += tankHeading[0];
            col += tankHeading[1];
        }
    }

    public static void solve() {
        int[] tankPosition = new int[2];
        int[] tankHeading = new int[3];

        // find initial tank position
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                boolean found = false;
                switch (map[i][j]) {
                    case '^':
                        found = true;
                        System.arraycopy(UP, 0, tankHeading, 0, 3);
                        break;
                    case 'v':
                        found = true;
                        System.arraycopy(DOWN, 0, tankHeading, 0, 3);
                        break;
                    case '<':
                        found = true;
                        System.arraycopy(LEFT, 0, tankHeading, 0, 3);
                        break;
                    case '>':
                        found = true;
                        System.arraycopy(RIGHT, 0, tankHeading, 0, 3);
                        break;
                    default:
                        break;
                }
                if (found) {
                    map[i][j] = '.';
                    tankPosition[0] = i;
                    tankPosition[1] = j;
                    break;
                }
            }
        }

        // process user input
        for (int i = 0; i < userInput.length; i++) {
            switch (userInput[i]) {
                case 'U':
                    move(tankPosition, tankHeading, UP);
                    break;
                case 'D':
                    move(tankPosition, tankHeading, DOWN);
                    break;
                case 'L':
                    move(tankPosition, tankHeading, LEFT);
                    break;
                case 'R':
                    move(tankPosition, tankHeading, RIGHT);
                    break;
                case 'S':
                    shoot(tankPosition, tankHeading);
                    break;
                default:
                    break;
            }
        }

        // draw tank
        map[tankPosition[0]][tankPosition[1]] = (char)tankHeading[2];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int H = Integer.parseInt(st.nextToken());
            // int W = Integer.parseInt(st.nextToken());

            map = new char[H][];
            for (int j = 0; j < H; j++) {
                map[j] = br.readLine().toCharArray();
            }

            // int N = Integer.parseInt(br.readLine());
            userInput = br.readLine().toCharArray();

            solve();

            sb.append("#" + (i + 1) + " ");
            for (int j = 0; j < H; j++) {
                sb.append(map[j]);
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
    }
}
