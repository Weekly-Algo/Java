import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Deque<int[]> q = new ArrayDeque<>();

        // [원래 위치, 우선순위] 큐에 넣기
        for (int i = 0; i < priorities.length; i++) {
            q.offer(new int[]{i, priorities[i]});
        }

        int count = 0;

        while (!q.isEmpty()) {
            //현재 프로세스 꺼내기
            int[] curr = q.poll();

            boolean higher = false;

            // 현재 프로세스보다 우선순위가 높은 프로세스가 있는지 확인
            for (int[] process : q) {
                if (process[1] > curr[1]) {
                    higher = true;
                    break;
                }
            }

            // 더 높은 우선순위가 있으면 다시 큐에 넣기
            if (higher) {
                q.offer(curr);
            } else {
                // 현재 프로세스 실행
                count++;

                // 실행된 프로세스가 찾던 프로세스라면 종료
                if (curr[0] == location) {
                    return count;
                }
            }
        }

        return count;
    }
}