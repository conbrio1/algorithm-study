package programmers.DFSBFS.여행경로;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        String[][] tickets1 = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] tickets2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};

        System.out.println(Arrays.toString(solution(tickets1)));
        System.out.println(Arrays.toString(solution(tickets2)));
    }

    public static String[] solution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
        String[] temp = new String[tickets.length + 1];
        temp[0] = "ICN";

        boolean[] used = new boolean[tickets.length]; // 한번만 쓰는지 확인 필요
        dfs(tickets, temp, answer, used, 0);
        return answer;
    }

    private static void dfs(String[][] tickets, String[] temp, String[] answer, boolean[] used, int count) {
        if (count == tickets.length) {
            if (check(temp, answer)) {
                System.arraycopy(temp, 0, answer, 0, answer.length);
            }
        }

        for (int i = 0; i < tickets.length; i++) {
            if (temp[count].equals(tickets[i][0]) && !used[i]) {
                temp[count + 1] = tickets[i][1];
                used[i] = true;
                dfs(tickets, temp, answer, used, count + 1);
                used[i] = false;
            }
        }
    }

    private static boolean check(String[] temp, String[] answer) {
        if (answer[0] == null)
            return true;

        for (int i = 0; i < temp.length; i++) {
            int res = temp[i].compareTo(answer[i]);
            if (res < 0)
                return true;
            else if (res > 0)
                return false;
        }
        return false;
    }
}
