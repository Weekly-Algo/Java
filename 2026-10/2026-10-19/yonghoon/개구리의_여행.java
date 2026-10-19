import java.io.*;
import java.util.*;

public class 개구리의_여행 {
    static int N;
    static char[][] map;
    static int[][][] dist;
    static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1};
    static class Pos {
        int r;
        int c;
        int jump;
        int time;

        public Pos(int r, int c, int jump, int time) {
            this.r = r;
            this.c = c;
            this.jump = jump;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        dist = new int[N][N][6];

        // 맵 입력
        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int Q = Integer.parseInt(br.readLine());

        for(int q = 0; q < Q; q++) {

            // 걸리는 시간 배열 무한대로 초기화
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    Arrays.fill(dist[i][j], Integer.MAX_VALUE);
                }
            }

            st = new StringTokenizer(br.readLine());

            int r1 = Integer.parseInt(st.nextToken()) - 1;
            int c1 = Integer.parseInt(st.nextToken()) - 1;
            int r2 = Integer.parseInt(st.nextToken()) - 1;
            int c2 = Integer.parseInt(st.nextToken()) - 1;

            int time = dijkstra(r1, c1, r2, c2);
            sb.append(time).append("\n");
        }
        System.out.println(sb.toString());
    }

    // 걸리는 시간 계산
    static int dijkstra(int r1, int c1, int r2, int c2) {
        PriorityQueue<Pos> pq = new PriorityQueue<>((a, b) -> a.time - b.time);

        pq.offer(new Pos(r1, c1, 1, 0));
        dist[r1][c1][1] = 0;

        while(!pq.isEmpty()) {
            Pos cur = pq.poll();
            int r = cur.r;
            int c = cur.c;
            int jump = cur.jump;
            int time = cur.time;

            // 목적지에 도착함
            if(r == r2 && c == c2) {
                return dist[r][c][jump];
            }

            // 사방 + 점프 탐색
            for(int i = 0; i < 4; i++) {
                for(int j = 1; j <= 5; j++) {
                    int nr = r + j * dx[i];
                    int nc = c + j * dy[i];

                    // 범위 벗어나거나 미끌돌이면 패스
                    if(nr < 0 || nc < 0 || nr >= N || nc >= N || map[nr][nc] == 'S')
                        continue;

                    // 중간에 포식자 있으면 패스
                    boolean canGo = true;
                    for(int k = 1; k <= j; k++) {
                        int tmpR = r + k * dx[i];
                        int tmpC = c + k * dy[i];

                        if(map[tmpR][tmpC] == '#') {
                            canGo = false;
                            break;
                        }
                    }

                    if(!canGo) break;

                    // 점프 감소
                    int nextTime = time + 1;
                    if(j < jump)
                        nextTime += 1;
                        // 점프 증가
                    else if(j > jump) {
                        nextTime = time + 1;
                        for(int k = jump + 1; k <= j; k++) {
                            nextTime += k * k;
                        }
                    }
                    // 점프 유지시 없음

                    // 기존 시간보다 작다면 업데이트 후 큐에 넣기
                    if(nextTime < dist[nr][nc][j]) {
                        dist[nr][nc][j] = nextTime;
                        pq.offer(new Pos(nr, nc, j, nextTime));
                    }
                }
            }
        }

        return -1;
    }
}
