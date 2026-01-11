import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S4_11399_ATM {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		List<Integer> list = new ArrayList<>();

		for (int t = 0; t < N; t++) {
			list.add(Integer.parseInt(st.nextToken()));
		} // tc

		list.sort(null);

		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= i; j++) {
				sum += list.get(j);
			}
		}

		System.out.println(sum);
	}

}
