import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_9461_파도반수열 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N의 최대 범위인 100까지 저장할 수 있는 공간
		long[] dp = new long[101];
		
		dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
		
        for (int i = 4; i <= 100; i++) {
            dp[i] = dp[i-2] + dp[i-3];
        }
        
        int T = Integer.parseInt(br.readLine()); 
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");
        }
        
        System.out.print(sb.toString());

	} // main

}