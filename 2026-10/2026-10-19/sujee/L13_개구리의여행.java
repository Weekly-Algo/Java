import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static char[][] A;
    static int[][][] dist;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());
        A = new char[N][];

        // 호수의 돌 정보 입력
        for (int i = 0; i < N; i++)
            A[i] = br.readLine().trim().toCharArray();

        int Q = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        // 각 여행의 최소 시간 계산
        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;

            sb.append(solve(r1, c1, r2, c2)).append('\n');
        }

        System.out.print(sb);
    }


    // 더 짧은 시간이면 갱신하고 큐에 추가
    static void calculate(PriorityQueue<int[]> pq,
                          int x, int y, int k, int nd) {
        if (nd < dist[x][y][k]) {
            dist[x][y][k] = nd;
            pq.add(new int[]{nd, x, y, k});
        }
    }

    static int solve(int sr, int sc, int tr, int tc) {
        // 위치와 점프력별 최소 시간 초기화
        dist = new int[N][N][6];
        for (int[][] a : dist)
            for (int[] b : a)
                Arrays.fill(b, INF);

        // {시간, 행, 열, 점프력}
        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        dist[sr][sc][1] = 0;
        pq.add(new int[]{0, sr, sc, 1});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0], x = cur[1], y = cur[2], k = cur[3];

            // 이미 더 짧은 경로가 있으면 생략
            if (d != dist[x][y][k]) continue;

            // 목적지에 도착하면 최소 시간 반환
            if (x == tr && y == tc) return d;

            // 점프력 증가: 증가한 점프력의 제곱만큼 시간 소요
            if (k < 5)
                calculate(pq, x, y, k + 1,
                        d + (k + 1) * (k + 1));

            // 점프력 감소: 원하는 낮은 점프력으로 변경
            for (int nk = 1; nk < k; nk++)
                calculate(pq, x, y, nk, d + 1);

            // 상하좌우로 점프
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir] * k;
                int ny = y + dy[dir] * k;

                // 범위를 벗어나거나 안전한 돌이 아니면 불가
                if (nx < 0 || nx >= N || ny < 0 || ny >= N
                        || A[nx][ny] != '.') continue;

                boolean ok = true;

                // 점프 경로에 천적 돌이 있는지 확인
                for (int i = 1; i < k; i++) {
                    int tx = x + dx[dir] * i;
                    int ty = y + dy[dir] * i;

                    if (A[tx][ty] == '#') {
                        ok = false;
                        break;
                    }
                }

                // 점프 가능하면 시간 1을 더해 갱신
                if (ok)
                    calculate(pq, nx, ny, k, d + 1);
            }
        }

        // 목적지에 도달할 수 없음
        return -1;
    }
}
