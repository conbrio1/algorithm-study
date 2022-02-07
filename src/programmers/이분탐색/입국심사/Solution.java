package programmers.이분탐색.입국심사;
// 모든 사람이 각 심사관에게 심사를 받는 경우의 수 중 최소 시간이 아닌
// 각 심사관들이 주어진 시간동안 처리할 수 있는 최대의 사람을 구하는 것이 관건
// line 26 : capacity >= n 이면 어차피 right = mid - 1 이므로 break. (모든 심사관들을 확인하지 않아도 n명 이상 심사 가능)
// line 32 : capacity == n 이더라도 answer 는 더 줄어들 수 있다. (right = mid - 1 이므로 계속 줄어드는 방향으로 갈 것이다.)

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int n = 6;
        int[] times = {7, 10};
        System.out.println(solution(n, times));
    }

    public static long solution(int n, int[] times) {
        long answer = 0;
        long left = 1;
        long right = Arrays.stream(times).max().getAsInt() * (long)n;
        while(left <= right) {
            long mid = (left + right) / 2;
            long capacity = 0;

            for (int time : times) {
                capacity += mid / time;
                if (capacity >= n)
                    break;
            }

            if (capacity < n) {
                left = mid + 1;
            } else {
                answer = mid;
                right = mid - 1;
            }
        }
        return answer;
    }
}
