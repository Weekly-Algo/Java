import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    static int N;
    static List<Integer>[] graph;
    static int[] degree; //진입차수 배열
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        N = numCourses;
        graph = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        degree = new int[numCourses];

        for(int i = 0; i < prerequisites.length; i++) {
            int after = prerequisites[i][0];
            int before = prerequisites[i][1];

            graph[before].add(after);
            degree[after]++; //진입차수 증가
        }

        return bfs();
    }

    static boolean bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        //진입차수가 0인 정점 모두 넣기
        for(int i = 0; i < N; i++) {
            if(degree[i] == 0) queue.offer(i);
        }
        
        int cnt = 0; //처리한 개수 세기
        while(!queue.isEmpty()) {
            int curr = queue.poll(); //하나 빼기
            cnt++;
            for(int next : graph[curr]) {
                degree[next]--; //진입차수 감소
                if(degree[next] == 0) queue.offer(next); //큐에 넣기
            }
        }
        
         //모두 처리 완료하면
        if(cnt == N) return true;

        return false;
    }

}