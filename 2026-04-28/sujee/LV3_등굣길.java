import java.util.*;

class Solution {

    static int[][] dp;
    static boolean[][] puddle;
    static final int MOD = 1000000007;

    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        dp = new int[n + 1][m + 1];
        puddle = new boolean[n + 1][m + 1];

        for(int i = 0; i < puddles.length; i++) {
            int x = puddles[i][0];
            int y = puddles[i][1];
            puddle[y][x] = true;
        }

        dp[1][1] = 1;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(puddle[i][j]) {
                    dp[i][j] = 0;
                    continue;
                }

                if(i == 1 && j == 1) continue;

                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
            }
        }

        answer = dp[n][m];
        return answer;
    }
}