class Solution {
    public int solution(int m, int n, int[][] puddles) {

        int[][] dp = new int[n + 1][m + 1];
        boolean[][] water = new boolean[n + 1][m + 1];

        // 물 웅덩이 체크
        for (int[] puddle : puddles) {
            int col = puddle[0];
            int row = puddle[1];
            water[row][col] = true;
        }

        dp[1][1] = 1;

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {

                // 물 웅덩이면 패쓰
                if (water[r][c]) continue;
                // 출발지면 패쓰
                if (r == 1 && c == 1) continue;

                // 왼쪽 위쪽 합
                dp[r][c] = (dp[r - 1][c] + dp[r][c - 1]) % 1_000_000_007;
            }
        }

        return dp[n][m];
    }
}