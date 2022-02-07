package programmers.완전탐색.모의고사;

import java.util.Arrays;
import java.util.LinkedList;

public class Solution {
    static int[][] method = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
    };

    public static void main(String[] args) {
        int[] answers1 = {1, 2, 3, 4, 5};
        int[] answers2 = {1, 3, 2, 4, 2};
        System.out.println(Arrays.toString(solution(answers1)));
        System.out.println(Arrays.toString(solution(answers2)));
    }

    public static int[] solution(int[] answers) {
        int[] score = new int[4];
        int max = 0;
        for (int i = 0; i < 3; i++) {
            score[i + 1] = getCount(answers, i);
            max = Math.max(max, score[i + 1]);
        }
        LinkedList<Integer> answer = new LinkedList<>();

        for (int i = 1; i <= 3; i++) {
            if (score[i] == max)
                answer.offer(i);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int getCount(int[] answers, int type) {
        int count = 0;
        for (int i = 0; i < answers.length; i++) {
            if (method[type][i % method[type].length] == answers[i])
                count++;
        }
        return count;
    }
}
