package programmers.미분류._124_나라의_숫자;

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
