package programmers.해시.베스트앨범;
// java stream 연습용 문제

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop" };
        int[] plays = {500, 600, 150, 800, 2500};

        System.out.println(Arrays.toString(solution(genres, plays)));
    }

    public static int[] solution(String[] genres, int[] plays) {
        Map<String, Genre> gMap = new HashMap<>();
        int i = 0;
        for (String g : genres) {
            Genre genre = gMap.getOrDefault(g, new Genre(g));
            genre.add(i, plays[i]);
            gMap.put(g, genre);
            i++;
        }

        List<Genre> sortedGenres = gMap.values().stream().sorted((g1, g2) -> g2.sum - g1.sum).collect(Collectors.toList());
        List<Integer> answer = new LinkedList<>();
        for (Genre genre : sortedGenres) {
            List<Map.Entry<Integer, Integer>> list = genre.indexMap.entrySet().stream().sorted((e1, e2) -> e2.getValue() - e1.getValue()).collect(Collectors.toList());
            answer.add(list.get(0).getKey());
            if (list.size() > 1)
                answer.add(list.get(1).getKey());
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    static class Genre {
        String name;
        Map<Integer, Integer> indexMap;
        int sum;

        public Genre(String name) {
            this.name = name;
            this.indexMap = new HashMap<>();
            this.sum = 0;
        }

        public void add(int index, int playNum) {
            indexMap.put(index, playNum);
            sum += playNum;
        }
    }
}
