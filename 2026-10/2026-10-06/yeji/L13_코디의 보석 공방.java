import java.io.*;
import java.util.*;

public class Main {

    static int[] weight = new int[1101];
    static int[] value = new int[1101];
    static boolean[] sold = new boolean[1101];

    static int lastNum;   // 마지막으로 부여한 보석 번호
    static int remain;    // 현재 남아있는 보석 수

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Q = Integer.parseInt(br.readLine());

        for (int q = 0; q < Q; q++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int work = Integer.parseInt(st.nextToken());

            // 1. 보석 준비
            if (work == 1) {

                int n = Integer.parseInt(st.nextToken());

                lastNum = n;
                remain = n;

                for (int i = 1; i <= n; i++) {
                    weight[i] = Integer.parseInt(st.nextToken());
                    value[i] = Integer.parseInt(st.nextToken());
                }
            }

            // 2. 보석 입고
            else if (work == 2) {

                int w = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                lastNum++;

                weight[lastNum] = w;
                value[lastNum] = v;

                remain++;
            }

            // 3. 보석 판매
            else if (work == 3) {

                int idx = Integer.parseInt(st.nextToken());

                // 없는 번호이거나 이미 판매된 보석
                if (idx > lastNum || sold[idx]) {
                    sb.append(-1).append('\n');
                } else {
                    sb.append(value[idx]).append('\n');

                    sold[idx] = true;
                    remain--;
                }
            }

            // 4. 진열
            else if (work == 4) {

                int W = Integer.parseInt(st.nextToken());

                int[] dp = new int[W + 1];

                // 남아있는 보석만 확인
                for (int i = 1; i <= lastNum; i++) {

                    if (sold[i]) {
                        continue;
                    }

                    int w = weight[i];
                    int v = value[i];

                    // 같은 보석을 한 번만 사용하기 위해 뒤에서부터
                    for (int j = W; j >= w; j--) {
                        dp[j] = Math.max(dp[j],
                                dp[j - w] + v);
                    }
                }

                sb.append(dp[W]).append('\n');
            }

            // 5. 세트 구성
            else if (work == 5) {

                int D = Integer.parseInt(st.nextToken());

                if (remain < 2) {
                    sb.append(0).append('\n');
                    continue;
                }

                // 남아있는 보석의 무게만 저장
                int[] arr = new int[remain];
                int idx = 0;

                for (int i = 1; i <= lastNum; i++) {
                    if (!sold[i]) {
                        arr[idx++] = weight[i];
                    }
                }

                Arrays.sort(arr);

                int left = 0;
                int cnt = 0;

                for (int right = 1; right < arr.length; right++) {

                    // 무게 차이가 D 이하가 될 때까지 left 이동
                    while (arr[right] - arr[left] > D) {
                        left++;
                    }

                    // left ~ right-1까지 모두 right와 세트 가능
                    cnt += right - left;
                }

                sb.append(cnt).append('\n');
            }
        }

        System.out.print(sb);
    }
}