import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class S1_11286_절댓값힙 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		// 양수와 음수 각각의 우선순위큐
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		PriorityQueue<Integer> mq = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int t=0; t<N; t++) {
			int x = Integer.parseInt(br.readLine());
			
			// x==0 일때, 모든 큐가 비어있으면 0 출력
			if (x == 0 && pq.isEmpty() && mq.isEmpty()) {
				sb.append(0).append("\n");
				continue;
			}
			
			// x의 값이 양수면 플러스우큐, 음수면 마이너스우큐에 삽입
			if (x > 0) {
				pq.offer(x);
				continue;
			}
			else if (x < 0) {
				mq.offer(x);
				continue;
			}
			
			// 최소값을 비교하기 위한 변수
			int plus = 0;
			int minus = 0;
			
			// 하나의 큐만 비어있으면, 다른 큐의 최소값 출력
			if (!pq.isEmpty()) {
				plus = Math.abs(pq.peek());
			}
			else {
				sb.append(mq.poll()).append("\n");
				continue;
			}

			if (!mq.isEmpty()) {
				minus = Math.abs(mq.peek());
			}
			else {
				sb.append(pq.poll()).append("\n");
				continue;
			}
			
			// 빈 큐가 없으면 최소값을 비교해서 출력
			if (plus < minus) {
				sb.append(pq.poll()).append("\n");
			}
			else {
				sb.append(mq.poll()).append("\n");
			}
			
		} // tc
		
		System.out.println(sb);

	} // main

}