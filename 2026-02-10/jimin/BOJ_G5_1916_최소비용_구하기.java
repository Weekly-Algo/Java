import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_G5_1916_최소비용_구하기 {

    static class Node {
        int to; //도착정점
        int weight; //가중치

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

    }
    static List<Node>[] graph;
    static int[] dist;

    static int N;
    static int M;
    static final int INF = Integer.MAX_VALUE; //무한

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        dist = new int[N + 1];

        for(int i = 1; i <=N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }

        for(int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int numStart = Integer.parseInt(st.nextToken());
            int numTo = Integer.parseInt(st.nextToken());
            int numWeight = Integer.parseInt(st.nextToken());

            graph[numStart].add(new Node(numTo, numWeight));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(dist[end]);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.weight, b.weight) //작은 것부터
        ); //큐 생성

        dist[start] = 0; //시작정점의 거리
        pq.add(new Node(start, 0)); //시작정점의 노드 넣기

        while(!pq.isEmpty()) { //큐가 빌 때까지
            Node curr = pq.poll(); //큐에서 하나 빼기
            int now = curr.to; //현재 정점
            int nowDist = curr.weight; //현재 가중치

            if(dist[now] < nowDist) continue; //현재 정점까지의 거리가 현재 가중치보다 작다면 넘어가기

            //아니라면
            for(Node next : graph[now]) { //현재 정점의 다음 연결 정점 순회
                int newDist = dist[now] + next.weight; //거리 = 지금까지의 거리 + 다음정점까지의 가중치

                if(newDist < dist[next.to]) { //거리가 다음정점까지의 최소거리보다 작다면
                    dist[next.to] = newDist; //갱신
                    pq.add(new Node(next.to, newDist)); //큐에 넣기
                }
            }
        }
    }

}
