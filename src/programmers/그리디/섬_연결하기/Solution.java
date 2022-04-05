package programmers.그리디.섬_연결하기;
// https://ongveloper.tistory.com/376
// Kruskal + Union-Find 알고리즘 사용
// 그래프 + 그리디
// MST

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
        System.out.println(solution(n, costs));
    }

    public static int solution(int n, int[][] costs) {
        int answer = 0;
        Arrays.sort(costs, Comparator.comparingInt(o -> o[2]));
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] edge : costs) {
            if (!isCycling(edge[0], edge[1], parent)) {
                unionParent(edge[0], edge[1], parent);
                answer += edge[2];
            }
        }
        return answer;
    }

    // find
    public static int findParent(int node, int[] parent) {
        if (parent[node] == node)
            return node;
        else
            return parent[node] = findParent(parent[node], parent);
    }

    // union
    public static void unionParent(int a, int b, int[] parent) {
        a = findParent(a, parent);
        b = findParent(b, parent);

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static boolean isCycling(int a, int b, int[] parent) {
        a = findParent(a, parent);
        b = findParent(b, parent);

        return a == b;
    }
}
