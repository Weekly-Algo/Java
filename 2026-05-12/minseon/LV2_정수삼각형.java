import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;

        int[][] dp = new int[n][n];

        dp[0][0] = triangle[0][0];

        //0 지나고 1번째 줄부터 시작
        for(int i=1; i<n; i++) {
            for(int j=0; j<=i; j++) {

                //맨 왼쪽
                if(j == 0) {
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                }

                //맨 오른쪽
                else if(j == i) {
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                }

                //가운데 (위에서 내려오는 두 가지 중에서 max 선택)
                else {
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
                }
            }
        }

        int ans = 0;
        for(int j=0; j<n; j++) {
            ans = Math.max(ans, dp[n-1][j]);
        }

        return ans;
    }
}