import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1_14940_쉬운_최단거리 {
    static int n, m; // 가로, 세로 길이
    static int[][] map; // 지도
    static boolean[][] visited; // 방문 여부
    static int[][] distance; // 거리 저장
    static int x_goal, y_goal; // 2(목적지)가 있는곳

    // 사방탐색
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 큐에 사용할 클래스
    static class Pos{
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        distance = new int[n][m];

        // 맵 입력
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 목적지 저장
                if(map[i][j] == 2) {
                    x_goal = i;
                    y_goal = j;
                }
            }
        }

        // 목적지에서 bfs로 모든 경로 탐색
        bfs(x_goal, y_goal);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 0)
                    sb.append(0).append(" ");
                else if (!visited[i][j] && map[i][j] == 1)
                    sb.append(-1).append(" ");
                else
                    sb.append(distance[i][j]).append(" ");
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static void bfs(int row, int col) {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(row, col));
        visited[row][col] = true;
        distance[row][col] = 0;
        while(!q.isEmpty()) {
            Pos curr = q.poll();
            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || map[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                q.offer(new Pos(nx, ny));
                distance[nx][ny] = distance[curr.x][curr.y] + 1;
            }
        }
    }
}
