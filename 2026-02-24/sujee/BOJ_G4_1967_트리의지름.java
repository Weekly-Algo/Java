import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_G4_1967_트리의지름 {
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static ArrayList<Edge>[] graph;
    static int n;
    static int max = 0; // 정답이 될 최대 길이
    static boolean[] visited; // 방문여부 확인
    static int tmp; // 노드 번호 저장할 임시 값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n+1];
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight)); // 무방향이므로 양쪽 다 입력해줘야함
            graph[to].add(new Edge(from, weight));
        } // 입력받기 완료!!

        visited = new boolean[n+1];
        dfs(1,0); // 1번 노드에서 시작해본다...

        visited = new boolean[n+1];
        max = 0;
        dfs(tmp, 0);

        System.out.println(max);
    }

    static void dfs(int node, int dist){
        visited[node] = true;
        if(dist > max) {
            max = dist;
            tmp = node;
        }

        for(Edge next : graph[node]) {
            if(!visited[next.to]) dfs(next.to, dist+next.weight);
        }
    }
}


