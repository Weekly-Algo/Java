import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_1504_특정한_최단_경로 {
    static class Node implements Comparable<Node> {
        int to;
        long cost;


        Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    static int N, E; //정점, 간선
    static final long INF = Integer.MAX_VALUE; //무한
    static List<Node>[] graph; //그래프
    static int v1, v2; //거쳐야하는 두 정점

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for(int i = 1; i < N+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        long[] dist1 = dijkstra(1);
        long[] distV1 = dijkstra(v1);
        long[] distV2 = dijkstra(v2);

        long path1 = dist1[v1] + distV1[v2] + distV2[N];
        long path2 = dist1[v2] + distV2[v1] + distV1[N];

        if(dist1[v1] == INF || distV1[v2] == INF || distV2[N] == INF) {
            path1 = INF;
        }
        if(dist1[v2] == INF || distV2[v1] == INF || distV1[N] == INF) {
            path2 = INF;
        }

        long answer = Math.min(path1, path2);

        if(answer >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    public static long[] dijkstra(int start) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0; //시작점의 거리는 0
        pq.offer(new Node(start, 0)); //pq에 넣기

        while(!pq.isEmpty()) { //우선순위큐가 빌때까지
            Node curr = pq.poll(); //하나 꺼내기

            //이미 더 짧은 거리로 방문된 경우
            if(curr.cost > dist[curr.to]) continue; //넘어가기

            for(Node next : graph[curr.to]) {
                if(dist[next.to] > curr.cost + next.cost) {
                    dist[next.to] = curr.cost + next.cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
        }

        return dist;
    }
}