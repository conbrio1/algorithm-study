package programmers.동적계획법.도둑질;
// DP
// 첫번째 원소 포함, 마지막 원소 미포함 / 첫번째 원소 미포함, 마지막 원소 포함 으로 나누어 둘의 값을 비교하자.

// 본 문제 효율성 테스트는 bottom-up 방식만 통과하였다.
// 추가로 왜 인지는 모르겠는데 int[][] mem = new int[2][money.length] 로 하니 시간이 오래 걸려 효율성 테스트 통과 못했다.
// 어차피 메서드 부분은 똑같은데 시간이 오래 걸리는 것은 이상하다.

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Solution {
    public int solution(int[] money) {
        int[] mem = new int[money.length];

        // initialization : 첫번쨰 원소 포함
        Arrays.fill(mem, -1);
        mem[0] = money[0];
        mem[1] = Math.max(money[0], money[1]);
        int withFist = bottomUp(money, mem, 0, money.length - 2); // 첫번째 원소 포함, 마지막 원소 미포함

        // initialization : 첫번째 원소 미포함
        Arrays.fill(mem, -1);
        mem[1] = money[1];
        mem[2] = Math.max(money[1], money[2]);
        int withLast = bottomUp(money, mem, 1, money.length - 1); // 첫번째 원소 미포함, 마지막 원소 포함

        return Math.max(withFist, withLast);
    }

    // top-down DP
    private int topDown(int[] money, int[] mem, int fromIndex, int toIndex) {
        // base case
        if (toIndex <= fromIndex + 1)
            return mem[toIndex];

        // solving
        if (mem[toIndex] != -1)
            return mem[toIndex];

        return mem[toIndex] = Math.max(topDown(money, mem, fromIndex, toIndex - 2) + money[toIndex],
                topDown(money, mem, fromIndex, toIndex - 1));
    }

    // bottom-up DP
    private int bottomUp(int[] money, int[] mem, int fromIndex, int toIndex) {
        for (int i = fromIndex + 2; i <= toIndex; i++) {
            mem[i] = Math.max(mem[i - 2] + money[i], mem[i - 1]);
        }
        return mem[toIndex];
    }

    @Test
    void test() {
        Assertions.assertEquals(4, solution(new int[]{1, 2, 3, 1}));
    }
}
