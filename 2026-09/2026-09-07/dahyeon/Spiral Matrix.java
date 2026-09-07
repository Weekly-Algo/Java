import java.util.*;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        int m = matrix.length;
        int n = matrix[0].length;

        boolean[][] visited = new boolean[m][n];
        // 우 하 좌 상
        // 행
        int[] dr = {0, 1, 0, -1};
        // 열
        int[] dc = {1, 0, -1, 0};

        // 방향
        int dir = 0;

        int r = 0;
        int c = 0;

        for (int i = 0; i < m * n; i++) {
            result.add(matrix[r][c]);
            visited[r][c] = true;

            int nr = r + dr[dir];
            int nc = c + dc[dir];

            // 범위를 벗어나거나 이미 방문했으면 방향 전환
            if (nr < 0 || nr >= m || nc < 0 || nc >= n || visited[nr][nc]) {
                // 계속 순환하기 위해서
                dir = (dir + 1) % 4;

                nr = r + dr[dir];
                nc = c + dc[dir];
            }

            r = nr;
            c = nc;
        }

        return result;
    }
}