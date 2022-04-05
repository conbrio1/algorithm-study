package programmers.미분류._124_나라의_숫자;
// 처음에 DP인 줄 알고 DP로 했다가 잘 안됨
// 단순 계산으로 풀 것
// StringBuilder 사용 필요

public class Solution {
    public String solution(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            int mod = (n - 1) % 3;
            n = (n - 1) / 3;

            if (mod == 0)
                mod = 1;
            else if (mod == 1)
                mod = 2;
            else
                mod = 4;

            sb.insert(0, mod);
        }
        return sb.toString();
    }
}
