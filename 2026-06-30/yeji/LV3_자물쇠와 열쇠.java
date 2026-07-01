class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        
        int m = key.length;
        int n = lock.length;
        int size = n + 2 * (m - 1);

        // key 4방향 회전
        for (int r = 0; r < 4; r++) {

            // key 이동
            for (int x = 0; x <= size - m; x++) {
                for (int y = 0; y <= size - m; y++) {

                    int[][] temp = new int[size][size];

                    // temp 배열 가운데에 lock 저장
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            temp[i + m - 1][j + m - 1] = lock[i][j];
                        }
                    }

                    // temp에 key 더하기
                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < m; j++) {
                            temp[x + i][y + j] += key[i][j];
                        }
                    }

                    // lock 영역 확인
                    if (check(temp, m, n)) {
                        return true;
                    }
                }
            }

            // key 회전
            key = rotate(key);
        }

        return false;
    }

    public boolean check(int[][] temp, int m, int n) {

        // lock 영역 확인
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (temp[i + m - 1][j + m - 1] != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public int[][] rotate(int[][] key) {

        int m = key.length;
        int[][] rotated = new int[m][m];

        // 90도 회전
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                rotated[j][m - 1 - i] = key[i][j];
            }
        }

        return rotated;
    }
}