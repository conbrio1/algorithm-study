package programmers.이분탐색.징검다리;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;
        System.out.println(solution(distance, rocks, n));
    }
/*
* left ~ right : 0 ~ distance
* mid : 거리의 최솟값
* 기준 : 모든 바위 사이의 거리를 순환하면서 mid 보다 작은 거리가 있다면 count 한다.
* 이때 count가 n보다 작다면 더 많은 바위를 제거해야 하므로 left = mid + 1, 반대의 경우 right = mid - 1
* count를 할 때 n보다 작다면 바위를 제거한다는 의미이므로 거리를 합쳐주는 것을 잊지 말아야 함
*/
    public static int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        int left = 0;
        int right = distance;

        Arrays.sort(rocks);
        int[] adjDist = new int[rocks.length + 1];
        for (int i = 1; i < rocks.length; i++) {
            adjDist[i] = rocks[i] - rocks[i - 1];
        }
        adjDist[0] = rocks[0];
        adjDist[rocks.length] = distance - rocks[rocks.length - 1];

        while (left <= right) {
            int mid = (left + right) / 2;

            int count = getCount(n, adjDist, mid);

            if (count <= n) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private static int getCount(int n, int[] adjDist, int mid) {
        int count = 0;
        int temp = 0;
        for (int dist : adjDist) {
            if (dist + temp < mid) {
                count++;
                temp += dist;
            } else
                temp = 0;

            if (count > n)
                break;
        }
        return count;
    }
}
