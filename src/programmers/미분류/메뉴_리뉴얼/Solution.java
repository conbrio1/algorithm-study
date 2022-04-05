package programmers.미분류.메뉴_리뉴얼;
// 메뉴 리뉴얼

// 각 입력 문자열마다 주어진 course 수만큼 고르도록 조합을 실행한 후,
// key를 조합된 문자열, value를 해당 조합이 만들어진 횟수로 Map으로 저장
// 저장된 map을 차례대로 출력

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Solution {
    public String[] solution(String[] orders, int[] course) {
        ArrayList<SortedMap<String, Integer>> mapList = new ArrayList<>();
        for (int i = 0; i < course.length; i++) {
            mapList.add(new TreeMap<>());
        }

        for (String order : orders) {
            for (int i = 0; i < course.length; i++) {
                if (order.length() >= course[i]) {
                    StringBuilder sb = new StringBuilder();
                    // 사전순으로 재정렬 해준다.
                    char[] charArr = order.toCharArray();
                    Arrays.sort(charArr);
                    makeCombi(String.valueOf(charArr), mapList.get(i), sb, 0, 0, course[i]);
                }
            }
        }

        LinkedList<String> answerList = new LinkedList<>();
        for (int i = 0; i < course.length; i++) {
            int maxVal = 0;
            LinkedList<String> tempList = new LinkedList<>();
            while (!mapList.get(i).isEmpty()) {
                String key = mapList.get(i).firstKey();
                int val = mapList.get(i).remove(mapList.get(i).firstKey());
                if (val < 2) continue;

                if (val > maxVal) {
                    tempList.clear();
                    tempList.offer(key);
                    maxVal = val;
                } else if (val == maxVal) {
                    tempList.offer(key);
                }
            }
            for (String key : tempList) {
                answerList.offer(key);
            }
        }
        Collections.sort(answerList);
        return answerList.toArray(new String[0]);
    }

    private void makeCombi(String order, SortedMap<String, Integer> map, StringBuilder sb, int index, int count, int target) {
        if (count == target) {
            // 조합이 완성되면 key는 조합된 문자열로, value는 해당 문자열이 출현한 횟수로 한다.
            Integer val = map.getOrDefault(sb.toString(), 0);
            map.put(sb.toString(), val + 1);
            return;
        }

        for (int i = index; i < order.length(); i++) {
            sb.append(order.charAt(i));
            makeCombi(order, map, sb, i + 1, count + 1, target);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    @Test
    void test() {
        Assertions.assertArrayEquals(new String[]{"AC", "ACDE", "BCFG", "CDE"}, solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4}));
        Assertions.assertArrayEquals(new String[]{"ACD", "AD", "ADE", "CD", "XYZ"}, solution(new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[]{2, 3, 5}));
        Assertions.assertArrayEquals(new String[]{"WX", "XY"}, solution(new String[]{"XYZ", "XWY", "WXA"}, new int[]{2, 3, 4}));
    }
}
