import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;

        int[] ans = new int[n]; //정답 저장 배열

        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                ans[i]++; //디폴트 1

                if(prices[i] > prices[j]) {
                    break;
                }
            }
        }
        return ans;
    }
}