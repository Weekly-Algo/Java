import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_10974_모든순열 {
	static int N;
	static int[] arr;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		visited = new boolean[N+1];
		
		dfs(0);
		
		System.out.println(sb);
		
	} // main
	
	static void dfs(int depth) {
		// 분기
		if (depth == N) {
			for (int i=0; i<N; i++) {
				sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=1; i<=N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				arr[depth] = i;
				dfs(depth + 1);
				visited[i] =false; // 초기화
			}
		}
		
	} // dfs

}
