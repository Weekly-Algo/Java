import java.util.*;

class Solution {

    public long solution(int n, int[] works) {

        // 작업량이 큰 순서대로 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // 모든 작업량을 우선순위 큐에 추가
        for(int work : works){
            pq.offer(work);
        }

        // 남은 시간이 없을 때까지 반복
        while(n > 0){

            // 가장 큰 작업량 꺼내기
            int max = pq.poll();

            // 가장 큰 작업량이 0이면 종료
            if(max == 0){
                break;
            }

            // max에서 1을 빼고 다시 큐에 추가
            pq.offer(max - 1);

            n--;
        }

        long answer = 0;

        // 남은 작업량을 각각 제곱해서 더하기
        while(!pq.isEmpty()){

            long work = pq.poll();

            answer += work * work;
        }

        return answer;
    }
}