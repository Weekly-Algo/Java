import java.util.*;

// 처음에 arrays.sort 썼다가 효율성 문제 발생 -> 우선순위 큐로 바꿈
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        int k = works.length;

        // 일단 다 우선순위 큐에 넣는다 (오름차순)
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int w : works) pq.offer(w);

        // 많이 남은 작업부터 한시간씩 실행
        while(n > 0) {
            if(pq.peek() == 0) {
                return 0;
            }
            int max = pq.poll();
            pq.offer(max - 1);
            n--;
        }

        // 남은 작업량 제곱해서 합산
        for (int i : pq) {
            answer += Math.pow(i, 2);
        }

        return answer;
    }
}