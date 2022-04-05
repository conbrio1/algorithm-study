package programmers.미분류.야근_지수;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
// priority queue
// 우선순위 큐

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (int work : works) {
            pQueue.offer(work);
        }
        for (int i = 0; i < n; i++) {
            if (!pQueue.isEmpty()) {
                int val = pQueue.poll() - 1;
                if (val != 0)
                    pQueue.offer(val);
            }
        }
        long answer = 0;
        int qSize = pQueue.size();
        for (int i = 0; i < qSize; i++) {
            answer += Math.pow(pQueue.poll(), 2);
        }
        return answer;
    }

    @Test
    void test() {
        Assertions.assertEquals(12, solution(4, new int[]{4, 3, 3}));
    }
}
