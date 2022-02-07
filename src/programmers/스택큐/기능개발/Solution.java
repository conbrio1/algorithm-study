package programmers.스택큐.기능개발;
// https://programmers.co.kr/learn/courses/30/lessons/42586/solution_groups?language=java
// 첫번째 풀이 스타일도 참고

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        int[] progresses1 = {93, 30, 55};
        int[] speeds1 = {1, 30, 5};
        int[] progresses2 = {95, 90, 99, 99, 80, 99};
        int[] speeds2 = {1, 1, 1, 1, 1, 1};
        System.out.println(Arrays.toString(solution(progresses1, speeds1)));
        System.out.println(Arrays.toString(solution(progresses2, speeds2)));
    }
    public static int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
           queue.offer((int)Math.ceil((100f - progresses[i]) / speeds[i]));
        }
        List<Integer> answerList = new LinkedList<>();
        while (!queue.isEmpty()) {
            int temp = 1;
            int now = queue.poll();
            while (!queue.isEmpty()) {
                if (queue.peek() <= now) {
                    queue.poll();
                    temp++;
                }
                else
                    break;
            }
            answerList.add(temp);
        }
        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }
}
