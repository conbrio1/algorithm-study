package programmers.힙.디스크_컨트롤러;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        int[][] jobs1 = {{0, 9}, {0, 3}, {0, 6}}; //9
        int[][] jobs2 = {{0, 5}, {2, 10}, {10000, 2}}; //6
        int[][] jobs3 = {{24, 10}, {28, 39}, {43, 20}, {37, 5}, {47, 22}, {20, 47}, {15, 34}, {15, 2}, {35, 43}, {26, 1}}; //72
        System.out.println(solution(jobs3));
    }

    public static int solution(int[][] jobs) {
        PriorityQueue<int[]> requestQueue = new PriorityQueue<>(jobs.length, Comparator.comparing(o -> o[0]));
        PriorityQueue<int[]> executeQueue = new PriorityQueue<>(jobs.length, Comparator.comparing(o -> o[1]));
        for (int[] job : jobs)
            requestQueue.offer(job);

        int time = 0;
        int answer = 0;
        while (!requestQueue.isEmpty() || !executeQueue.isEmpty()) {
            while (!requestQueue.isEmpty() && requestQueue.peek()[0] <= time) {
                executeQueue.offer(requestQueue.poll());
            }
            if (!executeQueue.isEmpty()) {
                int[] temp = executeQueue.poll();
                time += temp[1];
                answer += time - temp[0];
            } else if (!requestQueue.isEmpty()) time = requestQueue.peek()[0];
        }

        return answer / jobs.length;
    }
}
