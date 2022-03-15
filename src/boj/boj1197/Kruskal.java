package boj.boj1197;
// 최소 스패닝 트리 MST
// 그리디
// Kruskal 알고리즘 풀이

// https://velog.io/@fldfls/최소-신장-트리-MST-크루스칼-프림-알고리즘
// https://data-make.tistory.com/519
// https://m.blog.naver.com/parkhj2416/221861837543

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Kruskal {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // Kruskal 알고리즘은 인접 리스트가 아닌 그냥 간선 리스트를 쭉 나열하여 사용
        LinkedList<int[]> edgeList = new LinkedList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edgeList.offer(new int[]{a, b, w});
        }

        System.out.println(kruskal(edgeList, V));
    }

    public static int kruskal(LinkedList<int[]> edgeList, int V) {
        // initialization
        edgeList.sort(Comparator.comparingInt(o -> o[2]));
        int[] parent = new int[V + 1];
        for (int i = 1; i <= V; i++)
            parent[i] = i;

        // 가중치 오름차순으로 탐색
        int weightSum = 0;
        int nodeCount = 0;
        for (int[] edge : edgeList) { // 간선들을 이어보면서
            if (union(edge[0], edge[1], parent)) { // cycle 형성하지 않는다면
                weightSum += edge[2]; // 해당 간선 사용
                if (++nodeCount == V - 1) // MST 완성
                    return weightSum;
            }
        }

        return weightSum;
    }

    public static boolean union(int a, int b, int[] parent) {
        int aRoot = find(a, parent);
        int bRoot = find(b, parent);

        if (aRoot == bRoot)
            return false; // cycling

        if (aRoot < bRoot)
            parent[bRoot] = aRoot;
        else
            parent[aRoot] = bRoot;
        return true;
    }

    private static int find(int node, int[] parent) {
        if (parent[node] == node)
            return node;
        else
            return parent[node] = find(parent[node], parent); // path compression
    }
}
