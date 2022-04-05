package boj.boj1197;
// 최소 스패닝 트리 MST
// 그리디
// Prim 알고리즘 풀이
// 방향 가중치 그래프가 아닌 무향 가중치 그래프였다.

// https://data-make.tistory.com/519

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 인접 리스트 사용
        ArrayList<LinkedList<int[]>> adjList = new ArrayList<>(V + 1);
        for (int i = 0; i <= V; i++) {
            adjList.add(new LinkedList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList.get(a).add(new int[]{b, w});
            adjList.get(b).add(new int[]{a, w});
        }

        System.out.println(prim(adjList, V));
    }

    public static long prim(ArrayList<LinkedList<int[]>> adjList, int V) {
        // initialization
        PriorityQueue<int[]> pQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        boolean[] visited = new boolean[V + 1];
        pQueue.offer(new int[]{1, 0});

        long weightSum = 0;
        int nodeCount = 0;
        while (!pQueue.isEmpty()) {
            int[] now = pQueue.poll();

            // PriorityQueue 를 사용하여 Prim 알고리즘을 풀 때,
            // queue 에서 node 를 꺼낼 때 어느 node 를 선택할 지가 결정되므로 주의
            if (visited[now[0]])
                continue;
            visited[now[0]] = true;
            weightSum += now[1];
            if(++nodeCount == V)
                return weightSum;

            // 다음으로 선택 가능한 node 들을 priority queue 에 저장
            for (int[] edge : adjList.get(now[0])) {
                if (visited[edge[0]])
                    continue;
                pQueue.offer(new int[]{edge[0], edge[1]});
            }
        }
        return weightSum;
    }
}
