import java.io.*;
import java.util.*;

public class S1_1890_점프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][n];
        long[][] dp = new long[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int jump = map[i][j];

                // 점프가 0이거나 갈 수 없는 곳이면 pass
                if(jump == 0 || dp[i][j] == 0)
                    continue;

                // 오른쪽 이동
                if(j + jump < n)
                    dp[i][j + jump] += dp[i][j];

                // 아래로 이동
                if(i + jump < n)
                    dp[i + jump][j] += dp[i][j];
            }
        }

        System.out.println(dp[n-1][n-1]);
    }
}
