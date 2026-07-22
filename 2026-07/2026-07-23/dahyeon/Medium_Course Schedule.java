import java.util.*;
// [a][b] => b먼저 듣고 a해야 함
// 대칭이면? false임 충돌남

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList<>();

        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<>());
        }

        // 진입 차수
        int[] indegree = new int[numCourses];

        for(int[] prerequisite : prerequisites){
            // 후 과목
            int after = prerequisite[0];
            // 선 과목
            int first = prerequisite[1];

            graph.get(first).add(after);
            // after 과목 듣기 전에 들어야 할 과목 갯수 추가
            indegree[after]++;
        }

        Queue<Integer> q = new ArrayDeque<>();

        // 진입차수 0인것들 넣기
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0) q.offer(i);
        }

        int done = 0;

        while(!q.isEmpty()){
            // 뽑으면 수강했다!
            int curr = q.poll();
            done++;

            for(int next : graph.get(curr)){
                // 들었으니까 진입차수에서 빼주기
                indegree[next]--;

                if(indegree[next] == 0) q.offer(next);
            }


        }
        // 모든 강의 다 들었으면 true
        return done == numCourses;

    }
}