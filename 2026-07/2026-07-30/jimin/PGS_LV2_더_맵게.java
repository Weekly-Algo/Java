import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //오름차순
        for(int spicy : scoville) {
            pq.offer(spicy);
        }

        int answer = 0;
        while(!pq.isEmpty() && pq.peek() < K) {
            if(pq.size() < 2) { //음식을 더 이상 섞을 수 없을 경우
                return -1;
            }

            //섞기
            int first = pq.poll();
            int second = pq.poll();
            pq.offer(first + second * 2);
            answer++;
        }

        return answer;
    }
}