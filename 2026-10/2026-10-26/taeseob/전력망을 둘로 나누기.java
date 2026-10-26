import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = 9999;
        List<Integer>[] list = new ArrayList[n+1];
        for(int i = 0 ; i < list.length; i++) list[i] = new ArrayList<>();
        for(int i = 0; i < wires.length; i++){
            int from = wires[i][0];
            int to = wires[i][1];
            list[from].add(to);
            list[to].add(from); // 양 방향으로 이어져 있는 인접리스트
        }
        for(int i = 1 ; i < list.length; i++){
            for(int j = 0; j < list[i].size(); j++){
                int tmp =  bfs(list, i, j);
                int compare = Math.abs(Math.abs(n - tmp) - tmp);
                answer = Math.min(answer, compare);     
            }
        }
        
        return answer;
        
    }
    public int bfs(List<Integer>[] list, int start, int cut){ //bfs result 반환
        Deque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[list.length];
        int result = 1;  //담을 결과값
        q.offer(start);
        visited[start] = true;
        visited[list[start].get(cut)] = true; //잘린 곳 방문처리
        while(!q.isEmpty()){
            int curr = q.poll();
            for(int i = 0 ; i < list[curr].size(); i++){
                if(!visited[list[curr].get(i)]) {
                    visited[list[curr].get(i)] = true;
                    result++;
                    q.offer(list[curr].get(i));
                }
            }
        }
        return result;        
    }
    
}