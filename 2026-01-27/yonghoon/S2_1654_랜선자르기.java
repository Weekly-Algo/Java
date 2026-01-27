import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_1654_랜선자르기 {
    static int K, N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[K];
        long min = 1;
        long max = 0;
        for(int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(arr[i] > max)
                max = arr[i];
        }

        long ans = 0;
        while(min <= max) {
            long mid = (min + max) / 2;

            long cnt = 0;
            for(int i = 0; i < K; i++)
                cnt += arr[i] / mid;

            if(cnt < N)
                max = mid - 1;
            else {
                ans = mid;
                min = mid + 1;
            }
        }

        System.out.println(ans);
    }
}
