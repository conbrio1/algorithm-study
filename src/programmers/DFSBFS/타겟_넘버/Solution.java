package programmers.DFSBFS.타겟_넘버;

public class Solution {
    static int answerCount = 0;
    public static void main(String[] args) {
        int[] input = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(solution(input, target));
    }
    static public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answerCount;
    }

    private static void dfs(int[] numbers, int target, int idx, int tempResult) {
        if (idx == numbers.length) {
            if (tempResult == target)
                answerCount++;
            return;
        }

        dfs(numbers, target, idx+1, tempResult + numbers[idx]);
        dfs(numbers, target, idx+1, tempResult - numbers[idx]);
    }
}
