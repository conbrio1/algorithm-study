package programmers.힙.이중우선순위큐;
// https://readystory.tistory.com/144
// https://loosie.tistory.com/314

import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        String[] operations1 = {"I 16","D 1"};
        String[] operations2 = {"I 7","I 5","I -5","D -1"};
        System.out.println(Arrays.toString(solution(operations1)));
        System.out.println(Arrays.toString(solution(operations2)));
    }

    public static int[] solution(String[] operations) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (String operation : operations) {
            StringTokenizer st = new StringTokenizer(operation, " ");
            String operator = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            if (operator.equals("I")) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            } else {
                if (map.isEmpty())
                    continue;

                int key = (num == 1) ? map.lastKey() : map.firstKey();
                int val = map.get(key);

                if (val == 1)
                    map.remove(key);
                else
                    map.put(key, val - 1);
            }
        }

        if (map.isEmpty())
            return new int[]{0, 0};
        else
            return new int[]{map.lastKey(), map.firstKey()};
    }
}
