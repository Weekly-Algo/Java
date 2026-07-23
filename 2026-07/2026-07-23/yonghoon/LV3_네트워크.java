import java.util.*;

class LV3_네트워크 {
    public int solution(int n, int[][] computers) {
        int answer = 0; // 네트워크 개수
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();

        // 네트워크 체크(BFS)
        for(int i = 0; i < n; i++) {
            // 방문했었다면 pass
            if(visited[i]) continue;

            q.offer(i);
            visited[i] = true;

            while(!q.isEmpty()) {
                int cur = q.poll();

                // 연결된 컴퓨터 체크
                for(int j = 0; j < n; j++) {
                    // 연결되어 있고 방문 안했으면 큐에 넣고 방문처리
                    if(computers[cur][j] == 1 && !visited[j]) {
                        q.offer(j);
                        visited[j] = true;
                    }
                }
            }
            // 다 돌았으면 네트워크 개수++
            answer++;
        }
        return answer;
    }
}