package programmers.DFSBFS.단어_변환;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words1 = {"hot", "dot", "dog", "lot", "log", "cog"};
        String[] words2 = {"hot", "dot", "dog", "lot", "log"};

        System.out.println(solution(begin, target, words1));
        System.out.println(solution(begin, target, words2));
    }

    public static int solution(String begin, String target, String[] words) {
        int answer = 0;

        boolean able = false;
        for (String word : words) {
            if (target.equals(word)) {
                able = true;
                break;
            }
        }
        if (!able) return 0;

        Queue<String> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        queue.offer(begin);

        do {
            int qSize = queue.size();
            for (int s = 0; s < qSize; s++) {
                String now = queue.poll();
                if (now.equals(target))
                    return answer;

                for (int i = 0; i < words.length; i++) {
                    if (visited[i])
                        continue;
                    if (!check(now, words[i]))
                        continue;

                    queue.offer(words[i]);
                    visited[i] = true;
                }
            }
            answer++;
        } while (!queue.isEmpty());

        return 0;
    }

    private static boolean check(String from, String to) {
        char[] arr1 = from.toCharArray();
        char[] arr2 = to.toCharArray();
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                count++;
                if (count >= 2)
                    break;
            }
        }
        return count == 1;
    }
}
