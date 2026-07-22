import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int maxW = 0; //가장 킨 가로
        int maxH = 0; //가장 긴 세로

        //큰 값들 가로로 몰아주고
        //작은 값들 세로로 몰아잇
        for (int i=0; i<sizes.length; i++) {
            int w = Math.max(sizes[i][0], sizes[i][1]);
            int h = Math.min(sizes[i][0], sizes[i][1]);

            maxW = Math.max(maxW, w);
            maxH = Math.max(maxH, h);
        }

        return maxW * maxH;
    }
}