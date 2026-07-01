import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        Deque<Integer> deque = new ArrayDeque<>();
        
        // 작업별 남은 일수 계산
        for (int i = 0; i < progresses.length; i++) {
            
            int remain = 100 - progresses[i];
            int day = remain / speeds[i];
            
            if (remain % speeds[i] != 0) {
                day++;
            }
            
            deque.offer(day);
        }
        
        ArrayList<Integer> answer = new ArrayList<>();
        
        // 배포 묶음 계산
        while (!deque.isEmpty()) {
            
            int standard = deque.poll();
            int count = 1;
            
            // 앞 작업보다 빨리 끝나는 작업 함께 배포
            while (!deque.isEmpty() && deque.peek() <= standard) {
                deque.poll();
                count++;
            }
            
            answer.add(count);
        }
        
        int[] result = new int[answer.size()];
        
        // ArrayList를 배열로 변환
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }
        
        return result;
    }
}