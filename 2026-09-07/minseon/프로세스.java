import java.util.*;

class Solution {

    public int solution(int[] priorities, int location) {

        // 각 프로세스의 인덱스와 우선순위를 저장할 Queue
        Queue<Process> queue = new LinkedList<>();

        // Queue에 프로세스 넣기
        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new Process(i, priorities[i]));
        }

        // 실행된 프로세스의 순서
        int order = 0;

        while (!queue.isEmpty()) {

            // 가장 앞에 있는 프로세스를 꺼냄
            Process current = queue.poll();

            boolean hasHigherPriority = false;

            // 현재 프로세스보다 우선순위가 높은 프로세스가
            // Queue 안에 존재하는지 확인
            for (Process p : queue) {

                if (p.priority > current.priority) {
                    hasHigherPriority = true;
                    break;
                }
            }

            // 더 높은 우선순위의 프로세스가 있다면
            // 현재 프로세스를 다시 Queue의 뒤로 보냄
            if (hasHigherPriority) {

                queue.offer(current);

            } else {

                // 현재 프로세스를 실행
                order++;

                // 내가 찾던 프로세스라면
                // 몇 번째로 실행됐는지 반환
                if (current.index == location) {
                    return order;
                }
            }
        }

        return order;
    }

    static class Process {

        int index;
        int priority;

        Process(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
}