import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_18111_마인크래프트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // 2차원으로 만들지 않아도 됨(좌표값이 중요X)
        int[] arr = new int[N * M];

        int k = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[k++] = Integer.parseInt(st.nextToken());
            }
        }

        // 정답 초와, 땅의 높이
        int time = Integer.MAX_VALUE;
        int ground = 0;

        // 모든 높이 체크
        for(int i = 0; i <= 256; i++) {
            int sec = 0; // 초 0에서 시작
            int bag = B; // 인벤 B에서 시작
            // 모든 배열을 해당 높이로 맞춤
            for(int j = 0; j < N * M; j++) {
                // 현재 땅의 높이 < 맞춰야할 높이
                if(arr[j] < i) {
                    sec += (i - arr[j]);
                    bag -= (i - arr[j]);
                }
                // 현재 땅의 높이 > 맞춰야할 높이
                else if (arr[j] > i){
                    sec += 2 * (arr[j] - i);
                    bag += (arr[j] - i);
                }
                // 높이가 같으면 작업 x
            }

            // 인벤이 0보다 크거나 같고 시간이 더 빠르거나 같으면 갱신
            if(bag >= 0 && sec <= time) {
                time = sec;
                ground = i;
            }
        }

        sb.append(time).append(" ").append(ground);
        System.out.println(sb);
    }
}
