import java.util.*;

class Medium_Course_Schedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 선수 과목 - 이수가능 과목 리스트 생성
        List<List<Integer>> courseList = new ArrayList<>();
        for(int i = 0; i < numCourses; i++) {
            courseList.add(new ArrayList<>());
        }

        // 각 과목이 선수 과목이 몇 개가 필요한지 체크하는 배열
        int[] numList = new int[numCourses];

        for(int[] prerequisite : prerequisites) {
            int course = prerequisite[0]; // 과목
            int preCourse = prerequisite[1]; // 선수과목

            courseList.get(preCourse).add(course);
            numList[course]++;
        }

        Queue<Integer> q = new ArrayDeque<>();

        // 선수 과목이 없는 과목을 큐에 넣는다
        for(int i = 0; i < numCourses; i++) {
            if(numList[i] == 0) {
                q.offer(i);
            }
        }

        // 각 과목 큐에서 넣고 빼면서 총 몇 개의 과목을 수강했는지 체크
        int total = 0;
        while(!q.isEmpty()) {
            int cur = q.poll();
            total++;

            // 현재 꺼낸 과목을 선수 과목으로 하는 과목 체크
            for(int nextCourse : courseList.get(cur)) {
                numList[nextCourse]--;

                // 다음 과목에 필요한 선수 과목의 수가 0이되면 큐에 넣는다
                if(numList[nextCourse] == 0)  {
                    q.offer(nextCourse);
                }
            }
        }

        // 수강 과목수가 총 과목의 수와 같다면 true 아니면 false
        if(total == numCourses) {
            return true;
        } else {
            return false;
        }
    }
}