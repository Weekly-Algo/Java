import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    public int numIslands(char[][] grid) {
        // 그리드가 비어있으면 섬이 있을 수 없으니 바로 0
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // 그리드 전체 순회
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                // 육지('1')를 만나면 새로운 섬을 발견
                if (grid[r][c] == '1') {
                    count++;
                    grid[r][c] = '0'; // 방문 처리

                    // BFS 
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{r, c});

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        int curR = cur[0];
                        int curC = cur[1];

                        for (int d=0; d<4; d++) {
                            int nr = curR + dr[d];
                            int nc = curC + dc[d];

                            
                            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == '1') {
                                grid[nr][nc] = '0'; 
                                queue.offer(new int[]{nr, nc}); 
                            }
                        }
                    }
                }
            }
        }

        return count;
    }
}