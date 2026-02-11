import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G4_1753_최단경로 {
    static class Node {
        int to; //도착 정점
        int weight; //가중치

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int V; //정점 수
    static int E; //간선 수
    static final int INF = Integer.MAX_VALUE; //무한

    static List<Node>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine());

        graph = new ArrayList[V + 1];
        dist = new int[V + 1];

        for(int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }

        for(int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int startNum = Integer.parseInt(st.nextToken());
            int toNum = Integer.parseInt(st.nextToken());
            int weightNum = Integer.parseInt(st.nextToken());

            graph[startNum].add(new Node(toNum, weightNum));
        }

        dijkstra(K);

        for(int i = 1; i <= V; i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else if(i == K){
                System.out.println(0);
            } else {
                System.out.println(dist[i]);
            }
        }
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.weight, b.weight) //작은 것부터
        );

        dist[start] = 0; //시작정점 거리
        pq.add(new Node(start, 0)); //시작정점 넣기

        while(!pq.isEmpty()) { //큐가 빌 때까지
            Node curr = pq.poll(); //하나 빼기
            int now = curr.to; //현재 정점
            int nowDist = curr.weight; //현재 가중치

            if(dist[now] < nowDist) continue; //현재 정점 거리 < 현재 가중치 넘어가기

            for(Node next : graph[now]) { //현재 정점의 다음 정점들 순회
                int newDist = dist[now] + next.weight; //새로운 거리 = 현재 거리 + 다음까지의 가중치

                if(newDist < dist[next.to]) {
                    dist[next.to] = newDist; //갱신
                    pq.add(new Node(next.to, newDist)); //큐에 넣기
                }
            }
        }
    }
}
