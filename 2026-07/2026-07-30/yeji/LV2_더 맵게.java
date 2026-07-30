import java.util.*;

class Solution {

    public int solution(int[] scoville, int K) {

        // 스코빌 지수가 작은 순서대로 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int scov : scoville){
            pq.offer(scov);
        }

        int answer = 0;

        while(pq.peek() < K){

            // 음식이 하나만 남았으면 -1 반환
            if(pq.size() < 2){
                return -1;
            }

            int first = pq.poll();
            int second = pq.poll();
            int mixed = first + second * 2;
            
            pq.offer(mixed);

            answer++;
        }

        return answer;
    }
}