import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G2_1167_트리의_지름 {
    //간선 클래스
    static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    
    //상태 클래스
    static class State {
        int node; //정점 노드
        int dist; //시작점부터 누적 거리

        State(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static int V;
    static List<Edge>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());

        graph = new ArrayList[V + 1];
        dist = new int[V + 1];
        Arrays.fill(dist, -1); //-1로 채우기

        for(int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nodeNum = Integer.parseInt(st.nextToken());
            while(true) {
                int to = Integer.parseInt(st.nextToken());
                if(to == -1) break; //-1이면 빠져나가기
                int cost = Integer.parseInt(st.nextToken());
                graph[nodeNum].add(new Edge(to, cost));
                graph[to].add(new Edge(nodeNum, cost));
            }
        }
        
        //시작 정점 구하기
        int start = bfs(1);

        //초기화
        Arrays.fill(dist, -1);

        int diameter = bfs(start);

        System.out.println(dist[diameter]);
    }

    public static int bfs(int start) {
        Queue<State> queue = new ArrayDeque<>();
        queue.offer(new State(start, 0)); //큐에 넣기
        dist[start] = 0; //방문처리

        while(!queue.isEmpty()) { //큐가 빌 때까지
            State curr = queue.poll(); //하나 빼기
            int v = curr.node;
            int weight = curr.dist;

            for(Edge edge : graph[v]) {
                int nextNode = edge.to;
                if(dist[nextNode] > -1) continue; //방문했다면
                int nextDist = weight + edge.cost;
                dist[nextNode] = nextDist;

                queue.offer(new State(nextNode, nextDist)); //큐에 넣기
            }
        }

        int farNode = start;
        int maxDist = 0;

        for(int i = 1; i <= V; i++) {
            if(dist[i] > maxDist) {
                maxDist = dist[i];
                farNode = i;
            }
        }

        return farNode;
    }
}
