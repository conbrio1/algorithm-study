package programmers.그래프.순위;
// 플로이드-워샬과 dfs 로 모두 해볼 것
// https://mungto.tistory.com/58 : 플로이드-워샬(c++) boolean 배열로 구현
// https://easybrother0103.tistory.com/131 : 플로이드-워샬(java) int 배열로 구현
// https://chanhuiseok.github.io/posts/algo-50/ : 플로이드-워샬 알고리즘 설명 (무방향 그래프)
// https://blog.naver.com/ndb796/221234427842 : 플로이드-워샬 알고리즘 설명 (방향 그래프)

import java.util.ArrayList;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        int n = 5;
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        System.out.println(solution2(n, results));
    }

    // dfs 활용
    public static int solution1(int n, int[][] results) {
        // 인접 리스트
        ArrayList<LinkedList<int[]>> adjList = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adjList.add(new LinkedList<>());
        }
        // win : 1, lose : 0
        for (int[] result : results) {
            adjList.get(result[0]).offer(new int[]{result[1], 1});
            adjList.get(result[1]).offer(new int[]{result[0], 0});
        }

        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n + 1];
            dfs(adjList, visited, i, i);
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (adjList.get(i).size() == n - 1) {
                answer++;
            }
        }

        return answer;
    }

    public static void dfs(ArrayList<LinkedList<int[]>> adjList, boolean[] visited, int root, int cur) {
        if (visited[cur])
            return;
        else
            visited[cur] = true;

        LinkedList<int[]> rootAdj = adjList.get(root);
        LinkedList<int[]> curAdj = adjList.get(cur);
        if (root != cur) {
            boolean isContains = false;
            for (int[] edge : rootAdj) {
                if (edge[0] == cur) {
                    isContains = true;
                    break;
                }
            }
            if (!isContains) {
                rootAdj.offer(new int[]{cur, 1});
                curAdj.offer(new int[]{root, 0});
            }
        }

        int adjSize = curAdj.size();
        for (int i = 0; i < adjSize; i++) {
            int[] edge = curAdj.get(i);
            if (edge[1] == 1) {
                dfs(adjList, visited, root, edge[0]);
            }
        }
    }

    // Floyd Warshall 알고리즘 활용
    public static int solution2(int n, int[][] results) {
        boolean[][] adj = new boolean[n + 1][n + 1];
        for (int[] result : results) {
            adj[result[0]][result[1]] = true;
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (adj[i][k] && adj[k][j])
                        adj[i][j] = true;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (adj[i][j] || adj[j][i])
                    count++;
            }
            if (count == n - 1)
                answer++;
        }
        return answer;
    }
}
