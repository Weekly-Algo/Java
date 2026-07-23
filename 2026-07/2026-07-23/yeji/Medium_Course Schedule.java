import java.util.*;

class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // 각 수업 다음에 들을 수 있는 수업 저장
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // 각 수업의 선수 과목 개수
        int[] degree = new int[numCourses];

        // 선수 과목 관계 저장
        for (int i = 0; i < prerequisites.length; i++) {

            int next = prerequisites[i][0];
            int curr = prerequisites[i][1];

            // curr 수업을 들은 후 next 수업을 들을 수 있음
            graph.get(curr).add(next);

            // next 수업의 선수 과목 개수 증가
            degree[next]++;
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();

        // 선수 과목이 없는 수업을 큐에 추가
        for (int i = 0; i < numCourses; i++) {

            if (degree[i] == 0) {
                q.offer(i);
            }
        }

        // 들을 수 있는 수업 개수
        int count = 0;

        while (!q.isEmpty()) {

            // 현재 들을 수 있는 수업
            int curr = q.poll();
            count++;

            // 현재 수업 다음에 들을 수 있는 수업 확인
            for (int next : graph.get(curr)) {

                // 현재 수업을 들었으므로 선수 과목 개수 감소
                degree[next]--;

                // 모든 선수 과목을 들었다면 큐에 추가
                if (degree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        // 모든 수업을 들을 수 있는지 확인
        return count == numCourses;
    }
}