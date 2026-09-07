import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        int rows = matrix.length;
        int cols = matrix[0].length;

        // 방문 체크
        boolean[][] visited = new boolean[rows][cols];

        int[] dr = {0, 1, 0, -1}; // 오->아->왼->위 행
        int[] dc = {1, 0, -1, 0}; // 오->아->왼->위 열

        int row = 0, col = 0;
        int dir = 0; // 현재 방향 인덱스 (0:오른쪽)

        for (int i=0; i<rows * cols; i++) {
            // 현재 칸을 결과에 담고 방문 쳌
            result.add(matrix[row][col]);
            visited[row][col] = true;

            // 현재 방향으로 한 칸 이동했을 때의 좌표 계산
            int nextRow = row + dr[dir];
            int nextCol = col + dc[dir];

            // 다음 칸이 범위를 벗어났거나, 이미 방문한 칸이면 방향 전환
            if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols || visited[nextRow][nextCol]) {
                dir = (dir+1) % 4; // 다음 방향
                nextRow = row + dr[dir];
                nextCol = col + dc[dir];
            }

            // 위치 갱신
            row = nextRow;
            col = nextCol;
        }

        return result;
    }
}