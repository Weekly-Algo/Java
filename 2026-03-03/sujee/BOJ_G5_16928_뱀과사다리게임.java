import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_G5_16928_뱀과사다리게임 {
    static int N;
    static int M;
    static int[][] ladder;
    static int[][] snake;
    static boolean[] visited;
    static int[] cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 사다리 수
        M = Integer.parseInt(st.nextToken()); // 뱀 수

        // 사다리 입력
        ladder = new int[N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            ladder[i][0] = Integer.parseInt(st.nextToken());
            ladder[i][1] = Integer.parseInt(st.nextToken());
        }

        // 뱀 입력
        snake = new int[M][2];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            snake[i][0] = Integer.parseInt(st.nextToken());
            snake[i][1] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[101]; // 방문 확인용
        cnt = new int[101]; // 주사위 카운트용

        bfs(1);
        System.out.println(cnt[100]);

    }

    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        while(!q.isEmpty()){
            int curr = q.poll();
            if(curr == 100) {
                return;
            }
            for(int i = 1; i <= 6; i++){
                int next = curr + i;
                if(next > 100) {
                    break;
                }

                // 사다리인지 확인! -> 사다리라면 다음 이동을 사다리 탄 후로 옮김!!
                for(int j = 0; j < N; j++) {
                    if(ladder[j][0] == next) {
                        next = ladder[j][1];
                    }
                }

                // 뱀인지 확인! -> 뱀이라면 다음 이동을 뱀 따라간 후로 옮김!!
                for(int j = 0; j < M; j++) {
                    if(snake[j][0] == next) {
                        next = snake[j][1];
                    }
                }

                // 뱀도 사다리도 아니라면 -> 방문여부 확인후 큐에 넣기!
                if(!visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                    cnt[next] = cnt[curr] + 1;
                }
            }

        }
    }
}
