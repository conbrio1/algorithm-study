package programmers.DFSBFS.네트워크;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        int n = 3;
        int[][] computers1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] computers2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};

        System.out.println(solution(n, computers1));
        System.out.println(solution(n, computers2));
    }

    static public int solution(int n, int[][] computers) {
        int answer = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        boolean[] visited = new boolean[computers.length];
        visited[0] = true;

        boolean isEnd = true;
        do {
            answer++;
            while (!queue.isEmpty()) {
                int now = queue.poll();

                for (int i = 0; i < computers[0].length; i++) {
                    if (visited[i] || computers[now][i] == 0)
                        continue;

                    queue.offer(i);
                    visited[i] = true;
                }
            }

            isEnd = true;
            for (int i = 0; i < computers.length; i++) {
                if (!visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                    isEnd = false;
                    break;
                }
            }
        } while(!isEnd);

        return answer;
    }
}
