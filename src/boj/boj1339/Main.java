package boj.boj1339;
// 단어 수학

// 계수를 모두 합하여 정렬 & 비교
// https://stackoverflow.com/questions/53325650/what-is-the-type-of-map-entry-comparingbyvalue-reversed
// https://codechacha.com/ko/java-sort-map/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < N; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < temp.length; j++) {
                int cf = (int) Math.pow(10, temp.length - j - 1);
                if (map.containsKey(temp[j])) {
                    map.replace(temp[j], map.get(temp[j]) + cf);
                } else {
                    map.put(temp[j], cf);
                }
            }
        }
        LinkedList<Map.Entry<Character, Integer>> entries = new LinkedList<>(map.entrySet());
        entries.sort(Map.Entry.<Character, Integer>comparingByValue().reversed());

        int result = 0;
        for (int i = 0; i < entries.size(); i++) {
            result += (9 - i) * entries.get(i).getValue();
        }
        System.out.println(result);
    }
}
