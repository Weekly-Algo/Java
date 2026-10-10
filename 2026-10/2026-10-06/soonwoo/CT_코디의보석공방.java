import java.io.*;
import java.util.*;

public class CT_코디의보석공방 {
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Q : 작업 개수
        int Q = Integer.parseInt(br.readLine());

        /* 작업 번호별 정보
            1. N w1 v1 w2 v2 ... wN vN : 보석을 준비
            2. w v : 무게 w, 가치 v인 보석을 입고
            3. idx : idx 보석을 판매
            4. W : 진열을 수행
            5. D : 세트 구성을 수행
        */
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 작업번호1 - 보석 준비는 가장 처음에 한 번만 주어짐
        int work = Integer.parseInt(st.nextToken());  
        int N = Integer.parseInt(st.nextToken());
        int[][] jewelry = new int[N+101][2];  // 추가 보석 입고는 최대 100번
        jewelry[0] = null;

        // 남아있는 보석의 무게를 담을 리스트 -> 작업번호5에서 사용
        List<Integer> weights = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            jewelry[i][0] = Integer.parseInt(st.nextToken());
            jewelry[i][1] = Integer.parseInt(st.nextToken());

            // 초기 보석의 무게를 weights에 저장
            weights.add(jewelry[i][0]);
        }

        // 무게 정렬
        Collections.sort(weights);

        StringBuilder sb = new StringBuilder();

        for (int q = 2; q <= Q; q++) {
            st = new StringTokenizer(br.readLine());
            work = Integer.parseInt(st.nextToken());  // 작업번호(2~5)

            // 작업번호2 - 보석 입고
            if (work == 2) {
                N++;  // 추가 입고된 보석의 인덱스
                int wei = Integer.parseInt(st.nextToken());
                int val = Integer.parseInt(st.nextToken());

                jewelry[N][0] = wei;
                jewelry[N][1] = val;

                // weights가 정렬된 상태를 유지하도록 새 무게의 삽입 위치 탐색
                // wei가 존재하면 해당 인덱스, 없으면 -(삽입 위치)-1 반환
                int pos = Collections.binarySearch(weights, wei);

                // binarySearch에서 값이 없으면 -(삽입 위치)-1 (< 0) 반환
                if (pos < 0)
                    pos = -pos - 1;

                // 정렬된 위치에 삽입
                weights.add(pos, wei);
            }

            // 작업번호3 - 보석 판매
            if (work == 3) {
                int idx = Integer.parseInt(st.nextToken());

                // 존재하지 않거나 이미 판매된 보석이라면 : -1 출력
                if (idx > N || jewelry[idx] == null) 
                    sb.append(-1).append("\n");

                // 판매된 보석이 아니라면 해당 보석의 가치를 출력 -> 판매한 보석은 null 처리
                else {
                    sb.append(jewelry[idx][1]).append("\n");

                    // 판매할 보석의 무게를 weights에서도 하나 제거
                    int wei = jewelry[idx][0];
                    int pos = Collections.binarySearch(weights, wei);

                    weights.remove(pos);
                    jewelry[idx] = null;
                }
            }

            // 작업번호4 - 진열
            if (work == 4) {
                int W = Integer.parseInt(st.nextToken());

                // dp[x] : 최대 허용 무게가 w일 때 얻을 수 있는 최대 가치
                int[] dp = new int[W+1];

                for (int i = 1; i <= N; i++) {
                    if (jewelry[i] == null) continue;
                    
                    int wei = jewelry[i][0];
                    int val = jewelry[i][1];

                    for (int w = W; w >= wei; w--) 
                        dp[w] = Math.max(dp[w], dp[w - wei] + val);
                }
                
                sb.append(dp[W]).append("\n");
            }
            
            // 작업번호5 - 세트 구성
            if (work == 5) {
                int D = Integer.parseInt(st.nextToken());
                
                long cnt = 0;
                int left = 0;

                if (weights.size() < 2) {
                    sb.append(0).append("\n");
                }else {
                    // 정렬된 weights에서 two pointer로 조건을 만족하는 쌍 계산
                    for (int right = 1; right < weights.size(); right++) {
                        while (weights.get(right) - weights.get(left) > D) {
                            left++;
                        }

                        cnt += right - left;
                    }

                    sb.append(cnt).append("\n");
                }
            }
        }

        System.out.print(sb);
    }
}