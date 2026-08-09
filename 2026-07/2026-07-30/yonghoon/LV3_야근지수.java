import java.util.*;

class LV3_야근지수 {
    public long solution(int n, int[] works) {
        long answer = 0;

        // 가장 높은 작업량을 1씩 줄이자
        // 이를 위해 우선순위 큐 사용
        PriorityQueue<Integer> pq =
                new PriorityQueue<>(Collections.reverseOrder());

        int totalWork = 0; // 총 작업량
        for(int work : works) {
            pq.offer(work);
            totalWork += work;
        }

        // 총 작업량이 작업시간보다 작거나 같다면 야근지수 0
        if(totalWork <= n)
            return 0;

        // 젤 큰 작업량 꺼내서 1줄이기
        while(n > 0) {
            int maxWork = pq.poll();

            // 1시간 작업
            maxWork--;
            n--;

            if(maxWork != 0)
                pq.offer(maxWork);
        }

        // 야근지수 구하기
        while(!pq.isEmpty()) {
            long work = pq.poll();
            answer += work * work;
        }

        return answer;
    }
}