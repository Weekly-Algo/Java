import java.util.*;

// 그니까 이거는 완전히 모르겠다
// 트리 + bfs + dfs
// 근데 이제 dfs는 모든 열 수 있는 파이프 탐색
// bfs는 선택한 파이프에서 나올 경우의 수 탐색

class Solution {

    // 그래프
    static List<Edge>[] graph;

    // 배양체 개수
    static int n;

    // 최대 행동 횟수
    static int k;

    // 최대 감염 개수 (return 값)
    static int answer = 0;

    // 파이프 정보 저장용 클래스
    static class Edge {
        int next;   // 연결된 배양체 번호
        int type;   // 파이프 타입 (1=A, 2=B, 3=C)

        Edge(int next, int type) {
            this.next = next;
            this.type = type;
        }
    }

    public int solution(int n, int infection, int[][] edges, int k) {

        this.n = n;
        this.k = k;

        // 그래프 생성
        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // edges 배열에 있는 모든 파이프 정보 탐색
        for (int[] edge : edges) {

            int from = edge[0]; // 파이프 한쪽 배양체 (1)
            int to = edge[1]; // 나머지 배양체 (3)
            int type = edge[2]; // 파이프 타입 (A 파이프)

            // 파이프는 양방향이므로 양쪽에 저장
            graph[from].add(new Edge(to, type));
            graph[to].add(new Edge(from, type));
        }

        // 현재 감염 여부를 저장하는 배열
        boolean[] infected = new boolean[n + 1];

        // 처음 감염된 배양체
        infected[infection] = true;

        // 파이프 선택 시작
        dfs(0, infected);

        return answer;
    }

    // 어떤 종류의 파이프를 열지 모든 경우를 탐색
    // DFS 탐색
    static void dfs(int depth, boolean[] infected) {

        // 현재 감염된 배양체 개수 확인
        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (infected[i]) {
                count++;
            }
        }

        // 최대값 갱신
        answer = Math.max(answer, count);

        // k번 행동했다면 종료
        if (depth == k) {
            return;
        }

        // A, B, C 파이프를 각각 열어보는 경우
        for (int type = 1; type <= 3; type++) {

            // 다른 경우의 탐색에 영향을 주지 않도록
            // 현재 감염 상태를 복사 (세 경우는 독립적이어야 함)
            // 여기부터 머리 터짐 ㄹㅇ
            boolean[] nextInfected = infected.clone();

            // 선택한 타입의 파이프를 열어서 감염 확산
            spread(type, nextInfected);

            // 다음 행동으로 이동
            dfs(depth + 1, nextInfected);
        }
    }

    // 특정 타입의 파이프를 열었을 때 감염을 확산시키는 함수
    // 여기서 BFS 탐색
    static void spread(int type, boolean[] infected) {

        Queue<Integer> queue = new LinkedList<>();

        // 현재 감염되어 있는 모든 배양체를
        // BFS 시작점으로 넣음
        for (int i = 1; i <= n; i++) {

            if (infected[i]) {
                queue.offer(i);
            }
        }

        // BFS
        while (!queue.isEmpty()) {

            int current = queue.poll();

            // 현재 배양체와 연결된 파이프 확인
            for (Edge edge : graph[current]) {

                // 지금 열어놓은 파이프 타입이 아니라면 지나감
                if (edge.type != type) {
                    continue;
                }

                // 이미 감염됐다면 다시 방문할 필요 없음
                if (infected[edge.next]) {
                    continue;
                }

                // 새롭게 감염
                infected[edge.next] = true;

                // 새롭게 감염된 배양체에서도
                // 같은 타입의 파이프를 타고 계속 확산
                queue.offer(edge.next);
            }
        }
    }
}