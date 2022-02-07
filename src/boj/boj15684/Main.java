package boj.boj15684;
// 사다리 조작
// 문제 모델링 관건! + dfs

// https://www.acmicpc.net/source/33461627
// 너무 복잡하게 생각하지 말 것
// 최대한 간략하게 풀 수 있는 방법을 생각하자
// -->
// 처음에는 boj3025의 풀이에 bfs 조합을 붙일 생각만 했다.
// 이렇게 하니 path 등의 instance 데이터들을 매번 초기화하는 overhead 발생
// -->
// 그냥 map에 이어주는 사다리 정보만 기록하여 간단하게 처리함

// 55 line의 if (map[i][j] != 0 || map[i][j + 1] != 0) 에서 map[i][j - 1]을 확인하지 않는 이유는
// map[i][j - 1]이 RIGHT라면 map[i][j]가 LEFT여야 하므로 확인하지 않아도 됨.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H;

    static final int RIGHT = 1;
    static final int LEFT = -1;

    static boolean check(int[][] map) {
        for (int i = 1; i <= N; i++) {
            int col = i;
            for (int j = 1; j <= H; j++) {
                if (map[j][col] == RIGHT)
                    col++;
                else if (map[j][col] == LEFT)
                    col--;
            }
            if (col != i) {
                return false;
            }
        }
        return true;
    }

    static boolean combination(int[][] map, int rIndex, int cIndex, int count, int pick) {
        if (count == pick) {
            if (check(map))
                return true;
            else
                return false;
        }

        for (int i = rIndex; i <= H; i++) {
            for (int j = cIndex; j <= N; j++) {
                if (map[i][j] != 0 || map[i][j + 1] != 0)
                    continue;
                map[i][j] = RIGHT;
                map[i][j + 1] = LEFT;
                if (combination(map, i, j + 1, count + 1, pick))
                    return true;
                map[i][j] = 0;
                map[i][j + 1] = 0;
            }
            cIndex = 1;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        int[][] map = new int[H + 1][N + 2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = RIGHT;
            map[a][b + 1] = LEFT;
        }

        int i = 0;
        for (; i <= 3; i++) {
            if (combination(map, 1, 1, 0, i)) {
                System.out.println(i);
                break;
            }
        }
        if (i == 4)
            System.out.println(-1);
    }
}
