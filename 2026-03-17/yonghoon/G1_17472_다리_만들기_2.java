import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G1_17472_다리_만들기_2 {
    static int N, M; // 맵 크기
    static int[][] map; // 맵
    static boolean[][] visited; // 방문여뷰
    // 사방탐색용 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    // 크루스칼용 배열
    static int[] p;
    // 다리
    static class Edge {
        int from, to, length;
        public Edge(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        // 맵 생성
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        // 1. 섬 체크하기
        // 각 섬마다 숫자를 부여함
        int land = 1;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                // 방문x & 땅인 경우에만
                if(!visited[i][j] && map[i][j] == 1) {
                    checkLands(i, j, land);
                    land++;
                }
            }
        }

        // 2. 다리 찾기
        List<Edge> bridge = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                // 섬인 경우만 다리 놓기
                if(map[i][j] > 0) {
                    for(int k = 0; k < 4; k++) {
                        makeBridge(i, j, k, bridge);
                    }
                }
            }
        }

        // 3. 다리 정렬
        Collections.sort(bridge, (e1, e2) -> e1.length - e2.length);

        // 4. 크루스칼을 이용하여 최소길이로 연결하는 방법 찾기
        p = new int[land];

        for(int i = 0; i < land; i++)
            p[i] = i;

        int cnt = 0;
        int totalLength = 0;
        for(Edge e : bridge) {
            if(unionSet(e.from, e.to)) {
                totalLength += e.length;
                cnt++;
            }
        }

        // 5. 모든 섬이 연결 되었는지 체크
        // land가 원래 섬의 개수보다 1개 더 많으므로 2를 빼야함
        if(cnt != land - 2)
            System.out.println(-1);
        else
            System.out.println(totalLength);
    }

    // 섬 체크(bfs)
    static void checkLands(int row, int col, int land) {
        Queue<int[]> q = new ArrayDeque<>();
        visited[row][col] = true;
        map[row][col] = land;
        q.offer(new int[]{row, col});

        while(!q.isEmpty()) {
            int [] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                // 1. 범위 벗어남
                // 2. 땅이 아님
                // 3. 방문함
                // -> 건너뜀
                if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 0 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                map[nx][ny] = land;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    // 다리 놓기
    static void makeBridge(int x, int y, int dir, List<Edge> bridge) {
        int island = map[x][y];
        int length = 0;
        int nx = x;
        int ny = y;

        while(true) {
            nx += dx[dir];
            ny += dy[dir];

            // 1. 범위 벗어나거나 지금 내 섬이라면 종료
            if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == island) break;

            // 2. 바다라면 length 추가
            if(map[nx][ny] == 0) {
                length++;
                continue;
            }

            // 3. 다른 섬이라면 다리 생성
            if(map[nx][ny] > 0) {
                if(length >= 2) // 길이 2이상일때만 저장
                    bridge.add(new Edge(island, map[nx][ny], length));
                break;
            }
        }
    }

    // a 정점의 대표를 찾음
    static int findSet(int a) {
        if(a == p[a]) return  a;

        // 연결된 곳들의 정점을 대표로 업데이트
        return p[a] = findSet(p[a]);
    }

    // 내 집합인지 아닌지 체크
    static boolean unionSet(int a, int b) {
        a = findSet(a);
        b = findSet(b);

        // 정점이 같으면 false
        if(a == b) return false;

        // 정점이 다르면 맞춘 후 true
        p[b] = a;
        return true;
    }
}
