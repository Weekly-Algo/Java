import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S1_2667_단지번호붙이기 {
    static int N; // 정사각형 크기
    static char[][] map; // 단지
    static boolean[][] visited; // 방문여부
    static int numHome; // 단지 수
    static List<Integer> list = new ArrayList<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static class Pos {
        int row;
        int col;
        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++)
            map[i] = br.readLine().toCharArray();

        // bfs를 돌면서 단지 체크
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j] && map[i][j] == '1') {
                    numHome++;
                    int cnt = 0; // 단지에 속한 집의 개수
                    list.add(bfs(i, j, cnt));
                }
            }
        }

        Collections.sort(list); // 정렬
        sb.append(numHome).append("\n");
        for(Integer i : list)
            sb.append(i).append("\n");

        System.out.println(sb);
    }

    static int bfs(int row, int col, int cnt) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(row, col));
        visited[row][col] = true;
        cnt++;

        while(!q.isEmpty()) {
            Pos curr = q.poll();
            int currRow = curr.row;
            int currCol = curr.col;

            for(int i = 0; i < 4; i++) {
                int nx = currRow + dx[i];
                int ny = currCol + dy[i];

                // 1. 범위 벗어나거나
                // 2. 방문했거나
                // 3. 집이 아니거나
                if(nx < 0 || ny < 0 || nx >= N | ny >= N || visited[nx][ny] || map[nx][ny] == '0') continue;

                // 집일 경우 방문처리 후 큐에 넣기
                visited[nx][ny] = true;
                cnt++;
                q.offer(new Pos(nx, ny));
            }
        }

        return cnt;
    }
}
