import java.io.*;
import java.util.*;

public class 코디의_보석_공방 {
    static class jewel {
        int weight;
        int value;

        public jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
    static List<jewel> list = new ArrayList<>(); // 보석 진열
    static int remain = 0; // 남아있는 보석 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int Q = Integer.parseInt(st.nextToken()); // 작업의 수

        for(int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int work = Integer.parseInt(st.nextToken());

            // 준비
            if(work == 1) {
                remain = Integer.parseInt(st.nextToken()); // 보석의 개수

                for(int i = 0; i < remain; i++) {
                    int weight = Integer.parseInt(st.nextToken());
                    int value = Integer.parseInt(st.nextToken());

                    list.add(new jewel(weight, value));
                }
            } else if(work == 2) {
                // 입고
                int weight = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());

                list.add(new jewel(weight, value));
                remain++;
            } else if(work == 3) {
                // 판매
                int idx = Integer.parseInt(st.nextToken()) - 1;
                int value = 0;

                // 존재하지 않는 번호거나 이미 판매됨
                if(idx >= list.size() || list.get(idx).value == -1) {
                    value = -1;
                } else {
                    value = list.get(idx).value;

                    // 판매처리
                    list.set(idx, new jewel(-1, -1));
                    remain--;
                }

                sb.append(value).append("\n");
            } else if(work == 4) {
                // 진열
                int W = Integer.parseInt(st.nextToken()); // 무게 기준
                // 남은 보석이 없으면 가치합은 무조건 0
                if(remain <= 0) {
                    sb.append(0).append("\n");
                } else {
                    // 최대 가치합 계산
                    int[] dp = new int[W + 1];

                    for(jewel j : list) {
                        // 이미 판매된건 제외
                        if(j.weight == -1) continue;

                        // 앞에서부터 돌 경우 같은 보석을 또 사용할 수도 있음
                        // dp[w] = 무게 합이 w 이하가 되도록 보석을 골랐을 때 얻을 수 있는 최대 가치
                        for(int w = W; w >= j.weight; w--) {
                            dp[w] = Math.max(dp[w], dp[w - j.weight] + j.value);
                        }
                    }

                    sb.append(dp[W]).append("\n");
                }
            } else {
                int D = Integer.parseInt(st.nextToken());
                int cnt = 0; // 무게 차이가 D이하인 세트 개수

                // 남은 보석이 2개 미만이면 0
                if(remain < 2)
                    sb.append(cnt).append("\n");
                else {
                    int[] weightList = new int[remain]; // 무게 리스트
                    int idx = 0;
                    for(int i = 0; i < list.size(); i++) {
                        int weight = list.get(i).weight;
                        // 판매가 된 물품이면 무게 넣지 않음
                        if(weight == -1) continue;

                        weightList[idx++] = weight;
                    }

                    // 정렬
                    Arrays.sort(weightList);

                    int left = 0;
                    for(int right = 1; right < remain; right++) {
                        // 차가 D보다 크다면 left 조건 만족할때까지 이동
                        while(weightList[right] - weightList[left] > D)
                            left++;

                        cnt += right - left;
                    }

                    sb.append(cnt).append("\n");
                }
            }
        }

        System.out.println(sb.toString());
    }
}