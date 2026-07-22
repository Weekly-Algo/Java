import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_11722_가장_긴_감소하는_부분_수열 {
    static int N;
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        dp = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        cal();
        int ans = 0;
        for(int i = 0; i < N; i++)
            ans = Math.max(ans, dp[i]);
        System.out.println(ans);
    }

    static void cal() {
        for(int i = 1; i < N; i++) {
            for(int j = i-1; j >= 0; j--) {
                if(arr[i] < arr[j])
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }
    }
}
