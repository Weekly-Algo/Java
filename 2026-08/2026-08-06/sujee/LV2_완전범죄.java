import java.util.*;


class Solution {

    static int INF = Integer.MAX_VALUE;

    public int solution(int[][] info, int n, int m) {
        int answer = INF;

        // dp 배열 생성!
        // 인덱스 : B 흔적 누적값
        // 배열 안에 담긴 값 : A 흔적 누적값
        int[] dp = new int[m];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i = 0; i < info.length; i++) {
            int A = info[i][0];
            int B = info[i][1];

            for(int j = m-1; j >= 0; j--) {
                // A가 훔치는 경우
                int optionA = (dp[j] == INF) ? INF : dp[j] + A;
                // B가 훔치는 경우
                int optionB = (j >= B && dp[j - B] != INF) ? dp[j - B] : INF;

                dp[j] = Math.min(optionA, optionB);
            }

        }

        for(int i = 0; i < m; i++) {
            answer = Math.min(answer, dp[i]);
        }

        return answer < n ? answer : -1;
    }
}