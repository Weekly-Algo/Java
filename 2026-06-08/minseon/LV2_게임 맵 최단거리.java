import java.util.*;

class Solution {

    static int N;
    static int M;

    static boolean[][] visited; //방문 여부 체크
    static int[][] dist; //이동한 거리 체크

    //4방탐색 델타
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;

        visited = new boolean[N][M];
        dist = new int[N][M];

        bfs(maps); //시작 위치

        if (!visited[N-1][M-1]) return -1; //최종점에 도달하지 못할 때
        return dist[N-1][M-1]; //최종 지점에서의 거리 return
    }

    //bfs 함수 시작
    static void bfs(int[][] maps) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0, 0});

        visited[0][0] = true;
        dist[0][0] = 1;

        //q가 비어있지 않은 동안 반복
        while(!q.isEmpty()) {
            int curr[] = q.poll();
            int r = curr[0];
            int c = curr[1];

            //4방탐색 시작
            for(int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && maps[nr][nc] == 1) {
                    visited[nr][nc] = true; //방문했으니까 true로 바꿔주고
                    dist[nr][nc] = dist[r][c] + 1;

                    q.add(new int[]{nr, nc});
                }
            }
        }
    }
}