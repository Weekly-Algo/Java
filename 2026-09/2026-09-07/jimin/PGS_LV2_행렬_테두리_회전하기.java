class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        // 쿼리마다 최솟값을 저장할 배열
        int[] answer = new int[queries.length];

        //행렬 생성
        int[][] matrix = new int[rows][columns];
        
        //1부터 숫자 채우기
        int num = 1;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                matrix[i][j] = num;
                num++; 
            }
        }

        for(int i = 0; i < queries.length; i++) {

            int x1 = queries[i][0] - 1;
            int y1 = queries[i][1] - 1;
            int x2 = queries[i][2] - 1;
            int y2 = queries[i][3] - 1;

            //왼쪽 위 숫자 잠시 보관
            int temp = matrix[x1][y1];

            //최솟값 찾기
            int min = temp;

            //위쪽: 현재 칸을 왼쪽 칸의 숫자로 덮어씌우기
            for(int col = y1; col < y2; col++) {
                int next = matrix[x1][col + 1];
                matrix[x1][col + 1] = temp;
                temp = next;
                min = Math.min(min, matrix[x1][col + 1]);
            }

            //오른쪽: 현재 칸을 위쪽 칸의 숫자로 덮어씌우기
            for(int row = x1; row < x2; row++) {
                int next = matrix[row + 1][y2];
                matrix[row + 1][y2] = temp;
                temp = next;
                min = Math.min(min, matrix[row + 1][y2]);
            }

            //아래쪽: 현재 칸을 오른쪽 칸의 숫자로 덮어씌우기
            for(int col = y2; col > y1; col--) {
                int next = matrix[x2][col - 1];
                matrix[x2][col - 1] = temp;
                temp = next;
                min = Math.min(min, matrix[x2][col - 1]);
            }

            //왼쪽: 현재 칸을 아래쪽 칸의 숫자로 덮어씌우기
            for(int row = x2; row > x1; row--) {
                int next = matrix[row - 1][y1];
                matrix[row - 1][y1] = temp;
                temp = next;
                min = Math.min(min, matrix[row - 1][y1]);
            }

            //이번쿼리의 최솟값 저장
            answer[i] = min;
        }


        return answer;
    }
}