import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        int[] nodes = new int[numCourses]; // indegree(진입차수) 배열
        for (int i = 0; i < numCourses; i++)
            graph[i] = new ArrayList<>();

        // 그래프를 인접 리스트로 표현
        for(int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            graph[b].add(a);
            nodes[a]++;
        }

        // 진입차수 0인 노드(선수과목 없는 강의) 큐에 전부 넣기
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if(nodes[i] == 0)
                q.offer(i);
        }

        int count = 0;
        while(!q.isEmpty()) {
            int cur = q.poll();
            count++; // 들은 과목 세기!

            for(int next : graph[cur]) {
                nodes[next]--; // 강의 하나를 들으면 그 강의에 이어진 과목들의 선수과목이 줄어들기 때문에 -1 처리
                if(nodes[next] == 0)
                    q.offer(next);
            }
        }

        // 다 처리됐으면 count == numCourses
        return count == numCourses;
    }
}