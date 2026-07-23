import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    static int N; //컴퓨터 수
    static int[][] arr;
    static boolean[] visited; //방문배열
    static int count; //네트워크의 개수

    public int solution(int n, int[][] computers) {
        N = n;
        arr = computers;
        visited = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                bfs(i);
            }
        }

        return count;
    }

    static void bfs(int num) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(num); //큐에 넣기
        visited[num] = true; //방문처리

        while(!queue.isEmpty()) { //큐가 빌 때까지
            int curr = queue.poll(); //하나 꺼내기

            for(int i = 0; i < N; i++) {
                if(i != curr && !visited[i] && arr[curr][i] == 1) {
                    queue.offer(i); //큐에 넣기
                    visited[i] = true; //방문처리
                }
            }
        }

        count++; //개수 늘리기
    }
}