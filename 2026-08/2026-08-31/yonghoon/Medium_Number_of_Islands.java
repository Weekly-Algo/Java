import java.util.*;

class Medium_Number_of_Islands {
    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int numIslands(char[][] grid) {
        int answer = 0; // 섬개수
        int n = grid.length;
        int m = grid[0].length;
        int[] dx = {-1, 1, 0, 0}; // 사방탐색용
        int[] dy = {0, 0, -1, 1};
        boolean[][] visited = new boolean[n][m]; // 방문배열
        Queue<Pos> q = new ArrayDeque<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                // 물이거나 방문했던 곳이면 패스
                if(grid[i][j] == '0' || visited[i][j])
                    continue;
                    // 땅이면 bfs
                else {
                    q.offer(new Pos(i, j));
                    visited[i][j] = true;

                    // bfs
                    while(!q.isEmpty()) {
                        Pos cur = q.poll();
                        int x = cur.x;
                        int y = cur.y;

                        // 사방탐색
                        for(int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];

                            // 범위, 물, 방문체크
                            if(nx < 0 || ny < 0 || nx >= n || ny >= m || grid[nx][ny] == '0' || visited[nx][ny])
                                continue;

                            q.offer(new Pos(nx, ny));
                            visited[nx][ny] = true;
                        }
                    }

                    // 섬 추가
                    answer++;
                }
            }
        }

        return answer;
    }
}