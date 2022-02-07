package programmers.정렬.가장_큰_수;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] numbers1 = {6, 10, 2};
        int[] numbers2 = {3, 30, 34, 5, 9};
        System.out.println(solution(numbers1));
        System.out.println(solution(numbers2));
    }

    public static String solution(int[] numbers) {
        String[] strings = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strings[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(strings, (o1, o2) -> (o2 + o1).compareTo(o1 + o2)); // 앞 뒤로 붙여서 어떤 경우가 큰지 string 을 비교
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
        }
        if (strings[0].equals("0")) // 입력값이 모두 0인 경우
            return "0";
        return sb.toString();
    }
}
