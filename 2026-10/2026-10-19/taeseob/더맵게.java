import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < scoville.length; i++) pq.offer(scoville[i]);
        while(pq.peek() < K && pq.size() > 1){
            int mostLow = pq.poll();
            int low = pq.poll();
            pq.offer(mostLow+(low*2));
            answer++;
        }
        if(pq.size()<2) if(pq.peek()<K)return -1;
        
        return answer;
    }
}