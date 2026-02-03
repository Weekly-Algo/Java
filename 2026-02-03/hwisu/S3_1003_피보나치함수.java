package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_1003_피보나치함수 {
	static Integer[][] dp = new Integer[41][2];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		dp[0][0] = 1; // N=0일 때 0 호출 횟수
        dp[0][1] = 0; // N=0일 때 1 호출 횟수
        dp[1][0] = 0; // N=1일 때 0 호출 횟수
        dp[1][1] = 1; // N=1일 때 1 호출 횟수

        for (int i = 2; i <= 40; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
        }
        
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            System.out.println(dp[N][0] + " " + dp[N][1]);
        }
        
	} // main

}
