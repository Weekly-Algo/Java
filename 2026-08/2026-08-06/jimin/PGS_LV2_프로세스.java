import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); //내림차순

        for(int i = 0; i < priorities.length; i++) {
            queue.offer(new int[]{i, priorities[i]});
            pq.offer(priorities[i]); //중요도만 넣기
        }

        while(!queue.isEmpty()) {
            int[] arr = queue.poll();
            if(arr[1] == pq.peek()) {
                pq.poll();
                answer++;

                if(arr[0] == location) {
                    break;
                }
                
            } else {
                queue.offer(arr);
            }
        }

        return answer;
    }
}