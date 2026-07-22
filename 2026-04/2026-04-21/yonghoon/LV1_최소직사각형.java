public class LV1_최소직사각형 {
    public int solution(int[][] sizes) {
        for (int i = 0; i < sizes.length; i++) {
            // 앞에 수가 더 작으면 교체
            int front = sizes[i][0];
            int back = sizes[i][1];
            if (front < back) {
                sizes[i][0] = back;
                sizes[i][1] = front;
            }
        }

        // sizes[i][0]의 최댓값과 sizes[i][1]의 최댓값 구하기
        int frontMax = 0;
        int backMax = 0;
        for (int i = 0; i < sizes.length; i++) {
            frontMax = Math.max(frontMax, sizes[i][0]);
            backMax = Math.max(backMax, sizes[i][1]);
        }

        int answer = frontMax * backMax;
        return answer;
    }
}

