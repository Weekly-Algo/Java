import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_1012_유기농_배추 {
    static int M;
    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static Queue<int[]> queue;

    static int[] dr = {-1, 1, 0, 0}; //상하좌우
    static int[] dc = {0, 0, -1, 1}; //상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine()); //테스트 케이스

        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken()); //가로 길이
            N = Integer.parseInt(st.nextToken()); //세로 길이
            int K = Integer.parseInt(st.nextToken()); //배추 개수
            int count = 0; //지렁이 개수

            arr = new int[N][M];
            visited = new boolean[N][M];

            for(int i = 0; i < N; i++) {
                Arrays.fill(arr[i], 0);
            }

            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                arr[r][c] = 1; //해당 칸 1 입력
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(arr[i][j] == 1 && !visited[i][j]) {
                        bfs(i, j);
                        count++;
                    }
                }
            }

            System.out.println(count);
        }
    }

    public static void bfs(int x, int y) {
        queue = new ArrayDeque<>();
        queue.add(new int[]{x, y}); //큐에 넣기
        visited[x][y] = true; //방문처리

        while(!queue.isEmpty()) { //큐가 빌 때까지
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            for(int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue; //벗어났니?
                if(visited[nr][nc]) continue; //방문했었니?
                if(arr[nr][nc] == 0) continue; //0이니?
                queue.add(new int[]{nr, nc}); //큐에 넣기
                visited[nr][nc] = true; //방문처리
            }
        }
    }
}
