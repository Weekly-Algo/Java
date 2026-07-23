import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        
        // 컴퓨터 방문 배열
        boolean[] visited = new boolean[n];
        
        int answer = 0;
        
        
        for(int i = 0; i < n; i++){
            
            // 방문하지 않았다면 새로운 네트워크 
            if(!visited[i]){
                bfs(i, computers, visited);
                answer++;
            }
        }
        return answer;
    }
    
    public void bfs(int start, int[][] computers, boolean[] visited){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        // 시작 컴퓨터를 큐에 넣고 방문 처리
        q.offer(start);
        visited[start] = true;
        
        // 큐가 빌 때까지 반복
        while(!q.isEmpty()){
            int cur = q.poll();
            
            // 현재 컴퓨터와 연결된 컴퓨터 확인
            for(int next = 0; next < computers.length; next++){
                // 연결되지 않았거나 이미 방문했으면 건너뜀
                if(computers[cur][next] == 0 || visited[next]){
                    continue;
                }
                // 연결된 컴퓨터 방문 처리
                visited[next] = true;
                // 연결된 컴퓨터와 이어진 또 다른 컴퓨터도 확인하기 위해서 큐에 추가
                q.offer(next);
            }
        }
    }
}