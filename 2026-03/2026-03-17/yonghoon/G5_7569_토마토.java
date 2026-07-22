import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_7569_토마토 {
    static int M, N, H;// 가로, 세로, 높이
    static int[][][] tomato; // 토마토 배열
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static int ans = 0; // 토마토 모두 익는 데 피룡한 최소 일수
    static Queue<int[]> q = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 토마토 배열 입력받기
        tomato = new int[H][N][M];

        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < M; k++) {
                    tomato[i][j][k] = Integer.parseInt(st.nextToken());
                    if(tomato[i][j][k] == 1)
                        q.offer(new int[]{i, j, k, 0});
                }
            }
        }

        // 토마토 감염
        bfs();

        // 안익은 곳 체크
        loop:
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < M; k++) {
                    // 익은 것은 1로 체크했는데 0이 남아있음 -> 전부 익지 못함
                    if(tomato[i][j][k] == 0) {
                        ans = -1;
                        break loop;
                    }
                }
            }
        }

        System.out.println(ans);
    }

    static void bfs() {
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int z = curr[0];
            int x = curr[1];
            int y = curr[2];
            ans = curr[3];

            for(int i = 0; i < 6; i++) {
                int nz = z + dz[i];
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 벗어났거나
                // 방문했거나
                // 안 익은 토마토가 없을 시 통과
                if(nz < 0 || nx < 0 || ny < 0 || nz >= H || nx >= N || ny >= M || tomato[nz][nx][ny] != 0) continue;

                // 하루를 더하고 큐에 넣기
                q.offer(new int[] {nz, nx, ny, curr[3] + 1});
                tomato[nz][nx][ny] = 1; // 토마토 익음
            }
        }
    }
}
