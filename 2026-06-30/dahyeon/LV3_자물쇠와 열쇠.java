import java.util.*;
// 돌기 1 홈 0
// lock 0부분은 다 채워져야 함
// 1끼리는 부딪히면 안됨
// 2차원 배열 90도 돌리기
// 배열 회전 + 1끼리 충돌나지 않음 + lock 0 채워지기


class Solution {
    public boolean solution(int[][] key, int[][] lock) {

        int m = key.length;
        int n = lock.length;

        int holeCnt = 0;

        // 자물쇠 홈 개수 세기
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(lock[i][j] == 0)
                    holeCnt++;
             }
        }

        if(holeCnt == 0) return true;

        for(int r = 0; r < 4; r++){

            for (int x = -m + 1; x < n; x++) {
                for (int y = -m + 1; y < n; y++) {

                    int filled = 0;
                    boolean possible = true;

                    // key 모든칸 확인
                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < m; j++) {
                            int lockX = x + i;
                            int lockY = y + j;

                            // lock 경계 외면 패쓰
                            if (lockX < 0 || lockX >= n || lockY < 0 || lockY >= n) {
                                continue;
                            }

                            // 돌기끼리 부딪힌 경우
                            if (key[i][j] == 1 && lock[lockX][lockY] == 1) {
                                possible = false;
                            }

                            // key 돌기가 lock 홈을 채움
                            if (key[i][j] == 1 && lock[lockX][lockY] == 0) {
                                filled++;
                            }
                        }
                    }

                    if (possible && filled == holeCnt) {
                        return true;
                    }
                }
            }

            key = rotate(key);
        }

        return false;
    }

    int[][] rotate(int[][] key) {
        int m = key.length;
        int[][] rotated = new int[m][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                rotated[j][m - 1 - i] = key[i][j];
            }
        }

        return rotated;
    }
}