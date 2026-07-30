import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int totalWork = 0;

        for (int work : works) {
            maxHeap.offer(work);
            totalWork += work;
        }

        // 모든 일을 끝낼 수 있는 경우
        if (totalWork <= n) {
            return 0;
        }

        // 가장 큰 작업량을 하나씩 줄인다.
        while (n > 0) {
            int maxWork = maxHeap.poll();
            maxHeap.offer(maxWork - 1);
            n--;
        }

        long answer = 0;

        while (!maxHeap.isEmpty()) {
            long work = maxHeap.poll();
            answer += work * work;
        }

        return answer;
    }
}