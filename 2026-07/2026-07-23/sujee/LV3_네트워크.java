import java.util.*;

class Solution {

    static int n;
    static int[][] computers;
    static boolean[] visited; // 방문확인용

    public int solution(int n, int[][] computers) {

        int answer = 0;
        Solution.n = n;
        Solution.computers = computers;
        visited = new boolean[n];

        // 모든 컴퓨터들을 돌면서 bfs실행
        for(int i = 0; i < n; i++) {
            if(visited[i]) continue; // 이미 방문한 컴퓨터는 확인할 필요 없음
            bfs(i);
            answer++; // 한바퀴 돌때마다 +1
        }

        return answer;
    }

    static void bfs(int idx) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(idx);
        visited[idx] = true;
        while(!q.isEmpty()){
            int curr = q.poll();
            for(int i = 0; i < n; i++) {
                if(visited[i]) continue;
                if(computers[curr][i] == 0) continue;
                q.offer(i);
                visited[i] = true;
            }
        }
    }
}