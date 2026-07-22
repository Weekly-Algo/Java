import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_30804_과일탕후루 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] cnt = new int[10];
        int kinds = 0;           // 현재 과일 종류 수

        int maxLen = 0;
        int l = 0;

        for (int r = 0; r < N; r++) {
            if (cnt[arr[r]] == 0) {
                kinds++;
            }
            cnt[arr[r]]++;

            // 종류가 3개가 되면 왼쪽 줄이기
            while (kinds > 2) {
                cnt[arr[l]]--;
                if (cnt[arr[l]] == 0) {
                    kinds--;
                }
                l++;
            }

            maxLen = Math.max(maxLen, r - l + 1);
        }

        System.out.println(maxLen);
    }
}
