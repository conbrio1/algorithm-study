package programmers.정렬.HIndex;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        System.out.println(solution(citations));
    }
    public static int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);

        int high = 0;
        int low = citations.length - 1;
        int i = citations.length - 1;
        for (int h = citations[citations.length - 1]; h >= 0; h--) {
            while(i >= 0 && citations[i] >= h) {
                i--;
                high++;
                low--;
            }
            if (high >= h && low <= h)
                answer = Math.max(answer, h);
        }
        return answer;
    }
}
