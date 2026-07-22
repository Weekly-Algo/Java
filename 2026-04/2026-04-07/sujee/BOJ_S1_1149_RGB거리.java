import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_1149_RGB거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N+1][3];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] cost = new int[3]; // 현재 집 비용
            for(int j = 0; j < 3; j++) {
                cost[j] = Integer.parseInt(st.nextToken());
            }

            if(i == 1) {
                dp[i] = cost;
            } // 처음에는 그냥 각각의 비용!

            else {
                dp[i][0] = Math.min(cost[0]+dp[i-1][1], cost[0]+dp[i-1][2]);
                dp[i][1] = Math.min(cost[1]+dp[i-1][0], cost[1]+dp[i-1][2]);
                dp[i][2] = Math.min(cost[2]+dp[i-1][0], cost[2]+dp[i-1][1]);
            }
        }

        int ans = Math.min(dp[N][0], dp[N][1]);
        ans = Math.min(ans, dp[N][2]);
        System.out.println(ans);
    }
}
