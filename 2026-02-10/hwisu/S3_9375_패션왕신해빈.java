package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class S3_9375_패션왕신해빈 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			
			int N = Integer.parseInt(br.readLine());
			
			HashMap<String, Integer> map = new HashMap<>();
			
			for (int i=0; i<N; i++) {

				st = new StringTokenizer(br.readLine());
				st.nextToken();
				String item = st.nextToken();
				
				if (map.containsKey(item)) {
				    map.put(item, map.get(item) + 1);
				} else {
				    map.put(item, 1);
				}
			}
			
			int result = 1;
			
			for (int val : map.values()) {
			    result *= (val + 1);
			}
			System.out.println(result - 1);

		} // tc
		
		
		
	} // main
}
