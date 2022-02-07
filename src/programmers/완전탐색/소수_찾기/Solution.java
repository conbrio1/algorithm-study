package programmers.완전탐색.소수_찾기;
// https://programmers.co.kr/learn/courses/30/lessons/42839/solution_groups?language=java
// 위 url 의 첫번째 풀이 참고하여 더 깔끔하게 고칠 수 있으나, 내가 작성한 코드가 더 빠름
// 그래도 substring 활용하여 반복횟수를 줄이는 것은 참고

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        String[] numbers = {"17", "011"};
        Arrays.stream(numbers).forEach(number -> System.out.println(solution(number)));
    }

    public static int solution(String numbers) {
        Set<Integer> primes = new HashSet<>();
        for (int i = 1; i <= numbers.length(); i++) {
            findPrime(numbers, new boolean[numbers.length()], new StringBuilder(), primes, i, 0);
        }
        return primes.size();
    }

    public static void findPrime(String numbers, boolean[] visited, StringBuilder sb, Set<Integer> primes, int limit, int count) {
        if (count == limit) {
            int result = Integer.parseInt(sb.toString());
            if (isPrime(result))
                primes.add(result);
            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (visited[i]) continue;
            visited[i] = true;
            sb.append(numbers.charAt(i));
            findPrime(numbers, visited, sb, primes, limit, count + 1);
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }

    public static boolean isPrime(int num) {
        if (num <= 1) return false;

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }
}
