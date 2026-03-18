import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17472_다리_만들기2 {
    static int N, M;
    static int[][] island;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0}; //상하좌우
    static int[] dc = {0, 0, -1, 1};
    static int islandCnt; //섬번호
    static List<Bridge> bridges = new ArrayList<>();
    static int[] parent; //부모 배열

    //다리 클래스 생성
    static class Bridge {
        int from;
        int to;
        int cost;

        Bridge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    //부모 찾기
    public static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    //두 섬이 다른 집합이면 합치기
    public static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if(pa == pb) return false;

        parent[pb] = pa;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        island = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                island[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //[각 섬에 번호 붙이기 - bfs]
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j] && island[i][j] == 1) { //아직 방문하지 않았고 땅이라면
                    islandCnt++; //섬 번호 증가
                    bfs(i, j);
                }
            }
        }

        //[다리 후보 찾기 - 간선 & 가중치]
        for(int r = 0; r < N; r++) {
            for(int c = 0; c < M; c++) {
                if(island[r][c] != 0) { //섬이라면
                    for(int d = 0; d < 4; d++) { //사방탐색
                        //직진탐색
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        int len = 0; //길이
                        while(true) {
                            if(nr < 0 || nc < 0 || nr >= N || nc >= M) break; //범위 밖이라면 멈춤

                            if(island[nr][nc] == 0) { //바다라면 계속 직진
                                len++; //길이증가
                                nr += dr[d];
                                nc += dc[d];
                            } else if(island[nr][nc] == island[r][c]) { //같은 섬이라면 멈춤
                                break;
                            } else { //다른 섬이라면
                                if(len >= 2) { //길이가 2이상이라면
                                    //다리 후보 추가
                                    bridges.add(new Bridge(island[r][c], island[nr][nc], len));
                                }
                                break;
                            }
                        }


                    }
                }
            }
        }

        //[부모 배열 초기화 - 크루스칼]
        parent = new int[islandCnt + 1];
        for(int i = 1; i <= islandCnt; i++) {
            parent[i] = i;
        }

        Collections.sort(bridges, (a, b) -> a.cost - b.cost); //간선 길이순 정렬

        int total = 0;
        int cnt = 0;
        for(Bridge bridge : bridges) {
            if (union(bridge.from, bridge.to)) { //두 섬이 다른 집합이라면
                total += bridge.cost;
                cnt++;
            }
        }

        if(cnt == islandCnt - 1) {
            System.out.println(total);
        } else {
            System.out.println(-1);
        }
    }


    public static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        island[x][y] = islandCnt; //섬 번호
        visited[x][y] = true; //방문
        queue.offer(new int[]{x, y}); //큐에 넣기

        while(!queue.isEmpty()) { //큐가 빌 때까지
            int[] curr = queue.poll(); //하나 꺼내기
            int r = curr[0];
            int c = curr[1];
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue; //범위 밖이라면
                if(island[nr][nc] != 1) continue; //연결된 땅이 아니라면
                if (visited[nr][nc]) continue; //방문했다면
                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
                island[nr][nc] = islandCnt;
            }
        }
    }
}
