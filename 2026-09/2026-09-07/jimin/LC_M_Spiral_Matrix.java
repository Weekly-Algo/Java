import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        //상하좌우
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while(top <= bottom && left <= right) {
            //위쪽
            for(int col = left; col <= right; col++) {
                result.add(matrix[top][col]);        
            }
            top++;

            //오른쪽
            for(int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--;

            //아래쪽
            if(top <= bottom) { //아직 남아있는지 확인
                for(int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--;
            }

            //왼쪽
            if(left <= right) { //아직 남아있는지 확인
                for(int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++;
            }
        }

        return result;
    }
}