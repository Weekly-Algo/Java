import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_S3_1463_1로_만들기 {
    static int X;
    static int[] dist;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(br.readLine());

        dist = new int[X + 1];
        visited = new boolean[X + 1];

        bfs();
    }

    public static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(X); // 시작점 넣기
        visited[X] = true; // 방문처리
        dist[X] = 0;

        while(!queue.isEmpty()) { // 큐가 빌 때까지
            int curr = queue.poll(); // 하나 꺼내기

            if(curr == 1) { // 1이라면
                System.out.println(dist[curr]);
                return;
            }

            // -1의 경우
            int next = curr - 1;
            if(next >= 1 && !visited[next]) {
                visited[next] = true; // 방문처리
                dist[next] = dist[curr] + 1; //거리 증가
                queue.offer(next); // 큐에 넣기
            }

            // /2의 경우
            if (curr % 2 == 0) {
                next = curr / 2;
                if(!visited[next]) {
                    visited[next] = true; // 방문처리
                    dist[next] = dist[curr] + 1; //거리 증가
                    queue.offer(next); // 큐에 넣기
                }
            }

            // /3의 경우
            if (curr % 3 == 0) {
                next = curr / 3;
                if(!visited[next]) {
                    visited[next] = true; // 방문처리
                    dist[next] = dist[curr] + 1; //거리 증가
                    queue.offer(next); // 큐에 넣기
                }
            }
        }

    }
}
