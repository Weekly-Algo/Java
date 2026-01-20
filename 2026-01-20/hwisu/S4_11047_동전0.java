package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4_11047_동전0 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		
		for (int i=1; i<=N; i++ ) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int sum = 0;
		int count = 0;
		
		while (sum != K) {
			if (sum + arr[N] > K) {
				N--;
				continue;
			}
			sum += arr[N];
			count ++;
		}
		System.out.println(count);
	} // main

}