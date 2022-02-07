package programmers.힙.더_맵게;

import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        System.out.println(solution(scoville, K));
    }

    public static int solution(int[] scoville, int K) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int s : scoville)
            queue.offer(s);

        int answer = 0;
        while (!queue.isEmpty() && queue.peek() < K) {
            answer++;
            int a = queue.poll();
            int b = 0;
            if (!queue.isEmpty()) b = queue.poll();
            else return -1;
            int c = a + b * 2;
            queue.add(c);
        }

        if (queue.isEmpty() || queue.peek() < K) return -1;

        return answer;
    }
}
