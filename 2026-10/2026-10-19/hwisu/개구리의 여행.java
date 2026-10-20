import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        for (int i=0; i<n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken())-1;
            int c1 = Integer.parseInt(st.nextToken())-1;
            int r2 = Integer.parseInt(st.nextToken())-1;
            int c2 = Integer.parseInt(st.nextToken())-1;
            sb.append(solve(r1, c1, r2, c2)).append("\n");
        } // for

        System.out.print(sb);
    } // main

    // 다익스트라 (행, 열, 점프력)
    static int solve(int sr, int sc, int er, int ec) {
        int[][][] dist = new int[n][n][6];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        // {시간, x, y, 점프력}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0]-b[0]);
        dist[sr][sc][1] = 0;
        pq.add(new int[]{0, sr, sc, 1});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int time = cur[0];
            int x = cur[1];
            int y = cur[2];
            int k = cur[3];

            if (time > dist[x][y][k]) continue;
            if (x == er && y == ec) return time;

            // 점프
            for (int d=0; d<4; d++) {
                if (!canJump(x, y, k, d)) continue;
                int nx = x + dx[d]*k;
                int ny = y + dy[d]*k;
                if (time+1 < dist[nx][ny][k]) {
                    dist[nx][ny][k] = time+1;
                    pq.add(new int[]{time+1, nx, ny, k});
                }
            } // for

            // 점프력 증가
            if (k < 5) {
                int nt = time + (k+1)*(k+1);
                if (nt < dist[x][y][k+1]) {
                    dist[x][y][k+1] = nt;
                    pq.add(new int[]{nt, x, y, k+1});
                }
            }

            // 점프력 감소
            for (int nk=1; nk<k; nk++) {
                if (time+1 < dist[x][y][nk]) {
                    dist[x][y][nk] = time+1;
                    pq.add(new int[]{time+1, x, y, nk});
                }
            } // for
        } // while

        return -1;
    } // solve

    // 점프 가능한지 확인
    static boolean canJump(int x, int y, int k, int d) {
        int nx = x + dx[d]*k;
        int ny = y + dy[d]*k;

        // 범위 밖
        if (nx<0 || ny<0 || nx>=n || ny>=n) return false;
        // 도착칸은 안전한 돌만
        if (map[nx][ny] != '.') return false;
        // 지나가는 칸에 천적 있으면 안됨
        for (int s=1; s<k; s++) {
            if (map[x + dx[d]*s][y + dy[d]*s] == '#') return false;
        }

        return true;
    } // canJump
} // Main