package boj.boj17298;
// 오큰수
// 스택

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] input = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        int[] answer = solution(input);
        StringBuilder sb = new StringBuilder();
        for(int a : answer)
            sb.append(a).append(" ");
        System.out.println(sb);
    }

    private static int[] solution(int[] input) {
        int[] answer = new int[input.length];
        Stack<Integer> stack = new Stack<>();

        stack.push(-1); // 초기값으로 스택에 -1 가지고 시작
        for (int i = input.length - 1; i >= 0; i--) { // 역방향으로 input 원소 순회
            while (stack.peek() != -1 && stack.peek() <= input[i]) // 스택의 top 값이 input[i]이하라면 pop
                stack.pop();
            answer[i] = stack.peek(); // 스택의 top 값이 input[i]의 오른쪽에서 처음으로 input[i]보다 큰 수가 됨
            stack.push(input[i]); // input[i]를 stack 에 적재
        }

        return answer;
    }
}
