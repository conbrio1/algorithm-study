package boj.boj14890;
// 경사로

// 구현
// https://100100e.tistory.com/207 참고하기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int solution(int[][] map, int N, int L) {
        int able = 0;
        for (int i = 0; i < N; i++) {
            int conti = 0;
            int idx = 0;
            int prev = map[i][0];
            int upDown = 0; // up : 1, down : -1
            for (; idx < N; idx++) {
                if (map[i][idx] == prev) {
                    conti++;
                    continue;
                }

                if (Math.abs(map[i][idx] - prev) > 1) {
                    break;
                } else {
                    if (map[i][idx] > prev) { // 1 높아짐
                        if (upDown == 1) { // up + up
                            if (conti >= L) {
                                conti = 1;
                            } else {
                                break;
                            }
                        } else if (upDown == -1) { // down + up
                            if (conti >= 2 * L) {
                                conti = 1;
                            } else {
                                break;
                            }
                        } else { // up
                            if (idx >= L) {
                                conti = 1;
                            } else {
                                break;
                            }
                        }
                        upDown = 1;
                    } else { // 1 낮아짐
                        if (idx + L > N) {
                            break;
                        }

                        if (upDown == 1) { // up + down
                            conti = 1;
                        } else if (upDown == -1) { // down + down
                            if (conti >= L) {
                                conti = 1;
                            } else {
                                break;
                            }
                        } else { // down
                            conti = 1;
                        }
                        upDown = -1;
                    }
                    prev = map[i][idx];
                }
            }
            if (idx == N)
                able++;
        }

        for (int i = 0; i < N; i++) {
            int conti = 0;
            int idx = 0;
            int prev = map[0][i];
            int upDown = 0; // up : 1, down : -1
            for (; idx < N; idx++) {
                if (map[idx][i] == prev) {
                    conti++;
                    continue;
                }

                if (Math.abs(map[idx][i] - prev) > 1) {
                    break;
                } else {
                    if (map[idx][i] > prev) { // 1 높아짐
                        if (upDown == 1) { // up + up
                            if (conti >= L) {
                                conti = 1;
                            } else {
                                break;
                            }
                        } else if (upDown == -1) { // down + up
                            if (conti >= 2 * L) {
                                conti = 1;
                            } else {
                                break;
                            }
                        } else { // up
                            if (idx >= L) {
                                conti = 1;
                            } else {
                                break;
                            }
                        }
                        upDown = 1;
                    } else { // 1 낮아짐
                        if (idx + L > N) {
                            break;
                        }

                        if (upDown == 1) { // up + down
                            conti = 1;
                        } else if (upDown == -1) { // down + down
                            if (conti >= L) {
                                conti = 1;
                            } else {
                                break;
                            }
                        } else { // down
                            conti = 1;
                        }
                        upDown = -1;
                    }
                    prev = map[idx][i];
                }
            }
            if (idx == N)
                able++;
        }
        return able;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(solution(map, N, L));
    }
}
