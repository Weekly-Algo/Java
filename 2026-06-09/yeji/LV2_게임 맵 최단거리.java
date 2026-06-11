import java.util.*;

class Solution {

    public int solution(int[][] maps) {

        int n = maps.length;
        int m = maps[0].length;

        // 방문 여부 저장 배열
        boolean[][] visited = new boolean[n][m];

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        ArrayDeque<int[]> deque = new ArrayDeque<>();
        
        // 시작 위치 추가
        deque.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!deque.isEmpty()) {
            
            // 현재 위치 꺼내기
            int[] current = deque.poll();

            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 체크
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                // 벽이거나 이미 방문
                if (maps[nx][ny] == 0 || visited[nx][ny]) {
                    continue;
                }
                
                // 방문 처리
                visited[nx][ny] = true;

                // 현재 거리 + 1 저장
                maps[nx][ny] = maps[x][y] + 1;
                
                // 다음 탐색 위치 큐에 추가
                deque.offer(new int[]{nx, ny});
            }
        }

        // 도착하지 못한 경우
        if (maps[n - 1][m - 1] == 1) {
            return -1;
        }
        
        // 최단 거리 반환
        return maps[n - 1][m - 1];
    }
}