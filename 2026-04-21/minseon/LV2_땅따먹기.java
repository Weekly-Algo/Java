import java.util.*;

class Solution {
    int solution(int[][] land) {
        int n = land.length;

        //dp 사용 -> 해당 땅을 먹었을 때 점수가 누적

        //해당 열 골랐을 때 경우의 수
        for(int i=1; i<n; i++) {
            land[i][0] += Math.max(Math.max(land[i-1][1], land[i-1][2]), land[i-1][3]);
            land[i][1] += Math.max(Math.max(land[i-1][0], land[i-1][2]), land[i-1][3]);
            land[i][2] += Math.max(Math.max(land[i-1][0], land[i-1][1]), land[i-1][3]);
            land[i][3] += Math.max(Math.max(land[i-1][0], land[i-1][1]), land[i-1][2]);
        }

        return Math.max(Math.max(land[n-1][0], land[n-1][1]), Math.max(land[n-1][2], land[n-1][3]));
    }
}