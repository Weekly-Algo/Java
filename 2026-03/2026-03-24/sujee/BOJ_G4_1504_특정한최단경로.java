import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_1504_특정한최단경로 {

    static class Edge {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int INF = 1_000_000_000;
    static int N; // 정점의 개수
    static int E; // 간선의 개수
    static List<Edge>[] adj; // 인접리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점의 개수
        E = Integer.parseInt(st.nextToken()); // 간선의 개수

        adj = new ArrayList[N+1]; // 그래프 정보 저장할 인접리스트

        for(int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[from].add(new Edge(to, cost));
            adj[to].add(new Edge(from, cost));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        /////////////////////////////////// 입력완료 ///////////////////////////////

        /*
        갈 수 있는 경우는 딱 두가지.
        1 -> v1 -> v2 -> N
        1 -> v2 -> v1 -> N
        */

        int[] D_1 = new int[N+1]; // 1번에서 시작하는 다익스트라
        int[] D_v1 = new int[N+1]; // v1에서 시작하는 다익스트라
        int[] D_v2 = new int[N+1]; // v2에서 시작하는 다익스트라

        Arrays.fill(D_1, INF);
        Arrays.fill(D_v1, INF);
        Arrays.fill(D_v2, INF);

        D_1[1] = 0;
        D_1 = dijistra(D_1, 1);
        D_v1[v1] = 0;
        D_v1 = dijistra(D_v1, v1);
        D_v2[v2] = 0;
        D_v2 = dijistra(D_v2, v2);


        int m1 = D_1[v1] + D_v1[v2] + D_v2[N];
        int m2 = D_1[v2] + D_v2[v1] + D_v1[N];

        if((D_1[v1] >= INF || D_v1[v2] >= INF || D_v2[N] >=INF) && (D_1[v2] >=INF || D_v2[v1] >= INF || D_v1[N] >= INF)) {
            System.out.println(-1);
            return;
        }
        System.out.println(Math.min(m1, m2));

    }

    static int[] dijistra(int[] dist, int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()){

            Edge cur = pq.poll();

            int now = cur.to;
            int cost = cur.cost;

            // 이미 더 짧은 거리로 방문된 경우
            if(cost > dist[now]) continue;

            // 현재 정점과 연결된 간선 탐색
            for(Edge next : adj[now]){

                int nextNode = next.to;
                int newCost = cost + next.cost;

                // 더 짧은 경로 발견
                if(newCost < dist[nextNode]){
                    dist[nextNode] = newCost;
                    pq.offer(new Edge(nextNode, newCost));
                }
            }
        }

        return dist;
    }
}
