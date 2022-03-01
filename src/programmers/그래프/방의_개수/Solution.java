package programmers.그래프.방의_개수;
// https://programmers.co.kr/learn/courses/30/lessons/49190?language=java
// https://velog.io/@easycelsius/프로그래머스방의-개수
// 좌표 지점을 재방문하며, 해당 간선을 처음 지날 때 방이 생성된다는 아이디어로 접근해야 함
// equals 와 hashCode 공부할 것

import java.util.*;

public class Solution {
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

    public static void main(String[] args) {
        int[] arrows = {6, 6, 6, 4, 4, 4, 2, 2, 2, 0, 0, 0, 1, 6, 5, 5, 3, 6, 0};
        System.out.println(solution(arrows));
    }

    public static int solution(int[] arrows) {
        int answer = 0;

        Map<Point, Integer> pointMap = new HashMap<>(); // value 로 개별 point 의 index 를 사용하는 map
        pointMap.put(new Point(0, 0), 0); // 처음 위치 삽입 : (0, 0), index 0
        ArrayList<LinkedList<Integer>> adjLists = new ArrayList<>(arrows.length); // 인접 리스트
        adjLists.add(new LinkedList<>()); // point 0 에 대한 리스트 생성

        int nIndex = 0;
        int cX = 0; // current 좌표
        int cY = 0;
        for (int arrow : arrows) {
            for (int i = 0; i < 2; i++) { // 0.5 좌표에서 교차하여 방이 생성되는 것을 막기 위해 scale 2배로 하여 수행
                int nX = cX + dx[arrow]; // next 좌표
                int nY = cY + dy[arrow];
                Point nPoint = new Point(nX, nY);

                int cIndex = pointMap.get(new Point(cX, cY));
                if (!pointMap.containsKey(nPoint)) { // 새로운 point 가 나타날 경우
                    pointMap.put(nPoint, ++nIndex); // pointIndex 는 하나씩 증가시킴
                    adjLists.add(new LinkedList<>()); // pointIndex 에 해당하는 리스트 생성
                    adjLists.get(nIndex).offer(cIndex);
                    adjLists.get(cIndex).offer(nIndex);
                } else { // 재방문 point 인 경우
                    int index = pointMap.get(nPoint);
                    if (!adjLists.get(index).contains(cIndex)) { // 중복 방지
                        adjLists.get(index).offer(cIndex);
                        adjLists.get(cIndex).offer(index);
                        answer++;
                    }
                }
                cX = nX;
                cY = nY;
            }
        }
        return answer;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
