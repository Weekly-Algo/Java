import java.util.*;

class Solution {

    static int n;
    static List<Integer>[] graph;

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        Solution.n = n;

        // 양방향으로 그래프 저장
        graph = new List[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] wire : wires) {
            int a = wire[0], b = wire[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        // 전선을 하나씩 끊어보면서 나뉜 덩어리 크기 차이를 확인
        for (int[] wire : wires) {
            int cutA = wire[0], cutB = wire[1];
            int cnt = bfs(cutA, cutA, cutB); // cutA쪽 덩어리 크기
            int diff = Math.abs(cnt - (n - cnt));
            answer = Math.min(answer, diff);
        }

        return answer;
    }

    // start부터 시작해서 (cutA-cutB) 간선만 빼고 몇 개 방문하는지 세기
    static int bfs(int start, int cutA, int cutB) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;
        int cnt = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            cnt++;

            for (int next : graph[cur]) {
                if (visited[next]) continue;
                // 끊은 전선(cutA-cutB)은 건너뛰기
                if (cur == cutA && next == cutB) continue;
                if (cur == cutB && next == cutA) continue;

                visited[next] = true;
                q.offer(next);
            }
        }

        return cnt;
    }
}