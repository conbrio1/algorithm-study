package programmers.동적계획법.정수_삼각형;
// DP
// i --> i or i + 1
// cache[l][i] = max(cache[l+1][i], cache[l+1][i+1]) + triangle[l][i]
// 전체 삼각형의 각각의 원소들의 자기 자신의 sub 삼각형을 가진다고 생각하고 품

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(solution(triangle));
    }

    public static int solution(int[][] triangle) {
        // initialization
        int[][] mem = new int[triangle.length][];
        for (int i = 0; i < triangle.length; i++) {
            mem[i] = new int[triangle[i].length];
            Arrays.fill(mem[i], -1);
        }
        System.arraycopy(triangle[triangle.length - 1], 0, mem[triangle.length - 1], 0, triangle[triangle.length - 1].length);

        // top-down approach
        return DP(0, 0, mem, triangle);
    }

    private static int DP(int level, int index, int[][] mem, int[][] triangle) {
        // base case
        if (level == mem.length - 1) {
            return mem[mem.length - 1][index];
        }

        // solving
        if (mem[level][index] != -1)
            return mem[level][index];

        return mem[level][index] = Math.max(DP(level + 1, index, mem, triangle), DP(level + 1, index + 1, mem, triangle)) + triangle[level][index];
    }
}
