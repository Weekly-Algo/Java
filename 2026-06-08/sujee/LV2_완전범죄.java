import java.util.*;

class Solution {

    static int INF = Integer.MAX_VALUE;

    public int solution(int[][] info, int n, int m) {
        // dp[j]에는 B의 누적 흔적이 j 일때 A의 누적 흔적의 최솟값을 담는다...
        int[] dp = new int[m];
        Arrays.fill(dp, INF);
        dp[0] = 0; // 시작점

        for (int i = 0; i < info.length; i++) {
            int a = info[i][0], b = info[i][1];
            int[] next = new int[m]; // 다음 물건 훔친 뒤의 결과!
            Arrays.fill(next, INF);

            for (int j = 0; j < m; j++) {
                int x = dp[j];
                if (x == INF) continue;

                // A가 훔친 경우 -> B 흔적은 유지, A 흔적은 x+a
                if (x + a < n) {     // n 넘으면 안됨!                  
                    next[j] = Math.min(next[j], x + a);  // 이미 이번 물건을 더 적은 흔적으로 훔쳤을 경우 그 방식으로 유지
                }
                // B가 훔친 경우 -> B 흔적은 j+b로 이동, A 흔적은 그대로
                if (j + b < m) {     // m 넘으면 안됨!                  
                    next[j + b] = Math.min(next[j + b], x);
                }
            }
            dp = next;
        }

        int answer = INF;
        for (int j = 0; j < m; j++) {
            answer = Math.min(answer, dp[j]);  // 모든 칸 중 최솟값이 정답이 된다
        }
        return answer == INF ? -1 : answer;    // 다 INF면(최솟값이 INF이면) -1 반환.
    }
}