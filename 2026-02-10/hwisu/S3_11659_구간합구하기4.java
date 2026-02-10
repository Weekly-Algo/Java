package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_11659_구간합구하기4 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		long[] sum = new long[N];
		sum[0] = arr[0];
		
		for (int i=1; i<N; i++) {
			arr[i] = arr[i] + arr[i-1];
			sum[i] = arr[i];  
		}
		
		long answer = 0;
		for (int t=0; t<M; t++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken()) -1;
			int end = Integer.parseInt(st.nextToken()) -1;
			
			if (start == 0) {
				answer = sum[end];
			}
			else {
				answer = sum[end] - sum[start-1];
			}
			
			sb.append(answer).append("\n");
		} // tc
		
		System.out.println(sb);
	} // main

}
