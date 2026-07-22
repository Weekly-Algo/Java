import java.util.*;

public class PRG_L3_가장_먼_노드 {
    static List<Integer>[] graph;
    static boolean[] visited;

    public int solution(int n, int[][] edge) {
        int answer = 0;
        graph = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList();
        }

        for(int i = 0; i < edge.length; i++) {
            int num1 = edge[i][0];
            int num2 = edge[i][1];
            graph[num1].add(num2);
            graph[num2].add(num1); //양방향
        }

        visited = new boolean[n + 1];

        answer = bfs();

        return answer;
    }

    public int bfs() {
        int max = Integer.MIN_VALUE;
        int maxCount = 0;
        Queue<int[]> queue = new ArrayDeque();
        queue.offer(new int[] {1, 0}); //시작점 넣기
        visited[1] = true; //방문 처리

        while(!queue.isEmpty()) { //큐가 빌 때까지
            int[] curr = queue.poll(); //하나 꺼내기
            int num = curr[0]; //정점
            int count = curr[1]; //거쳐온 간선의 개수
            for(int i = 0; i < graph[num].size(); i++) {
                int next = graph[num].get(i);
                if(visited[next]) continue; //방문했다면
                int nextCount = count + 1; //거리
                queue.offer(new int[]{next, nextCount}); //큐에 넣기
                visited[next] = true; //방문처리
                if(nextCount == max) maxCount += 1; //최대거리와 같다면
                if(nextCount > max) { //최대거리보다 크다면
                    max = nextCount;
                    maxCount = 1;
                }
            }

        }

        return maxCount;
    }

}
