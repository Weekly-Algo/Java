import java.util.*;

class LV3_가장_먼_노드 {
    static List<Integer>[] graph;
    static boolean[] visited;
    static int[] dist;
    public int solution(int n, int[][] edge) {
        // 그래프 생성
        graph = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];

            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new boolean[n + 1]; // 방문여부
        dist = new int[n + 1]; // 거리배열
        bfs();

        // 가장 멀리 떨어진 노드의 개수
        int max = 0;
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (dist[i] > max) {
                max = dist[i];
                answer = 1;
            } else if (dist[i] == max) {
                answer++;
            }
        }
        return answer;
    }

    // bfs
    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {1, 0});
        visited[1] = true;
        // 1부터 시작
        dist[1] = 0;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0];
            int distance = curr[1];

            for(int i = 0; i < graph[node].size(); i++) {
                int next = graph[node].get(i);
                // 방문했으면 건너뜀
                if(visited[next]) continue;

                visited[next] = true;
                dist[next] = distance + 1;
                q.offer(new int[] {next, distance + 1});
            }
        }
    }
}