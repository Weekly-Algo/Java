import java.util.*;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        int top = 0;
        int bottom = matrix.length - 1;

        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {

            // 1. 왼쪽 → 오른쪽
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }

            // 위쪽 한 줄을 다 봤으므로 경계 이동
            top++;

            // 2. 위쪽 → 아래쪽
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }

            // 오른쪽 한 줄을 다 봤으므로 경계 이동
            right--;

            // 아직 아래쪽 행이 남아 있는 경우
            if (top <= bottom) {

                // 3. 오른쪽 → 왼쪽
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }

                // 아래쪽 경계 이동
                bottom--;
            }

            // 아직 왼쪽 열이 남아 있는 경우
            if (left <= right) {

                // 4. 아래쪽 → 위쪽
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }

                // 왼쪽 경계 이동
                left++;
            }
        }

        return result;
    }
}