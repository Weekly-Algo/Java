import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class G5_16928_뱀과_사다리_게임 {
    static int N, M; // 사다리, 뱀
    static Pos[] ladder;
    static Pos[] snake;
    static int min = Integer.MAX_VALUE; // 최소 횟수
    static boolean[] visited = new boolean[101];
    static class Pos {
        int from;
        int to;
        public Pos(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ladder = new Pos[N];
        snake = new Pos[M];

        // 사다리
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            ladder[i] = new Pos(from, to);
        }

        // 뱀
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            snake[i] = new Pos(from ,to);
        }

        bfs(1, 0);
        System.out.println(min);
    }

    static void bfs(int start, int cnt) {
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {start, cnt});
        visited[start] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int nowPos = curr[0];
            int nowCnt = curr[1];

            // 도착 시 카운트 측정
            if(nowPos == 100) {
                min = Math.min(min, nowCnt);
                continue;
            }

            // 주사위 굴리기
            for(int i = 1; i <= 6; i++) {
                // 다음 위치
                int next = nowPos + i;
                if(next > 100) continue;

                // 뱀 or 사다리 체크
                for(int j = 0; j < N ; j++) {
                    if(next == ladder[j].from)
                        next = ladder[j]. to;
                }
                for(int j = 0; j < M; j++) {
                    if(next == snake[j].from)
                        next = snake[j].to;
                }

                // 방문하지 않았다면 다음 위치 넣기
                if(!visited[next]) {
                    visited[next] = true;
                    q.offer(new int[] {next, nowCnt + 1});
                }
            }
        }
    }
}
