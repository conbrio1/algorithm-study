package swea.swea1233;
// 사칙연산 유효성 검사
// https://minhamina.tistory.com/80

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            sb.append("#").append(t).append(" ");
            int answer = 1;
            int num = Integer.parseInt(br.readLine());
            char[] tree = new char[num + 1];
            for (int i = 1; i <= num; i++) {
                String[] temp = br.readLine().split(" ");
                // leaf node 가 아닌데 숫자
                if (temp.length != 2 && temp[1].charAt(0) >= '0' && temp[1].charAt(0) <= '9') {
                    answer = 0;
                }
                // leaf node 인데 연산자
                else if (temp.length == 2 && (temp[1].charAt(0) < '0' || temp[1].charAt(0) > '9')) {
                    answer = 0;
                }
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
