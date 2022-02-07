package programmers.그래프.가장_먼_노드;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(n, edge));
    }

    public static int solution(int n, int[][] edge) {
        ArrayList<LinkedList<Integer>> vertexList = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            vertexList.add(new LinkedList<>());
        }
        for (int[] e : edge) {
            vertexList.get(e[0]).offer(e[1]);
            vertexList.get(e[1]).offer(e[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1]; // 1 ~ n
        queue.offer(1);
        visited[1] = true;

        int qSize = 0;
        while (!queue.isEmpty()) {
            qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                int curNode = queue.poll();
                for (int node : vertexList.get(curNode)) {
                    if (visited[node])
                        continue;
                    queue.offer(node);
                    visited[node] = true;
                }
            }
        }

        return qSize;
    }
}
