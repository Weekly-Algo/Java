import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_1707_이분그래프 {
    static List<Integer>[] graph;
    static int[] color; // 색깔을 칠해서 확인해보기!

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine()); // 테스트케이스

        for (int t = 0; t < K; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); // 정점 개수
            int E = Integer.parseInt(st.nextToken()); // 간선 개수

            graph = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) {
                graph[i] = new ArrayList<>();
            } // 리스트 초기화

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[u].add(v);
                graph[v].add(u);
            } // 그래프 입력 끝!

            color = new int[V + 1];
            boolean answer = true;

            for (int i = 1; i <= V; i++) {
                if (color[i] == 0) { // 방문 안한 정점이 있다면 -> 그래프가 끊긴경우만 반복
                    if (!dfs(i, 1)) { // 새로 시작하는 점을 1로 색칠하고 dfs 반복
                        answer = false;
                        break;
                    }
                }
            }

            if (answer) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static boolean dfs(int now, int c) {
        color[now] = c;

        for (int next : graph[now]) {
            if (color[next] == 0) { // 아직 방문하지 않은곳은 반대 색으로 칠하면서 반복
                if (!dfs(next, -c)) {
                    return false;
                }
            } else if (color[next] == color[now]) {
                return false;
            }
        }

        return true;
    }
}