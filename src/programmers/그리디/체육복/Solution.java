package programmers.그리디.체육복;
// reserve를 정렬시켜서 중간에 채워지지 못하는 부분 없도록 하는 것 중요

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int n1 = 5;
        int[] lost1 = {2, 4};
        int[] reserve1 = {1, 3, 5};
        int n2 = 5;
        int[] lost2 = {2, 4};
        int[] reserve2 = {3};
        int n3 = 3;
        int[] lost3 = {3};
        int[] reserve3 = {1};
        int n4 = 6;
        int[] lost4 = {6, 2, 4};
        int[] reserve4 = {1, 5, 3};
        System.out.println(solution(n1, lost1, reserve1)); // 5
        System.out.println(solution(n2, lost2, reserve2)); // 4
        System.out.println(solution(n3, lost3, reserve3)); // 2
        System.out.println(solution(n4, lost4, reserve4)); // 6
    }

    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] students = new int[n + 1];
        Arrays.fill(students, 1);
        Arrays.sort(reserve);
        for (int l : lost) {
            students[l] -= 1;
        }
        for (int r : reserve) {
            students[r] += 1;
        }
        for (int r : reserve) {
            if (students[r] == 2) {
                if (r - 1 >= 1 && students[r - 1] == 0)
                    students[r - 1] += 1;
                else if (r + 1 <= n && students[r + 1] == 0)
                    students[r + 1] += 1;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (students[i] >= 1)
                answer++;
        }
        return answer;
    }
}
