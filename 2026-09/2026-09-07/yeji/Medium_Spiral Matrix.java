class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> answer = new ArrayList<>();

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // 위쪽:  왼 -> 오
            for (int col = left; col <= right; col++) {
                answer.add(matrix[top][col]);
            }
            top++;

            // 오른쪽: 위 -> 아래
            for (int row = top; row <= bottom; row++) {
                answer.add(matrix[row][right]);
            }
            right--;

            // 아래쪽: 오 -> 왼
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    answer.add(matrix[bottom][col]);
                }
                bottom--;
            }

            // 왼쪽: 아래 -> 위
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    answer.add(matrix[row][left]);
                }
                left++;
            }
        }

        return answer;
    }
}