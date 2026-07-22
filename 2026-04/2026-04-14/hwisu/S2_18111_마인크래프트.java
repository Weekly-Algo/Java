import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_18111_마인크래프트 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		int min = 256;
		int max = 0;
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 높이는 0 ~ 256 사이므로 탐색 범위를 한정짓기
				if (map[i][j] < min) min = map[i][j];
				if (map[i][j] > max) max = map[i][j];
			}
		}
		
		int finalTime = Integer.MAX_VALUE;
		int finalHeight = -1;
		
		
		for (int height=min; height<=max; height++) {
			int time = 0;
			int inventory = B;
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					int dif = map[i][j] - height;
					
					if (dif > 0) {
						time += (dif * 2);
						inventory += dif;
					}
					else if (dif < 0) {
						time += (Math.abs(dif));
						inventory -= (Math.abs(dif));
					}
				}
			}
			
			// 인벤토리가 양수일 경우에만 업데이트
			if (inventory >= 0) {
				if (time <= finalTime) {
					finalTime = time;
					finalHeight = height;
				}
			}
			
		} // bf
		
		System.out.println(finalTime + " " + finalHeight);
	} // main

}