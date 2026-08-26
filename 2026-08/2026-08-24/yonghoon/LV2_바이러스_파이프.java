import java.util.*;

class 바이러스_파이프 {
    static class Edge {
        int to;
        int type;

        public Edge(int to, int type) {
            this.to = to;
            this.type = type;
        }
    }
    static List<Edge>[] graph; // 그래프
    int n, k; // n, k
    int answer; // 배양체 개수의 최댓값

    public int solution(int n, int infection, int[][] edges, int k) {
        this.n = n;
        this.k = k;

        // 그래프 초기화
        graph = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 만들기
        for(int[] edge : edges) {
            int to = edge[0];
            int from = edge[1];
            int type = edge[2];

            graph[to].add(new Edge(from, type));
            graph[from].add(new Edge(to, type));
        }

        // 감염 상태 체크
        boolean[] infected = new boolean[n + 1];
        infected[infection] = true;

        dfs(0, 0, infected);

        return answer;
    }

    // 파이프를 어떤 순서로 열 것인지(AAA, AAB, AAC ...) 모든 경우의 수 체크
    void dfs(int depth, int prevType, boolean[] infected) {
        // 감염자 수 갱신
        int count = countInfected(infected);
        answer = Math.max(answer, count);

        // k번이 반복됐다면 종료
        if(depth == k)
            return;

        // A, B, C 3개의 파이프 중 하나 선택
        for(int type = 1; type <= 3; type++) {
            // 같은 파이프 연속으로 여는 것은 의미 없음
            if(type == prevType)
                continue;

            // 현재 상태 복사
            boolean[] next = infected.clone();

            // type파이프를 열어서 바이러스 확산시키기
            spread(next, type);

            // 다음 파이프
            dfs(depth + 1, type, next);
        }
    }

    // 바이러스 확산시키기
    void spread(boolean[] infected, int type) {
        Queue<Integer> q = new ArrayDeque<>();

        // 현재 감염된 모든 배양체를 큐에 넣기
        for(int i = 1; i <= n; i++) {
            if(infected[i])
                q.offer(i);
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(Edge edge : graph[cur]) {
                // 현재 열 수 있는 파이프랑 다르면 열 수 없음
                if(edge.type != type)
                    continue;

                // 이미 감염되어 있으면 패스
                if(infected[edge.to])
                    continue;

                // 감염시키고 큐에 넣기
                infected[edge.to] = true;
                q.offer(edge.to);
            }
        }
    }

    // 감염된 배양체 세기
    int countInfected(boolean[] infected) {
        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (infected[i]) {
                count++;
            }
        }

        return count;
    }
}