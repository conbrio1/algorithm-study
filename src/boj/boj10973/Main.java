package boj.boj10973;
// 이전 순열
// next permutation 알고리즘 숙지할 것

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;

    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    static boolean solution(int[] arr) {
        int i = N - 1;

        while (i > 0 && arr[i - 1] <= arr[i])
            i--;

        if (i == 0) // 이미 오름차순으로 정렬됨
            return false;

        int j = N - 1;
        while (arr[i - 1] <= arr[j])
            j--;
        swap(arr, i - 1, j);

        for (int k = N - 1; i < k; ++i, --k)
            swap(arr, i, k);

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (solution(arr))
            System.out.println(Arrays.toString(arr).replaceAll("(\\[|\\]|,)", ""));
        else
            System.out.println(-1);
    }
}
