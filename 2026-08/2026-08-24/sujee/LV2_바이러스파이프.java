import java.util.*;

class Solution {
    static List<int[]>[] graph;
    static int answer;
    static int n, k;

    public int solution(int n, int infection, int[][] edges, int k) {
        this.n = n;
        this.k = k;
        answer = 0;

        graph = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            graph[i] = new ArrayList<>();
        }

        // A,B,C 파이프 간선끼리 분류해서 graph에 넣기!
        // 인덱스 기준으므로 -1 씩 해준다.
        for (int[] edge : edges) {
            int a = edge[0] - 1;
            int b = edge[1] - 1;
            int type = edge[2] - 1;

            graph[type].add(new int[]{a, b});
        }

        // 감염 여부 표시할 배열 -> 첫 감염 노드는 미리 체크!
        boolean[] infected = new boolean[n];
        infected[infection - 1] = true;

        // DFS 시작! -> 0은 파이프 연 횟수 (count)
        dfs(0, infected);

        return answer;
    }

    static void dfs(int count, boolean[] infected) {
        if (count == k) { // 최대 횟수만큼 파이프 열면 종료
            int total = 0;

            for (boolean x : infected) {
                if (x) total++;
            }

            // 최대 감염 노드 수 갱신!
            answer = Math.max(answer, total);
            return;
        }

        for (int type = 0; type < 3; type++) {
            // .clone() 안쓰면 동일한 배열 가리킴. 따라서 clone()으로 복사해줘야함.
            boolean[] next = infected.clone();

            // BFS -> 해당 종류의 파이프를 열어서 바이러스 전파
            Queue<Integer> queue = new LinkedList<>();

            // 감염된 노드들을 전부 큐에 넣는다.
            // -> 감염은 현재 감염되어있는 노드 전부에서 퍼질 수 있기 때문
            for (int i = 0; i < n; i++) {
                if (next[i]) {
                    queue.offer(i);
                }
            }

            while (!queue.isEmpty()) {
                int cur = queue.poll();

                // 선택한 노드 & 선택한 파이프 종류만 비교
                for (int[] edge : graph[type]) {
                    int nextNode = -1;

                    if (edge[0] == cur) nextNode = edge[1];
                    else if (edge[1] == cur) nextNode = edge[0];

                    // 방문하지 않았으면 방문하고, 감염됐으니까 큐에 넣는다!
                    if (nextNode != -1 && !next[nextNode]) {
                        next[nextNode] = true;
                        queue.offer(nextNode);
                    }
                }
            }

            dfs(count + 1, next);
        }
    }
}