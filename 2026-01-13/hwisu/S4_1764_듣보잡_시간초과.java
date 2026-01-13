package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S4_1764_듣보잡_시간초과 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String[] noListen = new String[N];
		String[] noSee = new String[M];
		
		for (int i=0; i<N; i++) {
			noListen[i] = br.readLine();
		}
		
		for (int i=0; i<M; i++) {
			noSee[i] = br.readLine();
		}
		
		List<String> list = new ArrayList<String>();
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (noListen[i].equals(noSee[j])) {
					list.add(noListen[i]);
				}
			}
		}
		
		list.sort(null);
		
		System.out.println(list.size());
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	} // main

}
