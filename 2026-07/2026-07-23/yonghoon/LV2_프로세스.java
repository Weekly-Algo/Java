import java.util.*;

class LV2_프로세스 {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<int[]> q = new ArrayDeque<>();

        // 우선 큐에 다 넣기
        for(int i = 0; i < priorities.length; i++) {
            q.offer(new int[]{priorities[i], i});
        }

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            boolean hasHigher = false; // 큐 내부에 더 높은 우선순위의 프로세스가 있는지 여부

            // 큐 내부를 보면서 우선순위가 더 높은게 있는지 체크
            for(int[] process : q) {
                if(process[0] > cur[0]) {
                    hasHigher = true;
                    break;
                }
            }

            // 더 높은게 있다면 다시 큐에 집어넣기
            // 아니라면 실행순서 +1
            if(hasHigher) {
                q.offer(cur);
            } else {
                answer++;

                // 우리가 찾는 프로세스라면 종료하기
                if(cur[1] == location)
                    break;
            }
        }
        return answer;
    }
}