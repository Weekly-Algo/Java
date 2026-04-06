import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_17626_FourSquares {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        // dp[i] : 자연수 i를 제곱수의 합으로 표현할 때 필요한 최소 개수
        int[] dp = new int[n + 1];
        
        // 0을 만드는 개수는 0
        dp[0] = 0; 
        
        // 1부터 n까지 순차적으로 최소 개수 구하기
        for (int i = 1; i <= n; i++) {
            // 기본값 설정: 1^2 들의 합으로만 구성하는 최악의 경우 (예: 5 = 1+1+1+1+1)
            // 이전 숫자(i-1)를 만드는 최소 개수에 1^2 한 개를 더하는 것과 같다
            dp[i] = dp[i - 1] + 1;
            
            // i보다 작은 모든 제곱수(j * j)를 빼보면서 최솟값 갱신하기
            for (int j = 2; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        
        System.out.println(dp[n]);

	} // main

}
