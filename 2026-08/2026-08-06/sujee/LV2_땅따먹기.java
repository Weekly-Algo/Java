class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        int n = land.length;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                int max = 0;
                // 바로 위 행에서, 같은 열(j)만 빼고 나머지 중 최댓값 찾기
                for (int k = 0; k < 4; k++) {
                    if (k == j) continue;
                    max = Math.max(max, land[i - 1][k]);
                }
                land[i][j] += max; // 지금 칸 점수에 누적
            }
        }


        for (int j = 0; j < 4; j++) {
            answer = Math.max(answer, land[n - 1][j]);
        }

        return answer;
    }
}