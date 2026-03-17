import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_11727_2Xn타일링2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int[] dp = new int[1001];
        
        // 2x1 세로 타일 1개 >> 1가지
        dp[1] = 1;
        
        // 1x2 가로 타일 2개, 2x1 세로 타일 2개, 2x2 정사각형 1개 >> 3가지
        dp[2] = 3;
		
        // 점화식
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10007;
        }
        
        // 결과 출력
        System.out.println(dp[n]);
        
	} // main

}