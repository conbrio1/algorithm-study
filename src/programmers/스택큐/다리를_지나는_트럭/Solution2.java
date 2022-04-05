package programmers.스택큐.다리를_지나는_트럭;
// 큐 활용

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int totalTime = 0;
        Queue<Integer> waitingQueue = new LinkedList<>();
        Queue<int[]> crossingQueue = new LinkedList<>();
        for (int truck : truck_weights) {
            waitingQueue.offer(truck);
        }
        int totalWeight = 0;
        while (!waitingQueue.isEmpty() || !crossingQueue.isEmpty()) {
            totalTime++;
            // 다리에서 트럭 빼기
            if (!crossingQueue.isEmpty() && crossingQueue.peek()[1] == bridge_length) {
                totalWeight -= crossingQueue.peek()[0];
                crossingQueue.poll();
            }
            // 트럭 전진
            for (int[] truck : crossingQueue) {
                truck[1]++;
            }
            // 다리에 트럭 올라가기
            if (crossingQueue.size() < bridge_length && !waitingQueue.isEmpty() && totalWeight + waitingQueue.peek() <= weight) {
                totalWeight += waitingQueue.peek();
                crossingQueue.offer(new int[]{waitingQueue.poll(), 1});
            }
        }
        return totalTime;
    }

    @Test
    void test() {
        Assertions.assertEquals(8, solution(2, 10, new int[]{7, 4, 5, 6}));
        Assertions.assertEquals(101, solution(100, 100, new int[]{10}));
        Assertions.assertEquals(110, solution(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}));
    }
}
