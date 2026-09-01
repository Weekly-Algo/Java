class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[] answer = {0, 0};

        int left = 0;
        int right = drops.length;
        int best = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            int[][] board = new int[m + 1][n + 1];

            for (int i = 0; i < mid; i++) {
                int x = drops[i][0];
                int y = drops[i][1];
                board[x + 1][y + 1]++;
            }

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    board[i][j] += board[i - 1][j] + board[i][j - 1] - board[i - 1][j - 1];
                }
            }

            boolean can = false;

            for (int i = 0; i <= m - h; i++) {
                for (int j = 0; j <= n - w; j++) {
                    int x1 = i + 1;
                    int y1 = j + 1;
                    int x2 = i + h;
                    int y2 = j + w;

                    int count = board[x2][y2]
                              - board[x1 - 1][y2]
                              - board[x2][y1 - 1]
                              + board[x1 - 1][y1 - 1];

                    if (count == 0) {
                        can = true;
                        break;
                    }
                }
                if (can) break;
            }

            if (can) {
                best = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        int[][] board = new int[m + 1][n + 1];

        for (int i = 0; i < best; i++) {
            int x = drops[i][0];
            int y = drops[i][1];
            board[x + 1][y + 1]++;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                board[i][j] += board[i - 1][j] + board[i][j - 1] - board[i - 1][j - 1];
            }
        }

        for (int i = 0; i <= m - h; i++) {
            for (int j = 0; j <= n - w; j++) {
                int x1 = i + 1;
                int y1 = j + 1;
                int x2 = i + h;
                int y2 = j + w;

                int count = board[x2][y2]
                          - board[x1 - 1][y2]
                          - board[x2][y1 - 1]
                          + board[x1 - 1][y1 - 1];

                if (count == 0) {
                    answer[0] = i;
                    answer[1] = j;
                    return answer;
                }
            }
        }

        return answer;
    }
}