package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class S3_2606_바이러스 {
	static int N, M;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[u].add(v);
			graph[v].add(u);
		}
		
		System.out.println(bfs(1));

	} // main
	
	static int bfs(int start) {
		Deque<Integer> deque = new ArrayDeque<>();
		deque.offer(start);
		visited[start] = true;
		
		int count = 0;
		
		while(!deque.isEmpty()) {
			int cur = deque.poll();
			
			for (int next : graph[cur]) {
				if(!visited[next]) {
					visited[next] = true;
					deque.offer(next);
					count++;
				}
			}
		} // while
		
		return count;
	} // bfs

}