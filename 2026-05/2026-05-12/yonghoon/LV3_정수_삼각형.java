import java.util.*;

class LV3_정수_삼각형 {
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < triangle[i].length; j++) {
                // 맨 왼쪽
                if(j == 0)
                    triangle[i][j] = triangle[i][j] + triangle[i - 1][j];
                    // 맨 오른쪽
                else if(j == i)
                    triangle[i][j] = triangle[i][j] + triangle[i - 1][j - 1];
                else
                    triangle[i][j] = triangle[i][j] + Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            }
        }

        // 최댓값 구하기
        for(int i = 0; i < n; i++)
            answer = Math.max(answer, triangle[n - 1][i]);

        return answer;
    }
}