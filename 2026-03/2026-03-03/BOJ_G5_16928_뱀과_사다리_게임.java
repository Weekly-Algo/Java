import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_뱀과_사다리_게임 {
    static int[] snakeLadder;
    static boolean[] visited;
    static int[] dist; //누적 이동 횟수
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        snakeLadder = new int[101];
        visited = new boolean[101];
        dist = new int[101];

        for(int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            snakeLadder[start] = end;
        }

        bfs(1);
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;

        while(!queue.isEmpty()) {
            int curr = queue.poll();
            for(int i = 1; i <= 6; i++) {
                int next = curr + i;
                if(next > 100) continue; //범위를 넘는다면

                //사다리/뱀 적용
                if(snakeLadder[next] != 0) {
                    next = snakeLadder[next]; //다음 칸
                }

                //방문했다면
                if(visited[next]) continue;
                //방문 안했다면
                visited[next] = true; //방문처리
                dist[next] = dist[curr] + 1; //이동 횟수
                queue.offer(next); //큐에 넣기

                if(next == 100) { //100이라면
                    System.out.println(dist[next]);
                    return;
                }

            }
        }
    }
}
