package boj.boj1541;
// 잃어버린 괄호

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] numString = input.split("[+-]");
        List<String> oper = new LinkedList<>(List.of(input.split("[^+-]+")));

        List<Integer> num = new LinkedList<>();
        for (String s : numString) {
            num.add(Integer.parseInt(s));
        }

        for (int i = 1; i < oper.size(); i++) {
            if (oper.get(i).equals("+")) {
                num.set(i-1, num.get(i - 1) + num.get(i));
                num.remove(i);
                oper.remove(i);
                i--;
            }
        }

        int answer = num.get(0);
        if (num.size() > 1) {
            for (int i = 1; i < num.size(); i++) {
                answer -= num.get(i);
            }
        }
        System.out.println(answer);
    }
}
