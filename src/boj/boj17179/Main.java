package boj.boj17179;
// 케이크 자르기

// 가장 작은 조각의 길이의 최댓값을 구하기
// dfs가 아닌 이분 탐색을 해야 함
// https://zin0-0.tistory.com/117

// 1. 이분 탐색으로 "가장 작은 조각의 길이"(mid)를 추측
// 2. "가장 작은 조각의 길이"를 만족하도록 순서대로 잘랐을 때 "잘라야 하는 수" 보다
// 2-1. 많이 잘려진다면 left = mid + 1 하고, 해당 mid값을 기록
// 2-2. 적게 잘려진다면 right = mid - 1
// 3. 이분 탐색이 끝났을 때(left > right), 그동안 나온 mid값의 최댓값을 return

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, L;

    static boolean canCut(int[] cutPoint, int cutNum, int minPiece) {
        int prev = 0;
        for (int i = 0; i < cutPoint.length; i++) {
            if (cutPoint[i] - prev >= minPiece) {
                cutNum--;
                prev = cutPoint[i];
            }
        }
        // cutNum(잘라야 하는 수) 이상 잘린다면 cutNum < 0 이 됨
        return cutNum < 0;
    }

    static int solution(int[] cutPoint, int cutNum) {
        int maxLength = 0;

        int left = 0;
        int right = L;
        while (left <= right) {
            int minPiece = (left + right) / 2; // mid
            if (canCut(cutPoint, cutNum, minPiece)) {
                left = minPiece + 1;
                maxLength = Math.max(maxLength, minPiece);
            } else {
                right = minPiece - 1;
            }
        }

        return maxLength;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        int[] cutPoint = new int[M + 1];
        for (int i = 0; i < M; i++) {
            cutPoint[i] = Integer.parseInt(br.readLine());
        }
        cutPoint[M] = L;

        int[] cutNums = new int[N];
        for (int i = 0; i < N; i++) {
            cutNums[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < N; i++) {
            System.out.println(solution(cutPoint, cutNums[i]));
        }
    }
}

/*

핵심 : 이분탐색으로 무엇을 찾을 것이냐?
--> 자를 수 있는 지점, 잘라야 하는 수
--> 여기서 잘라진 가장 작은 조각의 길이를 구하고,
--> 모든 경우의 수에서 가장 작은 조각의 길이의 최댓값을 구해야 함
--> 그래서 처음에 접근할 때는 자를 수 있는 지점들을 '조합'해서 그중에서 가장 작은 조각의 길이들을 다 구해보고, 최댓값을 구해야지 --> X

이분 탐색 low, high, mid
--> mid 즉 구해야 하는 '가장 작은 조각의 길이'를 mid로 한다.

    케이크가 70cm
    어쨋거나 0 < 가장 작은 조각의 길이 < 70
    10 20 35 55 60 70
    3번 잘라야 하는 데 mid : 15(답)가 되면
    prev : 0으로 두고,
    10 20 35 55 60 70 순회

    --> 10 - 0 < 15
    --> 20 - 0 > 15 --> 20 자름(prev = 20)
    --> 35 - 20 >= 15 --> 35 자름(prev = 35)
    --> 55 - 35 > 15 --> 55 자름(prev == 55)
    --> 60 - 55 < 15
    --> 70 - 55 > 15

    20 35 55 --> 3번 잘랐고 가장 작은 조각의 길이는 15

low : 0, high : 70, mid : 35
0 70 35
0 34 17
0 16 8 --> 3번 이상
9 16 12 --> 3번 이상 잘라짐
13 16 14 --> 3번 이상
15 16 15 --> 3번 이상
16 16 --> 끝

*/