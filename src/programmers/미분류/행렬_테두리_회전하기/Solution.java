package programmers.미분류.행렬_테두리_회전하기;
// https://programmers.co.kr/learn/courses/30/lessons/77485/solution_groups?language=java
// 위 url 의 첫번째 풀이를 참고해보자

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] map = new int[rows + 1][columns + 1];
        int[] answer = new int[queries.length];
        int idx = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                map[i][j] = (i - 1) * columns + j;
            }
        }

        for (int[] query : queries) {
            int min = map[query[0]][query[1]];
            int temp = map[query[0]][query[3]];
            for (int c = query[3] - 1; c >= query[1]; c--) { // 위
                map[query[0]][c + 1] = map[query[0]][c];
                min = Math.min(min, map[query[0]][c]);
            }
            for (int r = query[0] + 1; r <= query[2]; r++) { // 왼쪽
                map[r - 1][query[1]] = map[r][query[1]];
                min = Math.min(min, map[r][query[1]]);
            }
            for (int c = query[1] + 1; c <= query[3]; c++) { // 아래
                map[query[2]][c - 1] = map[query[2]][c];
                min = Math.min(min, map[query[2]][c]);
            }
            for (int r = query[2] - 1; r >= query[0] + 1; r--) { // 오른쪽
                map[r + 1][query[3]] = map[r][query[3]];
                min = Math.min(min, map[r][query[3]]);
            }
            map[query[0] + 1][query[3]] = temp;
            min = Math.min(min, temp);
            answer[idx++] = min;
        }
        
        return answer;
    }

    @Test
    void test() {
        Assertions.assertArrayEquals(new int[]{8, 10, 25}, solution(6, 6, new int[][]{{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}}));
        Assertions.assertArrayEquals(new int[]{1, 1, 5, 3}, solution(3, 3, new int[][]{{1,1,2,2}, {1,2,2,3}, {2,1,3,2}, {2,2,3,3}}));
        Assertions.assertArrayEquals(new int[]{1}, solution(100, 97, new int[][]{{1, 1, 100, 97}}));
    }
}