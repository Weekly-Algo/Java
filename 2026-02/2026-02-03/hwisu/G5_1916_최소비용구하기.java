package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int target, weight;
	
	public Node(int target, int weight) {
		this.target = target;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
	
} // Node

public class G5_1916_최소비용구하기 {
	static List<Node>[] adj;
	static int[] dist; // 최단거리 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		adj = new ArrayList[n+1];
		for (int i=1; i<=n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[u].add(new Node(v,w));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dijkstra(start);
		
		System.out.println(dist[end]);
		
	} // main
	
	static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.offer(new Node(start,0));
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int curTarget = cur.target;
			int curWeight = cur.weight;
			
			if (dist[curTarget] < curWeight) continue;
			
			for (Node next : adj[curTarget]) {
				if (dist[next.target] > dist[curTarget] + next.weight) {
					dist[next.target] = dist[curTarget] + next.weight;
					pq.offer(new Node(next.target, dist[next.target]));
				}
			}
			
		} // while
		
	} // dijstra

}
