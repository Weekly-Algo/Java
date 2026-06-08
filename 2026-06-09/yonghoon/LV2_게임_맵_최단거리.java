import java.util.*;

class Solution {
    static class Pos {
        int x;
        int y;
        int cnt;
        public Pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];

        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0, 1));
        visited[0][0] = true;

        while(!q.isEmpty()) {
            Pos cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int cnt = cur.cnt;

            // 도착
            if(x == n - 1 && y == m - 1) return cnt;

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 못가는 곳 체크
                if(nx < 0 || ny < 0 || nx >= n || ny >= m || maps[nx][ny] == 0 || visited[nx][ny]) continue;

                q.offer(new Pos(nx, ny, cnt + 1));
                visited[nx][ny] = true;
            }
        }
        return -1;
    }
}