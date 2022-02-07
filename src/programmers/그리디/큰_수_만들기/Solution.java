package programmers.그리디.큰_수_만들기;
// https://velog.io/@soo5717/프로그래머스-큰-수-만들기-파이썬
// https://ukyonge.tistory.com/197
// 스택을 이용한 방법 기억해둘 것!
// 첫번째 방법과 같이 빼야하는 수가 아닌 결과로 나와야 하는 수의 길이에 집중하여 비교하는 방법도 참고하자.

public class Solution {
    public static void main(String[] args) {
        String[] number = {
                "1924",
                "1231234",
                "4177252841",
                "2222222"
        };
        int[] k = {2, 3, 4, 1};
        System.out.println(solution2(number[0], k[0]));
        System.out.println(solution2(number[1], k[1]));
        System.out.println(solution2(number[2], k[2]));
        System.out.println(solution2(number[3], k[3]));
    }

    // 1 2 3 4 5 중 2개 제거 -> 5 - 2 = 3, 3자리를 리턴해야 함(len - k) -> idx 0 ~ 2 (len - (len - k)) 까지 비교
    public static String solution1(String number, int k) {
        int idx = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length() - k; i++) { // number.lenth() - k : 답의 길이
            int max = 0;
            for (int j = idx; j <= k + i; j++) { // k + i = number.length() - (number.length() - k) + i
                if (max < number.charAt(j) - '0') {
                    max = number.charAt(j) - '0';
                    idx = j + 1;
                }
            }
            sb.append(max);
        }
        return sb.toString();
    }

    public static String solution2(String number, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            while (sb.length() != 0 && sb.charAt(sb.length() - 1) < number.charAt(i) && k > 0) {
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
}
