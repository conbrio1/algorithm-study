package programmers.미분류.전력망을_둘로_나누기;
// BFS or Union-Find

// 방법1: 각각의 간선을 제외하고 BFS를 실행해본다.
// 방법2: 각 간선을 제외하고 union-find를 실행해본다. 이 방법은 따로 인접 리스트를 구성할 필요가 없을 뿐더러 실행속도도 더 빠르다.

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int solution(int n, int[][] wires) {
        // 인접 리스트 생성
        ArrayList<LinkedList<Integer>> adjList = new ArrayList<>(n);
        for (int i = 0; i <= n; i++) {
            adjList.add(new LinkedList<>());
        }
        for (int[] wire : wires) {
            adjList.get(wire[0]).add(wire[1]);
            adjList.get(wire[1]).add(wire[0]);
        }

        // 각 간선을 끊고 bfs 하였을 때 몇개의 노드를 탐색 가능한지 조사
        int answer = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            answer = Math.min(answer, Math.abs(n - 2 * bfs(adjList, wire[0], wire[1], n)));
        }

        return answer;
    }

    private int bfs(ArrayList<LinkedList<Integer>> adjList, int nodeA, int nodeB, int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(nodeA);

        boolean[] visited = new boolean[n + 1];
        visited[nodeA] = true;
        visited[nodeB] = true;

        int count = 1;
        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (Integer nNode : adjList.get(now)) {
                if (visited[nNode])
                    continue;

                visited[nNode] = true;
                queue.offer(nNode);
                count++;
            }
        }
        return count;
    }

    @Test
    void test() {
        Assertions.assertEquals(3, solution(9, new int[][]{{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}}));
        Assertions.assertEquals(0, solution(4, new int[][]{{1, 2}, {2, 3}, {3, 4}}));
        Assertions.assertEquals(1, solution(7, new int[][]{{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}}));
    }
}
