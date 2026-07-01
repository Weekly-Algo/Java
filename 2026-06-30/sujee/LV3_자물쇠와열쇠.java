class Solution {

    static int[][] lock;

    public boolean solution(int[][] key, int[][] lock) {
        Solution.lock = lock;

        for (int r = 0; r < 4; r++) {
            key = rotate(key); // 열쇠 회전....

            // 키 대보기!! -> 맞으면 바로 끝냄
            int M = lock.length, K = key.length;
            // 범위지정이 헷갈렸음...
            for (int x = -(K - 1); x < M; x++) {
                for (int y = -(K - 1); y < M; y++) {
                    if (check(key, x, y)) return true;
                }
            }
        }

        return false;
    }

    // 열쇠 90도 돌리기
    static int[][] rotate(int[][] key) {
        int len = key.length;
        int[][] n_key = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                n_key[j][len - 1 - i] = key[i][j];
            }
        }

        return n_key;
    }

    // 열리는지 체크...
    static boolean check(int[][] key, int x, int y) {
        int M = lock.length, K = key.length;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                int ki = i - x, kj = j - y;   // 이 자물쇠 칸에 닿는 열쇠 좌표
                int val = lock[i][j];

                // 열쇠 범위 안이면 열쇠 값 더하기
                if (ki >= 0 && ki < K && kj >= 0 && kj < K)
                    val += key[ki][kj];

                // 1이 아니면 안맞는거! -> 종료함
                if (val != 1) return false;
            }
        }
        return true;
    }
}