import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G4_1967_트리의_지름 {
    static int n;
    static int maxDist = 0, farNode = 0; // 최대 거리와 가장 먼 노드
    static List<Edge>[] tree;
    static boolean[] visited;
    static class Edge {
        int to, weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        for(int i = 0; i <= n; i++)
            tree[i] = new ArrayList<>();

        // 그래프 입력
        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree[from].add(new Edge(to, weight));
            tree[to].add(new Edge(from, weight));
        }

        // dfs 호출
        for(int i = 1; i <= n; i++) {
            dfs(i, 0);
            visited =  new boolean[n + 1];
        }

        System.out.println(maxDist);
    }

    // dfs
    static void dfs(int node, int dist) {
        // 현재 거리가 최대 거리보다 크다면 갱신
        if(dist > maxDist)
            maxDist = dist;

        visited[node] = true;

        for(Edge edge : tree[node]) {
            if(!visited[edge.to])
                dfs(edge.to, dist + edge.weight);
        }
    }
}
