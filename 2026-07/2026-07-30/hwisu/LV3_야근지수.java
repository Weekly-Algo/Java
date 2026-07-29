import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        long totalWork = 0;
        for (int work : works) {
            maxHeap.offer(work);
            totalWork += work;
        }
        
        // 전체 작업량이 n보다 작거나 같으면 야근할 필요 없이 다 끝낼 수 있음
        if (totalWork <= n) {
            return 0;
        }
        
        // n시간 동안 가장 큰 작업을 하나씩 1만큼 줄인다
        for (int i = 0; i < n; i++) {
            int max = maxHeap.poll();
            if (max == 0) {
                // 모든 작업량이 0이면 더 줄일 게 없음
                maxHeap.offer(0);
                break;
            }
            maxHeap.offer(max - 1);
        }
        
        // 남은 작업량들의 제곱합 계산
        long answer = 0;
        for (int work : maxHeap) {
            answer += (long) work * work;
        }
        
        return answer;
    }
}