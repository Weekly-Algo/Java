import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {
    static List<Integer>[] wireList;
    static int N;
    static boolean[] visited;

    public int solution(int n, int[][] wires) {
        N = n;
        wireList = new ArrayList[n + 1];
        
        for(int i = 1; i <= n; i++) {
            wireList[i] = new ArrayList<>();
        }

        for(int i = 0; i < n-1; i++) {
            int a = wires[i][0]; //정점
            int b = wires[i][1]; //연결된 점
            wireList[a].add(b);
            wireList[b].add(a); //양방향
        }

        int answer = Integer.MAX_VALUE;
        
        for(int i = 0; i < n-1; i++) {
            //visited 초기화
            visited = new boolean[n + 1];
            //전선을 끊었다고 가정
            int cutA = wires[i][0];
            int cutB = wires[i][1];

            int cnt = bfs(1, cutA, cutB);
            int other = n - cnt;
            answer = Math.min(answer, Math.abs(cnt - other));
        }

        return answer;
    }

    public static int bfs(int start, int cutA, int cutB) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;

        int cnt = 1; //시작정점 포함
        
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            for(int next : wireList[curr]) {
                //끊은 전선이라면
                if((curr == cutA && next == cutB) || (curr == cutB && next == cutA)) continue;
                //방문했다면
                if(visited[next]) continue;
                queue.offer(next);
                visited[next] = true;
                cnt++;
            }
        }

        return cnt;
    }
}