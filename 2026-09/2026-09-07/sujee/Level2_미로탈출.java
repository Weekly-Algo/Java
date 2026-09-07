import java.util.*;

class Solution {
    static String[] map;
    static int n, m;
    // 사방탐색용
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public int solution(String[] maps) {
        map = maps;
        n = map.length;
        m = map[0].length();

        // S,L,E 각각의 위치를 저장해둔다
        int[] S = find('S');
        int[] L = find('L');
        int[] E = find('E');

        int a = bfs(S, L); // 스타트 - 레버 bfs 실행 (레버 누르기용)
        int b = bfs(L, E); // 레버 - 출구 bfs 실행 (빠져나가기!)

        if (a == -1 || b == -1)
            return -1;

        return a + b;
    }

    static int[] find(char target) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (map[i].charAt(j) == target)
                    return new int[]{i, j};

        return null;
    }

    static int bfs(int[] start, int[] end) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        q.offer(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];
            int dist = cur[2]; // 현재까지 움직인 칸 수

            if (x == end[0] && y == end[1])
                return dist;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                // 벽이 아니면서 방문하지 않은곳만 탐색!
                if (nx >= 0 && nx < n && ny >= 0 && ny < m
                        && map[nx].charAt(ny) != 'X'
                        && !visited[nx][ny]) {

                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny, dist + 1});
                }
            }
        }

        return -1;
    }
}