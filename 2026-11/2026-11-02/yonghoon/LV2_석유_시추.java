import java.util.*;

class LV2_석유_시추 {
    static int n, m; // 행, 열
    static boolean[][] visited; // 방문여부
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] oil;

    class Pos {
        int row;
        int col;
        int size;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int solution(int[][] land) {

        n = land.length;
        m = land[0].length;

        visited = new boolean[n][m];
        oil = new int[m]; // 각 열에서 시추할 수 있는 석유의 양

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                // 일반 땅이거나 방문한 곳이면 패스
                if(land[i][j] == 0 || visited[i][j])
                    continue;

                bfs(i, j, land);
            }
        }

        // 가장 많은 석유 보유한 열 추출
        int answer = 0;

        for(int i = 0; i < m; i++) {
            answer = Math.max(answer, oil[i]);
        }
        return answer;
    }

    // bfs
    void bfs(int row, int col, int[][] land) {
        Queue<Pos> q = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>(); // 해당 석유가 차지하고 있는 열

        int oilSize = 0; // 해당 석유 덩어리 크기

        q.offer(new Pos(row, col));
        visited[row][col] = true;
        set.add(col);

        while(!q.isEmpty()) {
            Pos cur = q.poll();

            int i = cur.row;
            int j = cur.col;

            // 석유 크기 늘리기
            oilSize++;

            // 사방탐색
            for(int k = 0; k < 4; k++) {
                int nx = i + dx[k];
                int ny = j + dy[k];

                // 범위체크
                if(nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;

                // 땅 & 방문체크
                if(land[nx][ny] == 0 || visited[nx][ny])
                    continue;

                // 큐에 넣고 set에도 열 저장
                // + 방문처리
                q.offer(new Pos(nx, ny));
                set.add(ny);
                visited[nx][ny] = true;
            }
        }

        for(Integer c : set) {
            oil[c] += oilSize;
        }
    }
}