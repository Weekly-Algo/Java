import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_16928_뱀과사다리게임_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 사다리 수
        int M = Integer.parseInt(st.nextToken()); // 뱀의 수

        int[][] ladder = new int[N][2];
        int[][] snake = new int[M][2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            ladder[i][0] = Integer.parseInt(st.nextToken());
            ladder[i][1] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            snake[i][0] = Integer.parseInt(st.nextToken());
            snake[i][1] = Integer.parseInt(st.nextToken());
        }

        boolean[] visited = new boolean[101];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{1, 0}); // 시작점
        visited[1] = true;
        while(!q.isEmpty()) {
            int tmp[] = q.poll();
            int curr = tmp[0]; int cnt = tmp[1];
            if(curr == 100) {
                System.out.println(cnt);
                return;
            }

            for(int i = 6; i >= 1; i--) {
                int next = curr + i;
                if(next > 100) continue;
                for (int j = 0; j < N; j++) {
                    if (next == ladder[j][0]) {
                        next = ladder[j][1];
                        break;
                    }
                }

                for (int j = 0; j < M; j++) {
                    if (next == snake[j][0]) {
                        next = snake[j][1];
                        break;
                    }
                }

                if(visited[next]) continue;
                q.offer(new int[] {next, cnt+1});
                visited[next] = true;
            }
        }

    }
}
