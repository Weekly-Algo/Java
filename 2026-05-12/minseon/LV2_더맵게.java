import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        //우선순위 큐 호달달 이거 처음 써봐
        //오름차순 최소 힙 -> 반환할 때 가장 작은 값부터
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        //일단 큐에 해당하는 스코빌 지수 집어넣어
        for (int s : scoville) {
            pq.offer(s);
        }

        int count = 0;

        //스코빌 지수보다 작으면 꺼내
        while (pq.size() >= 2 && pq.peek() < K) {
            int first = pq.poll();
            int second = pq.poll();

            int mix = first + (second * 2);

            pq.offer(mix); //새로운 값 집어넣고

            count++; //섞는 횟수 +1
        }

        //가장 작은 값이 K보다 크면 결과 반환 가능
        if(pq.peek() >= K) {
            return count;
        }

        return -1;
    }
}