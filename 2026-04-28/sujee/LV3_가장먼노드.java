import java.util.*;

class Solution {

    static List<Integer>[] graph;
    static int[] dist;

    public int solution(int n, int[][] edge) {
        int answer = 0;
        dist = new int[n+1];

        graph = new ArrayList[n+1];
        for(int i = 0; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < edge.length; i++) {
            graph[edge[i][0]].add(edge[i][1]);
            graph[edge[i][1]].add(edge[i][0]);
        }

        bfs(1);

        int max = 0;
        for(int i = 1; i <= n; i++){
            max = Math.max(max, dist[i]);
        }

        for(int i = 1; i <= n; i++){
            if(dist[i] == max) answer++;
        }

        return answer;
    }

    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(start);
        dist[start] = 1;

        while(!q.isEmpty()) {
            int curr = q.poll();

            for(int i = 0; i < graph[curr].size(); i++){
                int next = graph[curr].get(i);

                if(dist[next] != 0) continue;

                dist[next] = dist[curr] + 1;
                q.offer(next);
            }
        }
    }
}