package boj.boj1011;
// Fly me to the Alpha Centauri 

// 수학
// https://8iggy.tistory.com/72?category=987804 참고하기
// 수학 문제는 일단 종이에 쭉 써보고 규칙을 파악해야 함

// 1 1
// 121 4 = 1 + 1 + 2
// 12321 9 = 4 + 2 + 3
// 1234321 16 = 9 + 3 + 4
// 123454321 25 = 16 + 4 + 5
// 12345654321 36 = 25 + 5 + 6

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int solution(int curPosition, int targetPosition) {
        int distance = targetPosition - curPosition;
        
        int root = (int)Math.sqrt(distance);
        int pow = (int)Math.pow(root, 2);
        if (distance == pow) {
            return 2 * root - 1;
        }
        else if (distance <= pow + root) {
            return 2 * root;
        }
        else {
            return 2 * root + 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int[][] input = new int[T][2];

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < T; i++) {
            int answer = solution(input[i][0], input[i][1]);
            System.out.println(answer);
        }
    }
}
