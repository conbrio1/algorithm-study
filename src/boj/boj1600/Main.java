package boj.boj1600;
// 말이 되고픈 원숭이

// bfs 응용
// https://regularmember.tistory.com/172
// https://j2wooooo.tistory.com/92
// qSize를 안썼을 때는 100%에서 계속 틀림
// qSize를 쓰니 맞았는데 도대체 차이가 뭔지는 모르겠다.
// -->
// 알고보니 qSize가 문제가 아니라 최솟값 구하는 로직에 문제가 있었음...
// 목적지(H-1, W-1) 좌표에 도착했을 때 바로 count를 answer에 삽입하고, return 하는 것 중요!
// 끝까지 계산하다가 실수한 케이스.
// -->
// W = 1, H = 1을 생각을 못한 것 같다.
// 항상 입력값의 범위를 잘 보자!

// 일단 depth(level)별 탐색을 하니 확실히 최단, 최소 경로를 찾을 때 도움이 됨

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int K, W, H;
    static int answer;

    static int[] mdr = { -1, 1, 0, 0 };
    static int[] mdc = { 0, 0, -1, 1 };
    static int[] hdr = { 1, 2, 2, 1, -1, -2, -2, -1 };
    static int[] hdc = { 2, 1, -1, -2, -2, -1, 1, 2 };

    static class Point {
        // r : row, c : column, k : jump count
        int r, c, k;

        public Point(int r, int c, int k) {
            this.r = r;
            this.c = c;
            this.k = k;
        }
    }

    static void solution(char[][] map) {
        Comparator<Point> comparator = (o1, o2) -> 0;
        boolean[][][] visited = new boolean[H][W][K + 1];

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 0));

        while (!queue.isEmpty()) {
            int qSize = queue.size();

            for (int i = 0; i < qSize; i++) {
                Point now = queue.poll();

                if (now.r == H - 1 && now.c == W - 1)
                    return;

                if (now.k < K) {
                    for (int j = 0; j < 8; j++) {
                        int nextR = now.r + hdr[j];
                        int nextC = now.c + hdc[j];

                        if (nextR < 0 || nextC < 0 || nextR >= H || nextC >= W)
                            continue;
                        if (visited[nextR][nextC][now.k + 1] || map[nextR][nextC] == '1')
                            continue;

                        visited[nextR][nextC][now.k + 1] = true;
                        queue.offer(new Point(nextR, nextC, now.k + 1));
                    }
                }

                for (int j = 0; j < 4; j++) {
                    int nextR = now.r + mdr[j];
                    int nextC = now.c + mdc[j];

                    if (nextR < 0 || nextC < 0 || nextR >= H || nextC >= W)
                        continue;
                    if (visited[nextR][nextC][now.k] || map[nextR][nextC] == '1')
                        continue;

                    visited[nextR][nextC][now.k] = true;
                    queue.offer(new Point(nextR, nextC, now.k));
                }
            }
            answer++;
        }
        answer = -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        char[][] map = new char[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        solution(map);
        System.out.println(answer);
    }
}
