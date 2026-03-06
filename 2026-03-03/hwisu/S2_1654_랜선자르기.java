import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_1654_랜선자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		
        int K = Integer.parseInt(st.nextToken()); // 가지고 있는 랜선
        int N = Integer.parseInt(st.nextToken()); // 필요한 랜선
        
        int[] cables = new int[K];
        long max = 0; // 랜선 중 최대 길이

        // 랜선 길이 입력 및 최댓값 탐색
        for (int i = 0; i < K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
            if (max < cables[i]) {
                max = cables[i];
            }
        }
        
        long left = 1; 
        long right = max;
        long result = 0; // 조건을 만족하는 최대 길이

        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;

            // 모든 랜선을 mid 길이로 잘라본다.
            for (int i = 0; i < K; i++) {
                count += (cables[i] / mid);
            }

            // 구한 개수가 필요한 개수 N 이상이라면 (조건 만족)
            if (count >= N) {
                result = mid; // 현재 길이를 임시 정답으로 기록
                left = mid + 1; // 더 긴 길이로도 가능한지 확인하기 위해 left를 증가
            } 
            // 구한 개수가 N 미만이라면 (길이가 너무 김)
            else {
                right = mid - 1; // 길이를 줄이기 위해 right를 감소
            }
        }

        System.out.println(result);
        
	} // main

}