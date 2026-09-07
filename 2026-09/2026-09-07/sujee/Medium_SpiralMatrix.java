// 후기 : 방법이 어렵진 않은데 항상 이런애들은 인덱스가 헷갈리더라아~

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> answer = new ArrayList<>();

        int top = 0; // 현재 탐색할 영역의 위쪽 행
        int bottom = matrix.length - 1; // 현재 탐색할 영역 아래쪽 행
        int left = 0; // 현재 탐색할 영역의 왼쪽 열
        int right = matrix[0].length - 1; // 현재 탐색할 영역의 오른쪽 열

        while (top <= bottom && left <= right) {

            // 왼쪽 -> 오른쪽
            for (int i = left; i <= right; i++)
                answer.add(matrix[top][i]);
            top++; // 위쪽 행 처리했으니까 행 아래로 이동!

            // 오른쪽 열 위 -> 아래
            for (int i = top; i <= bottom; i++)
                answer.add(matrix[i][right]);
            right--;

            // 아래쪽 행 오 -> 왼
            if (top <= bottom) {
                for (int i = right; i >= left; i--)
                    answer.add(matrix[bottom][i]);
                bottom--;
            }

            // 왼쪽열 아래 -> 위
            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    answer.add(matrix[i][left]);
                left++;
            }
        }

        return answer;
    }
}