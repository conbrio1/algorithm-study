package programmers.그리디.구명보트;
// 문제 제대로 읽자.

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] people1 = {70, 50, 80, 50};
        int[] people2 = {70, 80, 50};
        int limit = 100;
        System.out.println(solution(people1, limit));
        System.out.println(solution(people2, limit));
    }

    public static int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int left = 0;
        int right = people.length - 1;

        while (left < right) {
            if (people[left] + people[right] > limit) {
                right--;
                answer++;
            } else {
                left++;
                right--;
                answer++;
            }
        }

        if (left == right)
            answer++;

        return answer;
    }
}
