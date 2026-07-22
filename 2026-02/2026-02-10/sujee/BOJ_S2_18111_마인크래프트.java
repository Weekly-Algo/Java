import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S2_18111_마인크래프트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] arr = new int[N*M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i*M + j] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.sort(arr);

        int ans = Integer.MAX_VALUE; int height = 0;
        for(int i = 256; i >= 0; i--) { // 기준 높이
            int sec = 0; int tmp = 0;
            for(int j = 0; j < N*M; j++){
                if(arr[j] == i) continue;
                else if(arr[j] < i) {
                    sec += i - arr[j];
                    tmp -= i - arr[j];
                }
                else {
                    sec += 2 * (arr[j] - i);
                    tmp += arr[j] - i;
                }
            }

            if(tmp >= -B && sec < ans) {
                ans = sec; height = i;
            }
        }

        System.out.println(ans + " " + height);
    }
}
