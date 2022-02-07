package programmers.해시.위장;
// https://programmers.co.kr/learn/courses/30/lessons/42578/solution_groups?language=java
// 위 url 의 위에서 2번째 풀이 참고
// 부분집합으로 안풀고 위처럼 간단히 풀 수도 있다.

import java.util.HashMap;
import java.util.Map;

public class Solution {
    static int count = -1; // 공집합

    public static void main(String[] args) {
        String[][] clothes1 = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
        String[][] clothes2 = {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}};

        System.out.println(solution(clothes1));
        count = -1;
        System.out.println(solution(clothes2));
    }

    public static int solution(String[][] clothes) {
        Map<String, Integer> cMap = new HashMap<>();
        for (String[] c : clothes) {
            int v = cMap.getOrDefault(c[1], 0);
            cMap.put(c[1], v + 1);
        }

        powerSet(cMap.values().toArray(new Integer[0]), 0, 1);
        return count;
    }

    private static void powerSet(Integer[] valArr, int index, int sum) {
        if (index == valArr.length) {
            count += sum;
            return;
        }

        powerSet(valArr, index + 1, sum * valArr[index]);
        powerSet(valArr, index + 1, sum);
    }
}
