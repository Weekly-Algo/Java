import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G5_2565_전깃줄 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        }); // A를 기준으로 오름차순 정렬했다.

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        int[][] line = new int[N][2];
        for (int i = 0; i < N; i++) {
            line[i] = pq.poll();
        }

        int[] dp = new int[N+1];
        int max = 0; // 가장 긴 증가하는 부분수열의 길이
        for(int i = 1; i <= N; i++) {
            if(i == 1) {
                dp[i] = 1;
                max = 1;
                continue;
            }
            for(int j = i-2; j >= 0; j--) {
                if(line[j][1] < line[i-1][1])
                    dp[i] = Math.max(dp[j+1], dp[i]);
            }
            dp[i]++;
            max = Math.max(dp[i], max);
        }
        System.out.println(N - max);
    }
}
