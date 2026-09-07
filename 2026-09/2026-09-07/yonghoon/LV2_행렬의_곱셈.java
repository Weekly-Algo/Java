class LV2_행렬의_곱셈 {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int row1 = arr1.length;
        int col1 = arr1[0].length;
        int col2 = arr2[0].length;

        // 행렬 곱의 크기는 첫 번재 matrix의 row 와 두 번째 matrix의 col
        int[][] answer = new int[row1][col2];

        // arr1의 row만큼 반복
        for(int i = 0; i < row1; i++) {
            // arr2의 col만큼 반복
            for(int j = 0; j < col2; j++) {
                for(int k = 0; k < col1; k++) {
                    answer[i][j] += arr1[i][k] * arr2[k][j];
                }
            }
        }
        return answer;
    }
}