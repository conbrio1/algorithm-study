package programmers.해시.전화번호_목록;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        String[][] phone_book = {
                {"119", "97674223", "1195524421"},
                {"123", "456", "789"},
                {"12", "123", "1235", "567", "88"}
        };

        System.out.println(solution_hash(phone_book[0]));
        System.out.println(solution_hash(phone_book[1]));
        System.out.println(solution_hash(phone_book[2]));
    }

    // 사전 순으로 sort 하는 경우, i 항이 i+1 항의 접두어가 되는지만 비교하면 됨
    // 문제에서 요구하는 것은 '포함'이 아닌 '접두어'인 것을 기억하자
    public static boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                answer = false;
                break;
            }
        }
        return answer;
    }

    // 문제의 의도대로 hashmap 사용
    // phone_book의 길이는 1 이상 1,000,000 이하이며, 각 전화번호의 길이는 1 이상 20 이하 이므로 가능
    // O(n + n*20) ~ O(n)
    public static boolean solution_hash(String[] phone_book) {
        boolean answer = true;
        Map<String, Integer> pMap = new HashMap<>();

        int i = 0;
        for (String p : phone_book) {
            pMap.put(p, i++);
        }

        for (String p : phone_book) {
            for (int j = 0; j < p.length(); j++) {
                if (pMap.containsKey(p.substring(0, j)))
                    return false;
            }
        }
        return true;
    }
}
