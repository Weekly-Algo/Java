package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class S2_1927_최소힙 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		for (int i=0; i<N; i++) {
			int x = Integer.parseInt(br.readLine());
			
			// x 가 0일떄 (최소값 추출)
			if (x == 0) {
				if(queue.isEmpty()) sb.append(0).append("\n");
				else {
					sb.append(queue.poll()).append("\n");
				}
				continue;
			}
			// x 가 0이 아닐때 추가
			else {
				queue.offer(x);
			}
			
			
		} // tc
		
		System.out.println(sb);
	} // main

}
