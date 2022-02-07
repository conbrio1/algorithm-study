package programmers.그리디.조이스틱;
// 무조건 앞 뒤 한방향으로만 탐색하는 것이 아니라 다시 돌아갈 수도 있어야 한다.
// 가장 긴 연속 A의 시작 index, 끝 index를 알아야한다.
// 가장 긴 연속 A를 구한 후에(같은 길이도 고려하여) linked list 에 집어 넣어 마지막에 비교하였더니 테스트케이스 12번만 계속 틀렸음
// https://hellodavid.tistory.com/4
// 위 url의 풀이는 틀렸지만, 참고하여 그때 그때 가장 탐욕적인 결과를 가져오도록 수정하였다.

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        String[] name = {"JEROEN", "JAN", "BAAABBBBBBBAAAAAA", "BBBBBBAAAABAAAABAAAABBBBBB",
        "ABAAAAAAAAABB", "BBBBAAAAAB","BBBBAAAABA", "AABAAAAABBB"};
        Arrays.stream(name).forEach(x -> System.out.println(solution(x)));
    }

    public static int solution(String name) {
        int answer = 0;
        int minMove = name.length() - 1;
        for (int i = 0; i < name.length(); i++) {
            // 상하
            int order = name.charAt(i) - 'A';
            order = (order > 12) ? 26 - order : order;
            answer += order;

            // 좌우
            int nextIdx = i + 1;
            while (nextIdx < name.length() && name.charAt(nextIdx) == 'A')
                nextIdx++;
            minMove = Math.min(minMove, i * 2 + name.length() - nextIdx);
            minMove = Math.min(minMove, (name.length() - nextIdx) * 2 + i);
        }
        answer += minMove;

        return answer;
    }
}
/*
int answer = 0;

        for (int i = 0; i < name.length(); i++) {
            int order = name.charAt(i) - 'A';
            order = (order > 12) ? 26 - order : order;
            answer += order;
        }
        if (name.length() == 1)
            return answer;

        // find longest 'A's
        LinkedList<int[]> aIdxList = new LinkedList<>();
        int aCount = 0;
        int aMax = 0;
        int aTemp = 0;
        for (int i = 1; i < name.length(); i++) {
            if (name.charAt(i) == 'A') {
                if (aCount == 0)
                    aTemp = i;
                aCount++;
                if (i == name.length() - 1 && aMax < aCount) {
                    aIdxList = new LinkedList<>();
                    aIdxList.add(new int[] {aTemp, i});
                    aMax = aCount;
                } else if (i == name.length() - 1 && aMax == aCount) {
                    aIdxList.add(new int[] {aTemp, i});
                }
            } else {
                if (aCount != 0) {
                    if (aMax < aCount) {
                        aIdxList = new LinkedList<>();
                        aIdxList.add(new int[] {aTemp, i - 1});
                        aMax = aCount;
                    } else if (aMax == aCount) {
                        aIdxList.add(new int[] {aTemp, i - 1});
                    }
                    aCount = 0;
                }
            }
        }

        int minMove = name.length() - 1;
        if (aMax != 0) {
            for (int[] aIdx : aIdxList) {
                minMove = Math.min(minMove, (aIdx[0] - 1) * 2 + name.length() - aIdx[1] - 1);
                minMove = Math.min(minMove, (name.length() - aIdx[1] - 1) * 2 + aIdx[0] - 1);
            }
        }
        answer += minMove;

        return answer;
 */