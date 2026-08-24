import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    static class State {
        boolean[] infected; //현재까지 감염 여부
        int depth; //지금까지 파이프를 몇 번 열었는지

        State(boolean[] infected, int depth) {
            this.infected = infected;
            this.depth = depth;
        }
    }

    public int solution(int n, int infection, int[][] edges, int k) {

        int answer = 0;

        //첫 감염
        boolean[] start = new boolean[n + 1];
        start[infection] = true;

        Queue<State> queue = new ArrayDeque<>();
        //처음 상태 넣기
        queue.offer(new State(start, 0));


        //파이프를 선택하는 과정
        //k = 2라면
        //A > A, A > B, A > C, B > A, B > B, ...
        //모든 경우를 BFS로 탐색
        while (!queue.isEmpty()) {
            State current = queue.poll();
            
            //k번 파이프를 모두 선택했다면
            if (current.depth == k) {
                int count = 0;

                for (int i = 1; i <= n; i++) {
                    if (current.infected[i]) {
                        count++;
                    }
                }

                //지금까지 최대 감염 수 저장
                answer = Math.max(answer, count);
                continue;
            }


            //이번에 열 파이프 선택
            for (int type = 1; type <= 3; type++) {

                //현재 감염 상태를 복사
                boolean[] next = current.infected.clone();
                //감염시키기
                bfs(next, type, edges);

                //새로운 상태 큐에 넣기, depth + 1
                queue.offer(new State(next, current.depth + 1));
            }
        }
        return answer;
    }


    //특정 종류 파이프로 최대한 감염시키는 BFS
    static void bfs(boolean[] infected, int type, int[][] edges) {
        Queue<Integer> queue = new ArrayDeque<>();
        
        //모든 감염된 것을 넣기
        for (int i = 1; i < infected.length; i++) {
            if (infected[i]) queue.offer(i);
        }
        
        while (!queue.isEmpty()) {
            int current = queue.poll();

            //모든 파이프를 확인
            for (int[] edge : edges) {

                int from = edge[0];
                int to = edge[1];
                int pipeType = edge[2];

                //현재 노드가 from이고
                //파이프 종류가 현재 선택한 type이고
                //to가 아직 감염되지 않았다면
                if (current == from && pipeType == type && !infected[to]) {
                    infected[to] = true;
                    queue.offer(to);
                }

                //반대 방향일 경우
                if (current == to && pipeType == type && !infected[from]) {
                    infected[from] = true;
                    queue.offer(from);
                }
            }
        }
    }
}