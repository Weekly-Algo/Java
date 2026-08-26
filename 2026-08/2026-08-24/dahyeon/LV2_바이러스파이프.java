import java.util.*;

class Solution {
    List<Edge>[] graph;
    int n;
    int k;
    int answer;

    public int solution(int n, int infection, int[][] edges, int k) {
        this.n = n;
        this.k = k;

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }


        // edges[i] = {a, b, type}
        // a번과 b번 배양체가 type 타입 파이프로 연결되어 있다는 뜻
        // 양방향이니까 둘다 넣어줌
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int type = edge[2];

            graph[a].add(new Edge(b, type));
            graph[b].add(new Edge(a, type));
        }


        // 감염여부 확인용
        boolean[] infected = new boolean[n + 1];
        infected[infection] = true;

        dfs(0, infected);

        return answer;
    }

    // 지금까지 count번 파이프를 열었다.
    void dfs(int count, boolean[] infected) {
        answer = Math.max(answer, countInfected(infected));

        if (count == k) {
            return;
        }

        // A, B, C 타입을 각각 열어본다.
        for (int type = 1; type <= 3; type++) {
            // 서로 영향 주면 안되니까, 현재 감염 상태 복사
            // 새로운 배열을 만들면서, 감염 내용 복사함
            // 개인적으로 나는 이 코드 한 줄이 가장 헷갈렸음
            boolean[] next = infected.clone();
            // 감염 시키기
            spread(next, type);
            // 파이프 한번 열었으니까, 다음!
            dfs(count + 1, next);
        }
    }

    // 선택한 타입의 파이프를 열어 감염을 확산한다.
    void spread(boolean[] infected, int type) {
        Queue<Integer> queue = new ArrayDeque<>();

        for (int node = 1; node <= n; node++) {
            if (infected[node]) {
                queue.offer(node);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Edge edge : graph[current]) {
                // 지금 연 타입과 다른 파이프 타입일 경우
                // 연결된 배양체가 이미 감염된 경우
                if (edge.type != type || infected[edge.to]) {
                    continue;
                }

                infected[edge.to] = true;
                queue.offer(edge.to);
            }
        }
    }

    // 감염된 배양체 개수 세는 메서드
    int countInfected(boolean[] infected) {
        int count = 0;

        for (int node = 1; node <= n; node++) {
            if (infected[node]) {
                count++;
            }
        }

        return count;
    }

    static class Edge {
        int to;
        int type;

        Edge(int to, int type) {
            this.to = to;
            this.type = type;
        }
    }
}