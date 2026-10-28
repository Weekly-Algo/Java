import java.io.*;
import java.util.*;

public class Main {
    // 거북이의 이동 우선순위(우, 하, 좌, 상)
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] sea = new int[N][N];  // 바다: 0(빈공간), 1(산호초)
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 거북이의 위치를 저장하는 배열 turtleMap
        // 거북이 없으면 0, 있으면 ID를 저장(1~)
        int[][] turtleMap = new int[N][N];

        // 거북의 ID에 맞게 좌표를 저장
        int[][] turtle = new int[M+1][2];

        for (int id = 1; id <= M; id++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            turtleMap[r][c] = id;

            turtle[id][0] = r;
            turtle[id][1] = c;
        }

        // 화산 위치와 현재 압력, 분출 임계치 P를 저장
        // volcano = [r, c, 현재 압력, 분출 입계치 P], 초기 압력은 0
        int[][] volcano = new int[K][4];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            volcano[i][0] = r;
            volcano[i][1] = c;
            volcano[i][2] = 0;
            volcano[i][3] = P;
        }
        
        // 화석의 위치
        boolean[][] fossil = new boolean[N][N];

        // 해당 턴에 누적된 열기
        int[][] heat = new int[N][N];

        // index번 거북이의 정보를 담는 배열
        // 0: 이동 중, 1: 화석, 2: 도착
        int[] state = new int[M+1];

        // 각 거북이가 안식처에 도착한 턴
        // 도착하지 못하면 -1
        int[] answer = new int[M + 1];
        Arrays.fill(answer, -1);

        // 시뮬은 최대 100턴
        for (int turn = 1; turn <= 100; turn++) {

            // 1단계: 거북 이동 - ID가 낮은 거북부터 bfs
            for (int id = 1; id <= M; id++) {
                if (state[id] != 0) continue;  // 화석 or 도착했다면 건너뛰기

                // 이번 턴에 이동할 칸 탐색
                int[] next = bfs(id, N, sea, turtleMap, turtle, fossil);
                
                // 안식처까지 갈 수 없다면
                if (next == null) continue;

                int currR = turtle[id][0];
                int currC = turtle[id][1];

                int nextR = next[0];
                int nextC = next[1];
                
                // 기존 위치 비우기
                turtleMap[currR][currC] = 0;

                // 새로운 위치로 이동
                turtle[id][0] = nextR;
                turtle[id][1] = nextC;

                // 안식처 도착 여부 확인
                // 도착했다면 지도에서 제외
                if (nextR == N-1 && nextC == N-1) {
                    state[id] = 2;
                    answer[id] = turn;
                }else {
                    turtleMap[nextR][nextC] = id;
                }
            }

            // 2단계: 화산 압력 증가
            Queue<Integer> eruptionQ = new ArrayDeque<>();
            boolean[] erupted = new boolean[K];  // 이번 턴에 분출했거나 분출 예정인 화산
            
            for (int i = 0; i < K; i++) {
                volcano[i][2] += 10;

                // 현재 압력만으로 분출 가능하다면
                if (volcano[i][2] >= volcano[i][3]) {
                    eruptionQ.offer(i);
                    erupted[i] = true;
                }
            }

            // 3단계: 화산 분출 및 연쇄 반응
            while (!eruptionQ.isEmpty()) {
                int vIdx = eruptionQ.poll();

                // 해당 화산의 열기 전파
                eruption(vIdx, N, sea, volcano, heat);

                // 새롭게 분출 조건을 만족하는 화산 확인
                for (int i = 0; i < K; i++) {
                    if (erupted[i]) continue;

                    int vR = volcano[i][0];
                    int vC = volcano[i][1];

                    int currP = volcano[i][2];
                    int P = volcano[i][3];

                    if (currP + heat[vR][vC] >= P) {
                        erupted[i] = true;
                        eruptionQ.offer(i);
                    }
                }
            }

            // 거북이 화석화
            for (int id = 1; id <= M; id++) {
                if (state[id] != 0) continue;

                int r = turtle[id][0];
                int c = turtle[id][1];

                if (heat[r][c] >= 20) {
                    state[id] = 1;

                    turtleMap[r][c] = 0;  // 살아있는 거북 지도에서 제거

                    fossil[r][c] = true;  // 화석 생성
                }
            }

            // 4단계: 환경 초기화

            // 분출했던 화산 압력 초기화
            for (int i = 0; i < K; i++) {
                if (erupted[i]) volcano[i][2] = 0;
            }

            // 모든 열기 제거
            for (int i = 0; i < N; i++) {
                Arrays.fill(heat[i], 0);
            }

            // 모든 거북이의 상태가 확정되었다면 종료
            boolean finished = true;

            for (int id = 1; id <= M; id++) {
                if (state[id] == 0) {
                    finished = false;
                    break;
                }
            }

            if (finished) break;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int id = 1; id <= M; id++) {
            sb.append(answer[id]).append("\n");
        }

        System.out.print(sb);
    }
    
    // 거북이의 최단 경로 탐색 -> 안식처(N-1, N-1)에서 거꾸로 탐
    private static int[] bfs(int id, int N, int[][] sea, int[][] turtleMap, int[][] turtle, boolean[][] fossil) {
        Queue<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[N][N];

        // dist 배열을 -1로 초기화 -> 안식처는 0으로 시작하기 때문
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        // 안식처에서 시작
        q.offer(new int[] {N-1, N-1});
        dist[N-1][N-1] = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int currR = curr[0];
            int currC = curr[1];

            for (int d = 0; d < 4; d++) {
                int nr = currR + dr[d];
                int nc = currC + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (sea[nr][nc] == 1 || dist[nr][nc] != -1 || fossil[nr][nc]) continue;
                if (turtleMap[nr][nc] != 0 && turtleMap[nr][nc] != id) continue;

                dist[nr][nc] = dist[currR][currC] + 1;
                q.offer(new int[] {nr, nc});
            }
        }

        // 현재 id에 해당하는 거북이의 위치
        int currR = turtle[id][0];
        int currC = turtle[id][1];

        // 안식처까지 가는 최단 경로가 없는 경우
        if (dist[currR][currC] == -1) return null;

        // 현재 위치를 기준으로 거리-1 위치 탐색
        // 우선순위 기준 우하좌상
        for (int d = 0; d < 4; d++) {
            int nr = currR + dr[d];
            int nc = currC + dc[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            if (dist[nr][nc] == dist[currR][currC] - 1) return new int[] {nr, nc};
        }

        return null;
    }

    private static void eruption(int vIdx, int N, int[][] sea, int[][] volcano, int[][] heat) {
        int vR = volcano[vIdx][0];
        int vC = volcano[vIdx][1];
        int P = volcano[vIdx][3];

        // 화산이 위치한 칸에는 P만큼 열기 발생
        heat[vR][vC] += P;

        // 상하좌우 4방향으로 전파
        for (int d = 0; d < 4; d++) {
            int r = vR;
            int c = vC;
            int currHeat = P;

            while (true) {
                r += dr[d];
                c += dc[d];

                // 한 칸 이동할 때마다 열기 절반
                currHeat /= 2;

                // 범위 밖
                if (r < 0 || r >= N || c < 0 || c >= N)
                    break;

                // 열기가 0이면 전파 종료
                if (currHeat == 0)
                    break;

                // 산호초를 만나면 전파 종료
                if (sea[r][c] == 1)
                    break;

                // 해당 칸에 열기 누적
                heat[r][c] += currHeat;
            }
        }
    }
}