package programmers.스택큐.다리를_지나는_트럭;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        int bridge_length1 = 2;	int weight1 = 10;	int[] truck_weights1 = {7,4,5,6};
        int bridge_length2 = 100;	int weight2 = 100;	int[] truck_weights2 = {10};
        int bridge_length3 = 100;	int weight3 = 100;	int[] truck_weights3 = {10,10,10,10,10,10,10,10,10,10};

        System.out.println(solution(bridge_length1, weight1, truck_weights1));
        System.out.println(solution(bridge_length2, weight2, truck_weights2));
        System.out.println(solution(bridge_length3, weight3, truck_weights3));
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> waiting = new LinkedList<>();
        Queue<int[]> crossing = new LinkedList<>();
        Queue<Integer> complete = new LinkedList<>();

        for (int t : truck_weights) {
            waiting.offer(t);
        }

        int time = 0;
        int weightSum = 0;
        while (complete.size() != truck_weights.length) {
            time++;
            if (!crossing.isEmpty() && time - crossing.peek()[1] >= bridge_length) {
                weightSum -= crossing.peek()[0];
                complete.offer(crossing.poll()[0]);
            }
            if (!waiting.isEmpty() && weightSum + waiting.peek() <= weight && crossing.size() + 1 <= bridge_length) {
                weightSum += waiting.peek();
                crossing.offer(new int[]{waiting.poll(), time});
            }
        }

        return time;
    }
}
