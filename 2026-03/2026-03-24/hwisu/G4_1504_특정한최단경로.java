import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G4_1504_특정한최단경로 {
	
	static class Node implements Comparable<Node> {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight; 
        }
    }
	
	static int N, E;
    static ArrayList<ArrayList<Node>> graph;
    // 오버플로우 방지 (1억)
    static final int INF = 100000000;
	
	public static void main(String[] args) throws IOException {
	    
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 그래프 인접 리스트 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 정보 입력 (무방향 양방향 그래프)
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1번 정점, v1, v2를 시작점으로 하는 다익스트라 결과를 각각 배열로 받음
        int[] distFrom1 = dijkstra(1);
        int[] distFromV1 = dijkstra(v1);
        int[] distFromV2 = dijkstra(v2);

        // 경로 1: 1 -> v1 -> v2 -> N
        int path1 = distFrom1[v1] + distFromV1[v2] + distFromV2[N];
        
        // 경로 2: 1 -> v2 -> v1 -> N
        int path2 = distFrom1[v2] + distFromV2[v1] + distFromV1[N];

        // 두 경로 중 최솟값 찾기
        int minPath = Math.min(path1, path2);

        // 경로가 아예 존재하지 않아 INF 값 이상을 가지는 경우 -1 출력
        if (minPath >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(minPath);
        }
        
	} // main
	
	static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        // 시작점 초기화
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int cur = curNode.end;
            int weight = curNode.weight;

            // 이미 처리되어 더 짧은 경로가 발견된 노드는 스킵하기
            if (dist[cur] < weight) {
                continue;
            }

            // 인접한 노드들을 확인하며 최단 거리 갱신
            for (Node nextNode : graph.get(cur)) {
                if (dist[nextNode.end] > dist[cur] + nextNode.weight) {
                    dist[nextNode.end] = dist[cur] + nextNode.weight;
                    pq.offer(new Node(nextNode.end, dist[nextNode.end]));
                }
            }
        }
        return dist;
        
    } //dijkstra

}
