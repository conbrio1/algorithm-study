package programmers.미분류.다단계_칫솔_판매;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, String> parentMap = new HashMap<>();
        Map<String, Integer> earnMap = new HashMap<>();
        // init
        parentMap.put("center", "-"); // 민호
        earnMap.put("center", 0);
        for (int i = 0; i < enroll.length; i++) {
            if (referral[i].equals("-"))
                parentMap.put(enroll[i], "center");
            else
                parentMap.put(enroll[i], referral[i]);

            earnMap.put(enroll[i], 0);
        }
        // compute
        for (int i = 0; i < seller.length; i++) {
            int tip = (int) (100 * amount[i] * 0.1);
            int mine = 100 * amount[i] - tip;
            earnMap.put(seller[i], earnMap.get(seller[i]) + mine);

            String parent = parentMap.get(seller[i]);
            while (!parent.equals("-")) {
                int temp = tip;
                tip = (int) (tip * 0.1);
                mine = temp - tip;
                earnMap.put(parent, earnMap.get(parent) + mine);
                // 가지치기
                if (tip == 0)
                    break;

                parent = parentMap.get(parent);
            }
        }
        // result
        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = earnMap.get(enroll[i]);
        }
        return answer;
    }
}
