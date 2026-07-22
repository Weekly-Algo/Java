import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class G5_10026_적록색약 {
    // 큐 저장용 클래스
    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int normalCnt = 0; // 정상인의 구역 수
    static int halfCnt = 0; // 적록색약인 사람의 구역 수
    static int N; // 맵 크기
    static char[][] map; // 2차원 배열
    static boolean[][] visited; // 방문 배열
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];
        // 맵 받기
        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 일반인이 보는 영역
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j]) continue;
                normalBfs(i, j);
                normalCnt++;
            }
        }

        visited = new boolean[N][N];
        // 적록색맹이 보는 영역
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j]) continue;
                halfBfs(i, j);
                halfCnt++;
            }
        }

        sb.append(normalCnt).append(" ").append(halfCnt);
        System.out.println(sb);
    }

    static void normalBfs(int r, int c) {
        Queue<Pos> q = new LinkedList<Pos>();
        q.offer(new Pos(r, c));

        while (!q.isEmpty()) {
            Pos pos = q.poll();
            r = pos.r;
            c = pos.c;

            for(int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];

                // 범위 넘어가거나 방문한 곳이면 패스
                if(nr < 0 || nc < 0 || nr >=N || nc >=N || visited[nr][nc]) continue;

                // 같은 색이면 normalCnt++ 후 큐에 넣기
                if(map[r][c] == map[nr][nc]) {
                    q.offer(new Pos(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
    }

    static void halfBfs(int r, int c) {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(r, c));

        while(!q.isEmpty()) {
            Pos pos = q.poll();
            r = pos.r;
            c = pos.c;

            for(int i = 0; i < 4; i++) {
                int nr = r + dx[i];
                int nc = c + dy[i];

                // 범위 넘어가거나 방문한 곳이면 패스
                if(nr < 0 || nc < 0 || nr >=N || nc >=N || visited[nr][nc]) continue;

                // 빨강 or 초록, 파랑 2개의 케이스로 나누기
                if(map[r][c] == 'B') { // 파랑일때
                    if(map[nr][nc] == 'B') {
                        q.offer(new Pos(nr, nc));
                        visited[nr][nc] = true;
                    }
                } else { // 빨강 or 초록일때
                    if(map[nr][nc] == 'R' || map[nr][nc] == 'G') {
                        q.offer(new Pos(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
        }
    }
}
