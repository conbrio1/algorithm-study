package programmers.skillcheck.level2.큰_수_만들기;
// 그리디, 스택

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution {
    public String solution(String number, int k) {
        // StringBuilder sb를 스택으로 사용한다.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            // k가 아직 남아있다면 최대한 앞의 숫자를 큰 수로 갈아끼운다.
            while (sb.length() != 0 && k > 0 && sb.charAt(sb.length() - 1) < number.charAt(i)) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            sb.append(number.charAt(i));
        }
        while (k > 0) {
            sb.deleteCharAt(sb.length() - 1);
            k--;
        }
        return sb.toString();
    }

    @Test
    void test() {
        Assertions.assertEquals("94", solution("1924", 2));
        Assertions.assertEquals("3234", solution("1231234", 3));
        Assertions.assertEquals("775841", solution("4177252841", 4));
    }
}
