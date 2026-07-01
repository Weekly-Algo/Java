class Solution {
    public int solution(int[][] triangle) {
        int depth = triangle.length;
        int[] dp = new int[depth];

        dp[0] = triangle[0][0];   // 꼭짓점

        for (int i = 1; i < depth; i++) {
            // 오른쪽부터 거꾸로!
            for (int j = i; j >= 0; j--) {
                if (j == i)
                    dp[j] = dp[j-1] + triangle[i][j]; // 오른쪽 끝 - 왼쪽 위에서만
                else if (j == 0)
                    dp[j] = dp[j] + triangle[i][j]; // 왼쪽 끝 - 오른쪽 위에서만
                else
                    dp[j] = Math.max(dp[j-1], dp[j]) + triangle[i][j];
            }
        }

        int answer = 0;
        for (int j = 0; j < depth; j++)
            answer = Math.max(answer, dp[j]);
        return answer;
    }
}