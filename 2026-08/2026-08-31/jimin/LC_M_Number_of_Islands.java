import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    static int m, n;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;
    static int count;
    public int numIslands(char[][] grid) {
        m = grid.length; //행
        n = grid[0].length; //열
        visited = new boolean[m][n];
        count = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(!visited[i][j] && grid[i][j] == '1') {
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public void bfs(char[][] grid, int startR, int startC) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {startR, startC});
        visited[startR][startC] = true;

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nr = curr[0] + dr[i];
                int nc = curr[1] + dc[i];

                if(nr < 0 || nc < 0 || nr >= m || nc >= n) continue; //범위를 벗어났다면
                if(visited[nr][nc]) continue; //방문했다면
                if(grid[nr][nc] == '0') continue; //0이라면
                queue.offer(new int[] {nr, nc});
                visited[nr][nc] = true;
            }
        }

    }
}