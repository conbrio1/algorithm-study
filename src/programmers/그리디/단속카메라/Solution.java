package programmers.그리디.단속카메라;
// https://programmers.co.kr/questions/14636

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) {
        int[][] routes = {{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}, {-3, 2}};
        System.out.println(solution(routes));
    }

    public static int solution(int[][] routes) {
        // 빨리 나가는 순서대로 정렬
        Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));

        int answer = 1;
        int cam = routes[0][1];
        for (int[] route : routes) {
            if (cam >= route[0] && cam <= route[1])
                continue;

            cam = route[1];
            answer++;
        }
        return answer;
    }
}
