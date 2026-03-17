import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1_17472_다리만들기2 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int islandCount = 0;
    
    // 델타
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    static int[] parent;
    
    // 다리 정보를 담을 우선순위 큐 (거리가 짧은 순으로 정렬)
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    static class Edge implements Comparable<Edge> {
        int from, to, weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        visited = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // BFS로 섬 번호 부여하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    islandCount++;
                    bfs(i, j);
                }
            }
        }
        
        // 가능한 모든 다리 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    findBridges(i, j, map[i][j]);
                }
            }
        }
        
        // 최소 비용 찾기
        parent = new int[islandCount + 1];
        for (int i = 1; i <= islandCount; i++) {
            parent[i] = i; // 초기 부모는 자기 자신
        }
        
        int totalCost = 0;
        int edgeCount = 0;
        
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            
            // 사이클이 발생하지 않는다면 연결
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                totalCost += edge.weight;
                edgeCount++;
            }
        }
        
        // 모든 섬이 연결되었는지 확인
        if (totalCost == 0 || edgeCount != islandCount - 1) {
            System.out.println(-1);
        } else {
            System.out.println(totalCost);
        }
    }

    // 섬 번호 매기기 (BFS)
    static void bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        visited[r][c] = true;
        map[r][c] = islandCount; // 섬 번호 부여
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int nr = current[0] + dr[i];
                int nc = current[1] + dc[i];
                
                if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                    if (map[nr][nc] == 1 && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        map[nr][nc] = islandCount;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
        }
    }

    // 직선 다리 탐색
    static void findBridges(int r, int c, int islandNum) {
        for (int i = 0; i < 4; i++) {
            int nr = r;
            int nc = c;
            int length = 0;
            
            while (true) {
                nr += dr[i];
                nc += dc[i];
                
                // 맵을 벗어나면 종료
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) break;
                // 같은 섬을 만나면 종료
                if (map[nr][nc] == islandNum) break;
                // 다른 섬을 만났을 때
                if (map[nr][nc] > 0) {
                    // 다리 길이가 2 이상인 경우에만 유효
                    if (length >= 2) {
                        pq.add(new Edge(islandNum, map[nr][nc], length));
                    }
                    break;
                }
                // 바다(0)인 경우 다리 길이 증가
                length++;
            }
        }
    }

    // 부모 찾기
    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]); // 최적화 기법
    }

    // 두 그룹 합치기
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}