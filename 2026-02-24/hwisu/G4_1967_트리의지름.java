package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G4_1967_트리의지름 {
	
	static class Node {
		int to, w;
		Node(int to, int w) {
			this.to = to;
			this.w = w;
		}
	} // Node
	
	static ArrayList<Node>[] adj;
	static boolean[] visited;
	static int maxDist = 0;
	static int farNode = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// 노드가 1개일땐 지름이 0
		if (n == 1) {
			System.out.println(0);
			return;
		}
		
		adj = new ArrayList[n + 1];
		for (int i=1; i<=n; i++) adj[i] = new ArrayList<>();
		
		for (int i=0; i<n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[u].add(new Node(v, w));
            adj[v].add(new Node(u, w));
		}
		
		// 임의 노드에서 가장 먼 노드 탐색
		visited = new boolean[n+1];
		dfs(1, 0);
		
		// 찾은 노드에서 가장 먼노드 탐색
		maxDist = 0;
		visited = new boolean[n+1];
		dfs(farNode, 0);
		
		System.out.println(maxDist);
	} // main
	
	static void dfs(int node, int distance) {
		visited[node] = true;
		
		if (distance > maxDist) {
			maxDist = distance;
			farNode = node;
		}
		
		for (Node next : adj[node]) {
			if (!visited[next.to]) {
				dfs(next.to, distance + next.w);
			}
		}
	} // dfs
	
}