import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        // 인접 리스트 그래프 초기화
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 양방향 간선 정보 매핑
        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        
        // 거리 저장 배열 초기화
        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1);
        
        // BFS 탐색을 위한 큐 설정
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1); // 1번 노드부터 시작
        distance[1] = 0; // 시작 노드의 거리 0
        
        // BFS
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            // 현재 노드와 연결된 인접 노드들을 순회
            for (int next : graph.get(current)) {
                // 아직 방문하지 않은 노드라면
                if (distance[next] == -1) {
                    distance[next] = distance[current] + 1; // 이전 노드 거리 + 1
                    queue.add(next);
                }
            }
        }
        
        // 가장 먼 노드(최대 거리) 찾기 및 개수 카운트
        int maxDist = 0;
        for (int dist : distance) {
            if (dist > maxDist) {
                maxDist = dist;
            }
        }
        
        int answer = 0;
        for (int dist : distance) {
            if (dist == maxDist) {
                answer++;
            }
        }
        
        return answer;
    }
}