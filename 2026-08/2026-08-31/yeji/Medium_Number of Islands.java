import java.util.*;

class Solution {
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];

        // 상 하 좌 우
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int count = 0;

        // 모든 칸 확인
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                // 아직 방문하지 않은 땅이면 새로운 섬
                if (grid[r][c] == '1' && !visited[r][c]) {
                    count++;

                    Deque<int[]> q = new ArrayDeque<>();
                    q.offer(new int[]{r, c});
                    visited[r][c] = true;

                    // 현재 섬과 연결된 모든 땅 탐색
                    while (!q.isEmpty()) {
                        int[] curr = q.poll();

                        int currR = curr[0];
                        int currC = curr[1];

                        // 상하좌우 확인
                        for (int d = 0; d < 4; d++) {
                            int nextR = currR + dr[d];
                            int nextC = currC + dc[d];

                            // 범위를 벗어나면 건너뛰기
                            if (nextR < 0 || nextR >= rows ||
                                nextC < 0 || nextC >= cols) {
                                continue;
                            }

                            // 땅이고 아직 방문하지 않았다면 큐에 추가
                            if (grid[nextR][nextC] == '1'
                                    && !visited[nextR][nextC]) {
                                visited[nextR][nextC] = true;
                                q.offer(new int[]{nextR, nextC});
                            }
                        }
                    }
                }
            }
        }

        return count;
    }
}