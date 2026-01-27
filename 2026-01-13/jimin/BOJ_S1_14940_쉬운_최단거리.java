import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_14940_쉬운_최단거리 {
    static int n;
    static int m;
    static int[][] arr;
    static int[][] result;
    static boolean[][] visited;
    static Queue<int[]> queue;

    static int[] dr = {-1, 1, 0, 0}; //상하좌우
    static int[] dc = {0, 0, -1, 1}; //상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); //행
        m = Integer.parseInt(st.nextToken()); //열

        arr = new int[n][m];
        result = new int[n][m];
        visited = new boolean[n][m];
        int startR = 0, startC = 0;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 2) {
                    startR = i;
                    startC = j;
                }
                arr[i][j] = num;
            }
        }

        bfs(startR, startC);

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(arr[i][j] == 1 && !visited[i][j]) {
                    System.out.print(-1 + " ");
                } else {
                System.out.print(result[i][j] + " ");
                }
            }
            System.out.println();
        }

    }

    public static void bfs(int x, int y) {
        queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y}); //큐에 넣기
        visited[x][y] = true; //방문처리

        while(!queue.isEmpty()) { //큐가 빌 때까지
            int[] curr = queue.poll(); //큐에서 하나 꺼내기
            int r = curr[0];
            int c = curr[1];

            for(int i = 0; i < 4; i++) { //4방 탐색
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr < 0 || nc < 0 || nr >= n || nc >= m) continue; //범위밖이라면 넘어감
                if(arr[nr][nc] == 0) continue; //0이라면 넘어가기
                if(visited[nr][nc]) continue; //방문했다면 넘어가기
                queue.offer(new int[]{nr, nc}); //큐에 넣기
                visited[nr][nc] = true; //방문처리
                result[nr][nc] = result[r][c] + 1;
            }
        }
    }

}
