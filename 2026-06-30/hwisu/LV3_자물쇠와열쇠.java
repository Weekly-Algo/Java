class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int m = key.length;
        int n = lock.length;

        // 4방향 회전하면서 시도
        for (int rot = 0; rot < 4; rot++) {
            // 열쇠를 모든 위치에 놓아봄
            for (int x = 0; x < n + m - 1; x++) {
                for (int y = 0; y < n + m - 1; y++) {
                    if (check(key, lock, m, n, x, y)) {
                        return true;
                    }
                }
            }
            key = rotate(key, m);
        }

        return false;
    }

    // 열쇠를 (x, y) 위치에 놓았을 때 자물쇠가 열리는지 확인
    private boolean check(int[][] key, int[][] lock, int m, int n, int startX, int startY) {
        // 확장 판 만들기: 자물쇠를 (m-1, m-1) 위치에 복사
        int size = n + 2 * (m - 1);
        int[][] board = new int[size][size];

        // 자물쇠를 판 중앙에 배치
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i + m - 1][j + m - 1] = lock[i][j];
            }
        }

        // 열쇠를 (startX, startY) 위치에 놓기
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                board[startX + i][startY + j] += key[i][j];
            }
        }

        // 자물쇠 영역이 모두 1인지 확인
        for (int i = m - 1; i < m - 1 + n; i++) {
            for (int j = m - 1; j < m - 1 + n; j++) {
                if (board[i][j] != 1) {
                    return false; // 0이면 홈이 안채워진거, 2이면 돌기끼리 겹친거
                }
            }
        }

        return true;
    }

    // 시계 방향 90도 회전
    private int[][] rotate(int[][] key, int m) {
        int[][] rotated = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                rotated[j][m - 1 - i] = key[i][j];
            }
        }
        return rotated;
    }
}