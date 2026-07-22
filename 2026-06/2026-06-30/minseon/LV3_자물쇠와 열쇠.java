class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int m = key.length;
        int n = lock.length;

        // 열쇠 4방향 회전
        // 0도, 90도, 180도, 270도
        for (int r = 0; r < 4; r++) {
            key = rotate(key);

            // 열쇠 놓을 위치 [3n, 3n] 이니까 *2를 검사
            for (int x = 0; x <= n * 2; x++) {
                for (int y = 0; y <= n * 2; y++) {
                    // 자물쇠가 3*3 이면, board는 9*9
                    // 큰 판 놓고 가운데에 자물쇠 넣어버려
                    int[][] board = new int[n * 3][n * 3];

                    // 자물쇠를 board 중앙에 놓기
                    // 아까 말한 것처럼 3배 크기의 보드에서 가운데 위치에 lock 두는
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            board[i + n][j + n] = lock[i][j];
                        }
                    }

                    // 이번에는 열쇠를 board에 넣어
                    // 현재 열쇠의 위치가 (x, y)
                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < m; j++) {
                            board[x + i][y + j] += key[i][j];
                        }
                    }

                    // 자물쇠 열렸는지 확인
                    // 전부 1이 되면 열림
                    if (isOpen(board, n)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    // 열쇠 회전
    // 시계 방향 90도로
    // 1 2 3    7 4 1
    // 4 5 6 -> 8 5 2
    // 7 8 9    9 6 3
    private int[][] rotate(int[][] key) {
        int m = key.length;
        int[][] rotated = new int[m][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                rotated[j][m - 1 - i] = key[i][j];
            }
        }

        return rotated;
    }

    // 열렸는지 확인하는 함수
    // 중앙의 자물쇠 영역만 확인
    private boolean isOpen(int[][] board, int n) {
        for (int i = n; i < n * 2; i++) {
            for (int j = n; j < n * 2; j++) {
                if (board[i][j] != 1) {
                    return false;
                }
            }
        }

        return true;
    }
}