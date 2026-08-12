import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        // 가장 큰 작업량이 먼저 나오도록 최대 힙
        // ex) [3, 3, 4] 이라면 4부터 나오는 거지요
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        // 작업량 힙에 차례대로 넣어
        for (int work : works) {
            maxHeap.offer(work);
        }

        // 남은 n시간 동안 작업 수행
        while (n > 0 && !maxHeap.isEmpty()) {
            // 현재 가장 큰 작업량부터 꺼냄
            int maxWork = maxHeap.poll();

            // 가장 큰 작업량이 0이면 모든 작업 끝난 상태
            if (maxWork == 0) {
                break;
            }

            // 작업량 1 줄이고 다시 힙에 저장
            maxHeap.offer(maxWork - 1);
            n--;
        }

        // 남은 작업량으로 제곱 구함
        long ans = 0;

        while (!maxHeap.isEmpty()) {
            long work = maxHeap.poll();
            ans += work * work;
        }

        return ans;
    }
}