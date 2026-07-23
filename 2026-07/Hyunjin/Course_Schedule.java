import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // graph[i]: i번 강의를 선수 과목으로 요구하는 강의 목록
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // indegree[i]: i번 강의를 듣기 전에 필요한 선수 과목 수
        int[] indegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];

            // prerequisiteCourse를 들어야 course를 들을 수 있음
            graph.get(prerequisiteCourse).add(course);
            indegree[course]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();

        // 선수 과목이 없는 강의를 큐에 추가
        for (int course = 0; course < numCourses; course++) {
            if (indegree[course] == 0) {
                queue.offer(course);
            }
        }

        int completedCourses = 0;

        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            completedCourses++;

            // 현재 강의를 선수 과목으로 사용하는 강의 확인
            for (int nextCourse : graph.get(currentCourse)) {
                indegree[nextCourse]--;

                // 필요한 선수 과목을 모두 들었다면 수강 가능
                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        return completedCourses == numCourses;
    }
}