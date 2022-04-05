package programmers.미분류.리틀_프렌즈_사천성;
// 섣불리 BFS로 풀어서 망했다. (visited 연관)
// 이 문제는 해가 안되면 돌아가서 다른 경로를 택해야 하기 때문에 DFS로 푸는 것이 효율적이다.
// 아니면 아예 https://dev-note-97.tistory.com/255 이 분처럼 간단히 생각하거나.

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public String solution(int m, int n, String[] board) {
        StringBuilder answer = new StringBuilder();

        SortedMap<Character, LinkedList<int[]>> treeMap = new TreeMap<>(); // 알파벳 순으로 정렬하려고 TreeMap 사용했다.
        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
            for (int j = 0; j < n; j++) {
                if (map[i][j] < 'A' || map[i][j] > 'Z')
                    continue;
                LinkedList<int[]> list = treeMap.getOrDefault(map[i][j], new LinkedList<>());
                list.add(new int[]{i, j});
                if (list.size() == 1)
                    treeMap.put(map[i][j], list); // 처음 나온 알파벳이면 map에 새로 넣어줌
            }
        }
        while (!treeMap.isEmpty()) {
            boolean modified = false;
            List<Character> removeList = new LinkedList<>();
            for (Character key : treeMap.keySet()) {
                int[] start = treeMap.get(key).get(0);
                int[] end = treeMap.get(key).get(1);

                if (dfs(start[0], start[1], end, -1, 0, map, new boolean[m][n])) {
                    answer.append(key);
                    removeList.add(key);
                    modified = true;

                    map[start[0]][start[1]] = '.';
                    map[end[0]][end[1]] = '.';
                    break; // 만약 ABCD에서 B가 제거되었으면, 다시 A가 되는지 확인해야 함
                }
            }
            if (!modified) {
                return "IMPOSSIBLE";
            } else {
                for (char c : removeList)
                    treeMap.remove(c);
            }
        }
        return answer.toString();
    }

    boolean dfs(int row, int col, int[] end, int postDir, int curved, char[][] map, boolean[][] visited) {
        if (curved > 1) { // backtracking
            return false;
        }
        if (row == end[0] && col == end[1]) { // base case
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int nr = row + dr[i];
            int nc = col + dc[i];

            if (nr < 0 || nc < 0 || nr >= map.length || nc >= map[0].length) continue;
            if (visited[nr][nc] || map[nr][nc] == '*' ||
                    (map[nr][nc] != '.' && map[nr][nc] != map[end[0]][end[1]])) continue;

            int nCurved;
            if (postDir == -1) nCurved = 0;
            else if (postDir != i) nCurved = curved + 1;
            else nCurved = curved;

            visited[nr][nc] = true;
            if (dfs(nr, nc, end, i, nCurved, map, visited))
                return true;
            visited[nr][nc] = false; // backtracking
        }
        return false;
    }

    @Test
    void test() {
        Assertions.assertEquals("ABCD", solution(3, 3, new String[]{"DBA", "C*A", "CDB"}));
        Assertions.assertEquals("RYAN", solution(2, 4, new String[]{"NRYN", "ARYA"}));
        Assertions.assertEquals("MUZI", solution(4, 4, new String[]{".ZI.", "M.**", "MZU.", ".IU."}));
        Assertions.assertEquals("IMPOSSIBLE", solution(2, 2, new String[]{"AB", "BA"}));

        Assertions.assertEquals("ABC", solution(3,3, new String[]{"AAB","...","BCC"}));
        Assertions.assertEquals("ABCE", solution(3,3, new String[]{"CCB", "A.B", "AEE"}));
        Assertions.assertEquals("BAC", solution(4,4, new String[]{"A..C", "..CB", "B...", "...A"}));
    }
}
