import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_G1_17472_다리만들기2 {
    static int N;
    static int M; // 지도의 크기 : N * M
    static int[][] map; // 지도
    static boolean[][] visited;

    // 사방탐색용 배열
    static int[] arr1 = {-1, 0, 1, 0};
    static int[] arr2 = {0, -1, 0, 1};

    static class Edge { // 다리 정보들을 입력할 Edge 클래스
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this. weight = weight;
        }
    }


    // 크루스칼용...
    static int[] parent;
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return false;

        parent[pb] = pa;
        return true;
    }


    static PriorityQueue<Edge> bridge = new PriorityQueue<>(new Comparator<Edge>() {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0;  j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬에 번호 매기기~~~
        visited = new boolean[N][M];
        int landNum = 1; // 섬 번호 매기기 위한 수
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++) {
                if(visited[i][j] || map[i][j] == 0) continue;
                visited[i][j] = true;
                map[i][j] = landNum;
                bfs(i, j, landNum);
                landNum++;
            }
        }
        landNum--; // 마지막에 하나 더 더했으므로 최종 개수는 -1 해줘야함

        // 다리 만들기~~~~~~
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) continue;
                makeBridge(i, j);
            }
        }


        // 크루스칼~~~~~~~~
        parent = new int[landNum+1];
        for(int i = 0; i <= landNum; i++) {
            parent[i] = i;
        } // 일단 전부다 부모노드로 리셋!

        int result = 0; int cnt = 0;
        while(!bridge.isEmpty()) {
            Edge e = bridge.poll();

            if(union(e.from, e.to)) {
                result += e.weight;
                cnt++;
            }
        }


        if(cnt != landNum-1) System.out.println(-1);
        else System.out.println(result);
    }

    static void bfs(int i, int j, int landNum) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {i, j});

        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int curr1 = tmp[0]; int curr2 = tmp[1];

            for(int k = 0; k < 4; k++) {
                int next1 = curr1 + arr1[k];
                int next2 = curr2 + arr2[k];

                if(next1 < 0 || next1 >= N || next2 < 0 || next2 >= M) continue;
                if(visited[next1][next2]) continue;
                if(map[next1][next2] == 0) continue;

                visited[next1][next2] = true;
                map[next1][next2] = landNum;
                q.offer(new int[] {next1, next2});
            }
        }
    }

    static void makeBridge(int i, int j) {

        int from = map[i][j]; // 출발하는 섬의 번호

        for(int k = 0; k < 4; k++) {
            int len = 0;
            int next1 = i; int next2 = j;
            while(true) {
                next1 += arr1[k]; next2 += arr2[k];

                if(next1 < 0 || next1 >= N || next2 < 0 || next2 >= M) break;
                if(from == map[next1][next2]) break;
                if(map[next1][next2] == 0) {
                    len++;
                    continue;
                }

                if (len >= 2) {
                    int to = map[next1][next2];
                    if (from > to) break;
                    bridge.add(new Edge(from, to, len));
                }
                break;
            }
        }

    }
}
