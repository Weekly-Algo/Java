class Solution {

    public int solution(int n, int[][] computers) {

        // 방문 확인
        boolean[] visited = new boolean[n];

        // 네트워크 개수
        int answer = 0;

        // 모든 컴퓨터를 하나씩 확인
        for (int i = 0; i < n; i++) {

            // 아직 방문하지 않은 컴퓨터라면
            if (!visited[i]) {

                // 연결된 모든 컴퓨터 탐색
                dfs(i, computers, visited);

                // DFS -> 하나의 네트워크
                answer++;
            }
        }

        return answer;
    }

    void dfs(int current, int[][] computers, boolean[] visited) {

        // 현재 컴퓨터 방문 처리
        visited[current] = true;

        // 현재 컴퓨터와 연결된 모든 컴퓨터 확인
        for (int next = 0; next < computers.length; next++) {

            // current와 next가 연결되어 있고
            // 아직 next를 방문하지 않았다면
            if (computers[current][next] == 1 && !visited[next]) {

                // next 컴퓨터로 이동해서 계속 탐색
                dfs(next, computers, visited);
            }
        }
    }
}