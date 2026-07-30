import java.util.PriorityQueue;

class Solution {
    //작업량이 큰 것부터 일하는 것이 좋다!
    public long solution(int n, int[] works) {
        //내림차순 PQ 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        
        for(int i = 0; i < works.length; i++) {
            pq.offer(works[i]);
        }

        //남은 퇴근 시간까지 일하기
        for(int i = 0; i < n; i++) {
            int num = pq.poll();
            if(num == 0) return 0;
            pq.offer(num - 1);
        }

        //피로도 계산하기
        long result = 0;
        for(int i = 0; i < works.length; i++) {
            long num = pq.poll();
            result += num * num;
        }

        return result;
    }
}