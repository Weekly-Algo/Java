package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class S4_1764_듣보잡 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashSet<String> noSee = new HashSet<>();
		List<String> noSeeListen = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			noSee.add(br.readLine());
		}
		
		for (int i=0; i<M; i++) {
			String noListen = br.readLine();
			if (noSee.contains(noListen)) {
				noSeeListen.add(noListen);
			}
		}
		
		Collections.sort(noSeeListen);
		
		System.out.println(noSeeListen.size());
		for (int i=0; i<noSeeListen.size(); i++) {
			System.out.println(noSeeListen.get(i));
		}

	} // main

}