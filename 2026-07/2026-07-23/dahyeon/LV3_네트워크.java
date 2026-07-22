import java.util.*;
// n 은 컴퓨터 갯수
// 방문 배열을 1차원으로 해야겠다 -> 컴퓨터는 한 행을 차지 하니까

class Solution {
    public int solution(int n, int[][] computers) {

        boolean[] visited = new boolean[n];
        int cnt = 0;

        for(int i = 0; i < n; i++){
            if(visited[i]) continue;

            bfs(i, n, computers, visited);
            cnt++;
        }

        return cnt;
    }

    static void bfs(int start, int n , int[][] computers, boolean[] visited){

        Queue<Integer> q = new ArrayDeque<>();

        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()){
            int curr = q.poll();

            for(int next = 0; next < n; next++){
                if(computers[curr][next] == 1 && !visited[next]){

                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}