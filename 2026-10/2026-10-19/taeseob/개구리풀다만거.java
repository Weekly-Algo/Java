
import java.util.*;
import java.io.*;

public class Main {

    static class frog {
        int x;
        int y;
        int sec;
        int jump;

        public frog(int x, int y, int sec, int jump) {
            this.x = x;
            this.y = y;
            this.sec = sec;
            this.jump = jump;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        boolean[][][] map = new boolean[N][N][2];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < N; j++) {
                char tmp = str.charAt(j);

                if (tmp == '.') map[i][j][0] = true;
                if (tmp == '#') map[i][j][1] = true;
            }
        }

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;

            sb.append(bfs(map, sx, sy, ex, ey)).append('\n');
        }

        System.out.print(sb);
    }

    public static int bfs(boolean[][][] map, int sx, int sy, int ex, int ey) {

        int N = map.length;

        // dist[x][y][jump] = 해당 상태까지의 최소 시간
        int[][][] dist = new int[N][N][6];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dist[i][j], INF);
            }
        }

        PriorityQueue<frog> q =
            new PriorityQueue<>((a, b) -> Integer.compare(a.sec, b.sec));

        q.offer(new frog(sx, sy, 0, 1));
        dist[sx][sy][1] = 0;

        while (!q.isEmpty()) {
            frog curr = q.poll();

            int x = curr.x;
            int y = curr.y;
            int sec = curr.sec;
            int jump = curr.jump;

            // 이미 더 빠른 경로가 있으면 무시
            if (sec > dist[x][y][jump]) continue;

            // 목적지 도착
            if (x == ex && y == ey) return sec;

            // 1. 현재 점프력으로 상하좌우 점프
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i] * jump;
                int ny = y + dy[i] * jump;

                if (!isVaild(map, x, y, nx, ny, i, jump)) continue;

                if (dist[nx][ny][jump] > sec + 1) {
                    dist[nx][ny][jump] = sec + 1;
                    q.offer(new frog(nx, ny, sec + 1, jump));
                }
            }

            // 2. 점프력 증가 (최대 5)
            if (jump < 5) {
                int njump = jump + 1;
                int nsec = sec + njump * njump;

                if (dist[x][y][njump] > nsec) {
                    dist[x][y][njump] = nsec;
                    q.offer(new frog(x, y, nsec, njump));
                }
            }

            // 3. 점프력 감소 (1 ~ jump-1 중 선택)
            for (int njump = 1; njump < jump; njump++) {
                int nsec = sec + 1;

                if (dist[x][y][njump] > nsec) {
                    dist[x][y][njump] = nsec;
                    q.offer(new frog(x, y, nsec, njump));
                }
            }
        }

        return -1;
    }

    public static boolean isVaild(boolean[][][] map,
                                   int x, int y,
                                   int nx, int ny,
                                   int dir, int jump) {

        int N = map.length;

        // 범위 밖이면 이동 불가능
        if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
            return false;
        }

        // 점프하는 경로에 #이 있으면 이동 불가능
        for (int k = 1; k <= jump; k++) {
            int tx = x + dx[dir] * k;
            int ty = y + dy[dir] * k;

            if (map[tx][ty][1]) return false;
        }

        // 도착 위치는 반드시 안전한 돌(.)이어야 함
        if (!map[nx][ny][0]) return false;

        return true;
    }
}
