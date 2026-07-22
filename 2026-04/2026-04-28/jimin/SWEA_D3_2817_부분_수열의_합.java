import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D3_2817_부분_수열의_합 {
    static int N, K;
    static int[] arr;
    static boolean[] isSelected;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N];
            isSelected = new boolean[N];
            cnt = 0;

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            subset(0);

            System.out.println("#" + t + " " + cnt);
        }
    }

    private static void subset(int idx) {
        // 기저조건
        if(idx == N) {
        int sum = 0;
            for(int i = 0; i < N; i++) {
                if(isSelected[i]) {
                    sum += arr[i];
                }
            }
            if(sum == K) cnt++;
            return;
        }

        // 재귀
        isSelected[idx] = true; //선택
        subset(idx+1);
        isSelected[idx] = false; //미선택
        subset(idx+1);
    }
}
