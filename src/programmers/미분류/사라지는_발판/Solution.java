package programmers.미분류.사라지는_발판;
// https://tech.kakao.com/2022/01/14/2022-kakao-recruitment-round-1/
// https://yjyoon-dev.github.io/kakao/2022/01/23/kakao-2022-blind-07/
// https://youtu.be/caGtdr3_nxs?t=1780

// 즉, 이길 수 있는 플레이어는 최대한 빨리 승리하도록 플레이하고, 질 수밖에 없는 플레이어는 최대한 오래 버티도록 플레이합니다.
// '이길 수 있는 플레이어'는 실수만 하지 않는다면 항상 이기는 플레이어를 의미하며,
// '질 수밖에 없는 플레이어'는 최선을 다해도 상대가 실수하지 않으면 항상 질 수밖에 없는 플레이어를 의미합니다.

// 만약 상대 턴으로 넘어간 모든 함수의 결과가 ‘패배’일 경우, ‘나’는 반드시 이길 수 있습니다.
// 반대로, 상대 턴으로 넘어간 모든 함수의 결과가 ‘승리’일 경우, ‘나’는 무조건 질 수밖에 없습니다.
// 따라서 함수를 호출한 결과를 종합해 ‘이길 수 있는 방법이 있는지’, ‘질 수밖에 없는지’를 구할 수 있습니다.
// 그리고 문제에 ‘양 플레이어는 최적의 플레이를 합니다.’라는 조건이 있기 때문에,
// 승리하는 플레이어는 최소한의 이동으로 승리하고 패배하는 플레이어는 최대한의 이동으로 패배해야 합니다.
// 따라서 이동 횟수의 최댓값 또는 최솟값을 상대방의 결과에 따라 적절하게 반환해 주면 됩니다.

// --> 이기는 '경우'가 있다면 최소값, 이기는 경우가 없을 경우(뭘 해도 항상 지는 경우) 최대값 반환하도록 해야 함
// 재귀 각각의 상황을 새로운 게임으로 보고, 해당 위치에서 시작했을 때 어떻게 되는지로 생각해야 함
// --> top-down approach

import java.util.LinkedList;

public class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public int solution(int[][] board, int[] aloc, int[] bloc) {

        return move(board, aloc, bloc).moveCount;
    }

    static class Result {
        boolean canWin;
        int moveCount;

        public Result(boolean canWin, int moveCount) {
            this.canWin = canWin;
            this.moveCount = moveCount;
        }
    }

    // Top-down
    Result move(int[][] board, int[] loc1, int[] loc2) {
        LinkedList<int[]> nextList = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            int nR = loc1[0] + dr[i];
            int nC = loc1[1] + dc[i];

            if (nR < 0 || nR >= board.length || nC < 0 || nC >= board[0].length)
                continue;
            if (board[nR][nC] == 0)
                continue;

            nextList.offer(new int[]{nR, nC});
        }

        // base case
        // 갈 수 있는 경로가 없거나, 내가 서있는 발판이 이미 상대에 의해 사라진 경우
        if (nextList.isEmpty() || board[loc1[0]][loc1[1]] == 0) {
            return new Result(false, 0);
        }

        boolean canWin = false;
        int minTurn = Integer.MAX_VALUE;
        int maxTurn = 0;
        for (int[] next : nextList) {
            board[loc1[0]][loc1[1]] = 0;
            // 다음 턴이므로 parameter 순서 바뀌는 것에 주의
            Result res = move(board, loc2, new int[]{next[0], next[1]});
            board[loc1[0]][loc1[1]] = 1;

            if (!res.canWin) { // 상대가 짐 - 내가 이김
                canWin = true;
                minTurn = Math.min(minTurn, res.moveCount);
            } else { // 상대가 이김 - 내가 짐
                maxTurn = Math.max(maxTurn, res.moveCount);
            }
        }

        // 상대에게 넘어간 모든 결과가 '승리'인 경우 canWin은 false, 내가 이길 방법이 없으므로 maxTurn 사용
        // 상대에게 넘어간 결과 중 '패배'가 있는 경우 canWin은 true, 이길 수 있는 방법 있으며 minTurn 사용
        return new Result(canWin, (canWin ? minTurn : maxTurn) + 1);
    }
}
