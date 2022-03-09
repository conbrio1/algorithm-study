package programmers.동적계획법.N으로_표현;
// DP
// 혼자 못풀었음
// https://small-stap.tistory.com/65
// https://ko.wikipedia.org/wiki/%EB%8F%99%EC%A0%81_%EA%B3%84%ED%9A%8D%EB%B2%95
// N 이 사용된 횟수로 set 을 만들고 set 의 원소끼리 연산시킴
// top-down 에 매몰되지 말 것

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        System.out.println(solution(5, 12));
        System.out.println(solution(2, 11));
    }

    public static int solution(int N, int number) {
        // N 사용횟수 별 set을 가진 arraylist 생성
        ArrayList<Set<Integer>> countSetList = new ArrayList<>(9);
        // initialization
        countSetList.add(new HashSet<>());
        for (int i = 1; i <= 8; i++) {
            countSetList.add(new HashSet<>());
            countSetList.get(i).add(Integer.parseInt(String.valueOf(N).repeat(i))); // 숫자를 그대로 이어붙인 케이스
        }
        // N 이 1개 쓰인 것은 N 혼자 밖에 없음
        for (int i = 2; i <= 8; i++) {
            Set<Integer> countSet = countSetList.get(i);
            for (int j = 1; j < i; j++) {
                // N 이 j 와 i - j 번 쓰인 set 을 연산하여 N 이 i 번 쓰인 결과들을 추가
                Set<Integer> set1 = countSetList.get(j);
                Set<Integer> set2 = countSetList.get(i - j);
                for (int op1 : set1) {
                    for (int op2 : set2) {
                        countSet.add(op1 + op2);
                        countSet.add(op1 - op2);
                        countSet.add(op1 * op2);
                        if (op2 != 0)
                            countSet.add(op1 / op2);
                    }
                }
            }
        }
        // find
        for (Set<Integer> findSet : countSetList) {
            if (findSet.contains(number)) {
                return countSetList.indexOf(findSet);
            }
        }
        return -1;
    }
}
