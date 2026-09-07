import java.util.*;

class Medium_Spiral_Matrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> answer = new ArrayList<>();

        int n = matrix.length; // 행
        int m = matrix[0].length; // 열

        // 우 - 0 / 하 - 1 / 좌 - 2 / 상 - 3
        int dir = 0; // 시작은 우측으로 가는것부터
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        // 현재위치
        int x = 0;
        int y = 0;

        // 방문여부
        boolean[][] visited = new boolean[n][m];

        for(int i = 0; i < n * m; i++) {
            answer.add(matrix[x][y]);
            visited[x][y] = true;

            // 다음위치
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 범위 벗어나거나 방문 했던 곳이면 방향 바꾸기(90도 회전)
            if(nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) {
                dir = (dir + 1) % 4;

                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            // 위치 갱신
            x = nx;
            y = ny;
        }

        return answer;
    }
}