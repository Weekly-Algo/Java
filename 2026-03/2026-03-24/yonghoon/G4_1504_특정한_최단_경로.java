import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G4_1504_특정한_최단_경로 {
    static int N, E; // N: 정점 수, E: 간선 수
    static List<Node>[] graph; // 그래프
    static final int INF = 200000000; // 2억
    static class Node {
        int node;
        int weight;
        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 그래프 생성
        graph = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++)
            graph[i] = new ArrayList<>();

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, weight));
            graph[to].add(new Node(from, weight));
        }

        // 꼭 지나야 하는 2개의 점
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1. 1 -> v1 -> v2 -> N
        // 2. 1 -> v2 -> v1 -> N
        long dist1 = 0;
        dist1 += dijkstra(1, v1);
        dist1 += dijkstra(v1, v2);
        dist1 += dijkstra(v2, N);

        long dist2 = 0;
        dist2 += dijkstra(1, v2);
        dist2 += dijkstra(v2, v1);
        dist2 += dijkstra(v1, N);

        long ans = Math.min(dist1, dist2);
        if(ans >= INF)
            System.out.println(-1);
        else
            System.out.println(ans);
    }

    // 다익스트라
    static int dijkstra(int start, int end) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF); // 최소 거리 배열
        dist[start] = 0; // 시작점의 거리는 0

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            // 현재 거리가 이미 적혀있는 거리보다 크면 넣을 필요 없음
            if(curr.weight > dist[curr.node]) continue;

            for(Node next : graph[curr.node]) {
                // 다음 노드에서의 최소 거리 배열의 값이 현재 노드에서의 값 + 다음 노드까지의 거리 보다 크면 갱신이 필요
                if(dist[next.node] > dist[curr.node] + next.weight) {
                    dist[next.node] = dist[curr.node] + next.weight;
                    pq.offer(new Node(next.node, dist[next.node]));
                }
            }
        }

        return dist[end];
    }
}
