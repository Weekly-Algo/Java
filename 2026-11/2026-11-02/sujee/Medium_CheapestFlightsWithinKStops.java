import java.util.*;

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        int INF = Integer.MAX_VALUE;
        int[] cost = new int[n];
        Arrays.fill(cost, INF);
        cost[src] = 0;

        for(int i = 0; i <= k; i++) {
            int[] temp = cost.clone(); // 이전 반복 결과 복사
            for(int[] flight : flights) {
                if(cost[flight[0]] == INF) continue; // 아직 여기는 개척이 안됨
                int tmp = cost[flight[0]] + flight[2];
                if(temp[flight[1]] > tmp) temp[flight[1]] = tmp;
            }
            cost = temp;
        }


        if(cost[dst] == INF) return -1;
        return cost[dst];
    }
}