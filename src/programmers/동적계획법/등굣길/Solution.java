package programmers.동적계획법.등굣길;
// DP
// 나머지 연산을 요구하는 문제는 단순히 결과에만 하는 것이 아니라 연산 과정에서 적용해야 한다는 것을 기억하자.

public class Solution {
    public static void main(String[] args) {
        System.out.println(solution(4, 3, new int[][]{{2, 2}}));
    }

    public static int solution(int m, int n, int[][] puddles) {
        int[][] mem = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1)
                    mem[i][j] = 1;
                else
                    mem[i][j] = (mem[i - 1][j] + mem[i][j - 1]) % 1000000007;
                for (int[] puddle : puddles) {
                    if (i == puddle[0] && j == puddle[1]) {
                        mem[i][j] = 0;
                        break;
                    }
                }
            }
        }

        return mem[m][n] % 1000000007;
    }
}
