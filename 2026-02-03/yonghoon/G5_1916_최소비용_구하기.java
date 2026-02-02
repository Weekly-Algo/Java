import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class G5_1916_최소비용_구하기 {
    static class Edge {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 그래프 생성
        List<Edge>[] graph = new List[N + 1];
        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, cost));
        }

        // A도시에서 B도시까지의 최소 비용
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] D = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        Arrays.fill(D, Integer.MAX_VALUE);
        // 시작점은 비용이 0
        D[A] = 0;

        for(int i = 1; i < N ;i++) {
            int min = Integer.MAX_VALUE; // 최소비용
            int minIdx = 0;

            for(int j = 1; j <= N; j++) {
                if(!visited[j] && D[j] < min) {
                    min = D[j];
                    minIdx = j;
                }
            }

            if(minIdx == 0) break;
            visited[minIdx] = true;

            for(Edge e : graph[minIdx]) {
                // 방문하지 않았으면서 기존 비용보다 현재 정점에서 가는 비용이 더 적다면 갱신이 필요
                if(!visited[e.to] && D[e.to] > D[minIdx] + e.cost) {
                    D[e.to] = D[minIdx] + e.cost;
                }
            }
        }

        System.out.println(D[B]);
    }
}
