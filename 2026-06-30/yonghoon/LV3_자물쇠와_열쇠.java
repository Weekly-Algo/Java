class LV3_자물쇠와_열쇠 {
    public boolean solution(int[][] key, int[][] lock) {
        // 키와 자물쇠가 겹치는 모든 경우의 수를 포함하는 새로운 맵이 필요
        int keySize = key.length;
        int lockSize = lock.length;
        int n = lockSize + 2 * (keySize - 1);
        int[][] map = new int[n][n];

        // 새로운 맵의 가운데에 자물쇠 채우기
        for(int i = 0; i < lockSize; i++) {
            for(int j = 0; j < lockSize; j++) {
                map[i + keySize - 1][j + keySize - 1] = lock[i][j];
            }
        }

        int[][] rotatedKey = key;
        // 90도로 돌려서 확인하기 위함(총 4번)
        for(int i = 0; i < 4; i++) {
            for(int x = 0; x <= n - keySize; x++) {
                for(int y = 0; y <= n - keySize; y++) {
                    // 1. 열쇠 올리기
                    putKey(map, rotatedKey, x, y);

                    // 2. 자물쇠 열 수 있는지 확인
                    if(checkLock(map, keySize - 1, lockSize))
                        return true;

                    // 3. 열쇠 내리기
                    removeKey(map, rotatedKey, x, y);

                }
            }

            // 4. 열쇠 회전
            rotatedKey = rotateKey(rotatedKey);
        }
        return false;
    }

    // 열쇠 올리기
    static void putKey(int[][] map, int[][] key, int x, int y) {
        int keySize = key.length;
        for(int i = 0; i < keySize; i++) {
            for(int j = 0; j < keySize; j++) {
                map[x + i][y + j] += key[i][j];
            }
        }
    }

    // 자물쇠 열 수 있는지 확인
    static boolean checkLock(int[][] map, int offset, int lockSize) {
        for(int i = 0; i < lockSize; i++) {
            for(int j = 0; j < lockSize; j++) {
                if(map[offset + i][offset + j] != 1)
                    return false;
            }
        }

        return true;
    }

    // 열쇠 내리기
    static void removeKey(int[][] map, int[][] key, int x, int y) {
        int keySize = key.length;
        for(int i = 0; i < keySize; i++) {
            for(int j = 0; j < keySize; j++) {
                map[x + i][y + j] -= key[i][j];
            }
        }
    }

    // 열쇠 90도 회전
    static int[][] rotateKey(int[][] key) {
        int n = key.length;
        int[][] rotatedKey = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                rotatedKey[i][j] = key[n - j - 1][i];
            }
        }

        return rotatedKey;
    }
}