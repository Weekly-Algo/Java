import java.util.ArrayDeque;
import java.util.Queue;

class GameSolution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n;
    static int m;
    static boolean[][] visited; //방문 배열
    static int[][] dist; //거리 배열

    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length;
        visited = new boolean[n][m];
        dist = new int[n][m];

        //bfs
        int result = bfs(maps);

        return result;
    }

    public static int bfs(int[][] maps) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0}); //시작점 넣기
        visited[0][0] = true; //방문처리
        dist[0][0] = 1; //시작점 거리

        while(!queue.isEmpty()) { //큐가 빌 때까지
            int[] curr = queue.poll(); //하나 꺼내기
            int r = curr[0]; //행
            int c = curr[1]; //열
            if(r == n - 1 && c == m - 1) return dist[r][c]; //도착했다면

            for(int i = 0; i < 4; i++) { //사방탐색
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nc < 0 || nr >= n || nc >= m) continue; //범위를 벗어난다면
                if(visited[nr][nc]) continue; //방문했다면
                if(maps[nr][nc] == 0) continue; //벽이라면

                queue.offer(new int[]{nr, nc}); //큐에 넣기
                visited[nr][nc] = true; //방문처리
                dist[nr][nc] = dist[r][c] + 1; //거리 증가
                if(nr == n-1 && nc == m-1) return dist[nr][nc]; //도착했다면
            }

        }

        return -1; //큐가 비었더라도 반환할 것이 없을 때
    }
}

public class PRG_L2_게임_맵_최단거리 {
    public static void main(String[] args) {
        GameSolution sol = new GameSolution();

        int[][] maps = {
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,0,1,1,1},
                {1,1,1,0,1},
                {0,0,0,0,1}
        };

        int result = sol.solution(maps);
        System.out.println(result);
    }
}
