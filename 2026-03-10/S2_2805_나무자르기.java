import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_2805_나무자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
		
        int N = Integer.parseInt(st.nextToken()); // 나무 수
        int M = Integer.parseInt(st.nextToken()); // 필요한 나무 길이
        
        int[] trees = new int[N];
        int max = 0;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            if (trees[i] > max) max = trees[i]; // 가장 높은 나무 찾기
        }
		
        // 이분 탐색
        long low = 0;
        long high = max;
        long result = 0;

        while (low <= high) {
            long mid = (low + high) / 2;
            long sum = 0;

            for (int tree : trees) {
                if (tree > mid) {
                    sum += (tree - mid);
                }
            }
            
            // 나무가 충분! 더 높은 절단 높이를 찾아보자
            if (sum >= M) { 
                result = mid; // 현재 높이 기록
                low = mid + 1;
                
                // 나무 부족 -> 절단 높이를 낮추자
            } else { 
                high = mid - 1;
            }
        }

        System.out.println(result);

	} // main

}