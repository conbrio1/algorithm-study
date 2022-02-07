package boj.boj16935;
// 배열 돌리기 3
// regex로 생략 가능한 command 생략하기
// 오른쪽 90도와 왼쪽 90도는 실행 후 다음 command에서 행, 열이 뒤바뀌므로 N, M을 사용해서는 안됨
// 함수 내부에서 new int[][]하여 map에 할당할 경우, map은 함수가 끝난 뒤 원래 값으로 돌아옴(정확한 이유 파악 필요)
// -->
// 따라서 return int[][] 하여 해결

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;

    static int[][] solution(int[][] map, int[] commands) {
        int[][] output = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, output[i], 0, M);
        }

        for (int i = 0; i < commands.length; i++) {
            int rLength = output.length;
            int cLength = output[0].length;

            switch (commands[i]) {
                case 1: // 상하 반전
                    for (int j = 0; j < rLength / 2; j++) {
                        int[] tmp = output[j];
                        output[j] = output[rLength - j - 1];
                        output[rLength - j - 1] = tmp;
                    }
                    break;
                case 2: // 좌우 반전
                    for (int j = 0; j < rLength; j++) {
                        int[] row = output[j];
                        for (int k = 0; k < cLength / 2; k++) {
                            int tmp = row[k];
                            row[k] = row[cLength - k - 1];
                            row[cLength - k - 1] = tmp;
                        }
                    }
                    break;
                case 3: // 오른쪽 90도
                    int[][] rResult = new int[cLength][rLength];
                    for (int j = 0; j < rLength; j++) {
                        int[] row = output[j];
                        for (int k = 0; k < cLength; k++) {
                            rResult[k][rLength - j - 1] = row[k];
                        }
                    }
                    output = rResult;
                    break;
                case 4: // 왼쪽 90도
                    int[][] lResult = new int[cLength][rLength];
                    for (int j = 0; j < rLength; j++) {
                        int[] row = output[j];
                        for (int k = 0; k < cLength; k++) {
                            lResult[cLength - k - 1][j] = row[k];
                        }
                    }
                    output = lResult;
                    break;
                case 5: // 4부분 시계방향
                    int[][] tmp1 = new int[rLength][cLength];
                    for (int j = 0; j < rLength; j++) {
                        System.arraycopy(output[j], 0, tmp1[j], 0, cLength);
                    }
                    for (int j = 0; j < rLength; j++) {
                        for (int k = 0; k < cLength; k++) {
                            if (j < rLength / 2 && k < cLength / 2) {
                                output[j][k + cLength / 2] = tmp1[j][k];
                            } else if (j >= rLength / 2 && k < cLength / 2) {
                                output[j - rLength / 2][k] = tmp1[j][k];
                            } else if (j < rLength / 2 && k >= cLength / 2) {
                                output[j + rLength / 2][k] = tmp1[j][k];
                            } else if (j >= rLength / 2 && k >= cLength / 2) {
                                output[j][k - cLength / 2] = tmp1[j][k];
                            }
                        }
                    }
                    break;
                case 6: // 4부분 반시계방향
                    int[][] tmp2 = new int[rLength][cLength];
                    for (int j = 0; j < rLength; j++) {
                        System.arraycopy(output[j], 0, tmp2[j], 0, cLength);
                    }
                    for (int j = 0; j < rLength; j++) {
                        for (int k = 0; k < cLength; k++) {
                            if (j < rLength / 2 && k < cLength / 2) {
                                output[j + rLength / 2][k] = tmp2[j][k];
                            } else if (j >= rLength / 2 && k < cLength / 2) {
                                output[j][k + cLength / 2] = tmp2[j][k];
                            } else if (j < rLength / 2 && k >= cLength / 2) {
                                output[j][k - cLength / 2] = tmp2[j][k];
                            } else if (j >= rLength / 2 && k >= cLength / 2) {
                                output[j - rLength / 2][k] = tmp2[j][k];
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] commands = new int[R];
        st = new StringTokenizer(
                br.readLine().replaceAll("( 1 1)|( 2 2)|( 3 4)|( 5 6)|( 3 3 3 3)|( 4 4 4 4)|( 5 5 5 5)|( 6 6 6 6)", ""),
                " ");

        int i = 0;
        while (st.hasMoreTokens()) {
            commands[i++] = Integer.parseInt(st.nextToken());
        }

        int[][] output = solution(map, commands);
        StringBuilder sb = new StringBuilder();
        for (int[] row : output) {
            for (int element : row) {
                sb.append(element + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
