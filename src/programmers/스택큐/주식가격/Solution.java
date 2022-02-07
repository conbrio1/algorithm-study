package programmers.스택큐.주식가격;
// https://programmers.co.kr/learn/courses/30/lessons/42584/solution_groups?language=java
// 1, 2 번째 풀이 참조

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] prices1 = {1, 2, 3, 2, 3};
        int[] prices2 = {1, 2, 3, 2, 3, 1};
        System.out.println(Arrays.toString(solution(prices1)));
        System.out.println(Arrays.toString(solution(prices2)));
    }

    private static int[] solution(int[] prices) {
        List<int[]> pList = new ArrayList<>(100000);
        int[] answer = new int[prices.length];

        for (int i = prices.length - 1; i >= 0; i--) {
            pList.add(new int[]{prices[i], i});
            for (int j = pList.size() - 1; j >= 0; j--) {
                if (pList.get(j)[0] < prices[i]) {
                    answer[i] = pList.get(j)[1] - i;
                    break;
                }
            }
            if (answer[i] == 0)
                answer[i] = prices.length - 1 - i;
        }

        return answer;
    }
}
