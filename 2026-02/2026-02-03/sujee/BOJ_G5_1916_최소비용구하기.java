import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G5_1916_최소비용구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<int[]>[] graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[a].add(new int[]{b, dist});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        int[] dijkstra = new int[N + 1];
        Arrays.fill(dijkstra, Integer.MAX_VALUE);
        dijkstra[start] = 0;
        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i < N; i++) {

            int idx = -1;
            int minCost = Integer.MAX_VALUE;

            for (int j = 1; j <= N; j++) {
                if (!visited[j] && dijkstra[j] < minCost) {
                    minCost = dijkstra[j];
                    idx = j;
                }
            }

            if (idx == -1) break;

            visited[idx] = true;

            for (int[] edge : graph[idx]) {
                int nextNode = edge[0];
                int weight = edge[1];

                if (dijkstra[idx] + weight < dijkstra[nextNode]) {
                    dijkstra[nextNode] = dijkstra[idx] + weight;
                }
            }
        }

        System.out.println(dijkstra[dest]);
    }
}