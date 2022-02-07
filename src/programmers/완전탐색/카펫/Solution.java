package programmers.완전탐색.카펫;
// https://kbw1101.tistory.com/32
// 처음에 이분탐색으로 될 줄 알고 계속 시도했는데 안됨
// 작아지고 커지는 명확한 기준이 없으면 쓰기 어려울 듯 싶다.

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        int[][] brown_yellow = {
                {10, 2},
                {8, 1},
                {24, 24}
        };
        Arrays.stream(brown_yellow).forEach(arr -> System.out.println(Arrays.toString(solution(arr[0], arr[1]))));
    }

    public static int[] solution(int brown, int yellow) {
        int[] answer = {};
        int total = brown + yellow;

        Set<Integer> heightSet = new HashSet<>();
        for (int i = 1; i * i <= total; i++) {
            if (total % i == 0)
                heightSet.add(i);
        }
        for (int height : heightSet) {
            int width = total / height;
            if (width * 2 + height * 2 - 4 == brown && (width - 2) * (height - 2) == yellow)
                return new int[]{width, height};
        }
        return answer;
    }
}
