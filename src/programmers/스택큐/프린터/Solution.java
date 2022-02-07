package programmers.스택큐.프린터;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        int[] priorities1 = {2, 1, 3, 2};
        int[] priorities2 = {1, 1, 9, 1, 1, 1};
        int location1 = 2;
        int location2 = 0;

        System.out.println(solution(priorities1, location1));
        System.out.println(solution(priorities2, location2));
    }

    static class Document {
        int location;
        int priority;

        public Document(int location, int priority) {
            this.location = location;
            this.priority = priority;
        }
    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Document> queue = new LinkedList<>();
        int[] pArr = new int[10];
        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new Document(i, priorities[i]));
            pArr[priorities[i]]++;
        }

        while (true) {
            Document temp = queue.poll();

            boolean out = true;
            for (int i = temp.priority + 1; i <= 9; i++) {
                if (pArr[i] > 0) {
                    queue.offer(temp);
                    out = false;
                    break;
                }
            }

            if (out) {
                answer++;
                pArr[temp.priority]--;

                if (temp.location == location) {
                    break;
                }
            }
        }

        return answer;
    }
}
