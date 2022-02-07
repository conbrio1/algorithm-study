package programmers.해시.완주하지_못한_선수;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        String[] participant1 = {"leo", "kiki", "eden"};
        String[] completion1 = {"eden", "kiki"};
        String[] participant2 = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] completion2 = {"josipa", "filipa", "marina", "nikola"};
        String[] participant3 = {"mislav", "stanko", "mislav", "ana"};
        String[] completion3 = {"stanko", "ana", "mislav"};

        System.out.println(solution(participant1, completion1));
        System.out.println(solution(participant2, completion2));
        System.out.println(solution(participant3, completion3));

    }

    public static String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        for (String c : completion) {
            int value = map.getOrDefault(c, 0);
            map.put(c, value + 1);
        }

        for (String p : participant) {
            int value = map.getOrDefault(p, 0);
            if (value == 0) {
                return p;
            } else {
                map.put(p, value - 1);
            }
        }

        return "";
    }
}
