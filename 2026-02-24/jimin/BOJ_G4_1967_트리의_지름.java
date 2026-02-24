import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G4_1967_트리의_지름 {
    //간선 클래스
    static class Edge {
        int to; //도착정점
        int cost; //간선 가중치

        Edge(int to, int weight) {
            this.to = to;
            this.cost = weight;
        }
    }

    //큐 상태 클래스
    static class State {
        int node; //노드
        int dist; //시작점부터 누적 거리

        State(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static int n; //노드의 수
    static List<Edge>[] graph;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];
        visited = new boolean[n + 1];

        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            graph[parent].add(new Edge(child, num));
            graph[child].add(new Edge(parent, num));
        }

        //1번 노드에서 가장 먼 노드 찾기
        int first = bfs(1);

        //초기화
        Arrays.fill(dist, 0);
        Arrays.fill(visited, false);

        int second = bfs(first);

        //지름 출력
        System.out.println(dist[second]);

    }

    public static int bfs(int start) {
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(start, 0)); //큐에 넣기
        visited[start] = true; //방문처리

        while(!queue.isEmpty()) {
            State curr = queue.poll(); //하나 꺼내기
            int v = curr.node;
            int weight = curr.dist;

            for(Edge edge : graph[v]) {
                int nextNode = edge.to;
                if(visited[nextNode]) continue;
                visited[nextNode] = true;
                int nextDist = weight + edge.cost;
                dist[nextNode] = nextDist;
                queue.offer(new State(nextNode, nextDist));
            }
        }

        int farNode = start;
        int maxDist = 0;

        for(int i = 1; i <= n; i++) {
            if(dist[i] > maxDist) {
                maxDist = dist[i];
                farNode = i;
            }
        }

        return farNode;
    }
}
