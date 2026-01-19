import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_S2_1654_랜선자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken()); // 오영식이 가진 랜선 개수
        int N = Integer.parseInt(st.nextToken()); // 필요한 랜선 개수

        long[] arr = new long[K];
        for(int i = 0; i < K; i++){
            arr[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(arr);
        long max = arr[arr.length-1];
        long min = 1;
        long mid = max;
        long ans = 0;

        while(min <= max){
            int cnt = 0;
            mid = (max + min) / 2;

            for(int i = 0; i < K; i++){
                cnt += (arr[i] / mid);
            }

            if(cnt < N){
                max = mid-1;
            } else {
                ans = mid;
                min = mid+1;
            }
        }
        System.out.println(ans);
    }
}
