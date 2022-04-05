package programmers.미분류.전력망을_둘로_나누기;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnionFind {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        // 간선 하나씩 제외하여 이어보고, 각 경우마다 몇개가 되는지 확인
        for (int[] wireExcluded : wires) {
            // initialization
            int[] parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            // union통해 각 간선들 이어 붙이기
            for (int[] wire : wires) {
                // 간선 하나 제외
                if (wireExcluded == wire)
                    continue;
                union(wire[0], wire[1], parent);
            }

            // 몇 개인지 확인
            int count = 0;
            for (int i = 1; i <= n; i++) {
                // find를 한번 더 실행하여 parent가 누구인지 path compression 확실히 한다.
                parent[i] = find(i, parent);
                if (parent[i] == 1)
                    count++;
            }
            answer = Math.min(answer, Math.abs(n - 2 * count));
        }

        return answer;
    }

    private boolean union(int a, int b, int[] parent) {
        int aRoot = find(a, parent);
        int bRoot = find(b, parent);

        if (aRoot == bRoot) {
            return false;
        }

        if (aRoot < bRoot)
            parent[bRoot] = aRoot;
        else
            parent[aRoot] = bRoot;

        return true;
    }

    private int find(int node, int[] parent) {
        if (parent[node] == node)
            return node;
        else
            return parent[node] = find(parent[node], parent);
    }

    @Test
    void test() {
        Assertions.assertEquals(3, solution(9, new int[][]{{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}}));
        Assertions.assertEquals(0, solution(4, new int[][]{{1, 2}, {2, 3}, {3, 4}}));
        Assertions.assertEquals(1, solution(7, new int[][]{{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}}));
    }
}
