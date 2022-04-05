package programmers.미분류.짝지어_제거하기;
// 스택

import java.util.Stack;

public class Solution {
    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();
        char[] charArr = s.toCharArray();
        for (char ch : charArr) {
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.add(ch);
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }
}
