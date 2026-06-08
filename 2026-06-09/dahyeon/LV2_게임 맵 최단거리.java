import java.util.*;
// 아잇 이건 쉽잖어~~~
// 원래 방문 배열도 뒀었지만, 없애고
// LinkedList 보단 ArrayDeque 로!!

class Solution {
    public int solution(int[][] maps) {

        int n = maps.length;
        int m = maps[0].length;

        boolean[][] visited = new boolean[n][m];
        int[][] dist = new int[n][m];


        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0 , -1};

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0,0});
        visited[0][0] = true;
        dist[0][0] = 1;

        while(!q.isEmpty()){
            int[] curr = q.poll();

            int x = curr[0];
            int y = curr[1];

            if(x == n-1 && y == m-1){
                return dist[x][y];

            }


            for(int i = 0; i < 4; i++){

                int nx = x + dx[i];
                int ny = y + dy[i];


                if(nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;

                if(visited[nx][ny]) continue;

                if(maps[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                dist[nx][ny] = dist[x][y] + 1;
                q.offer(new int[]{nx, ny});

            }

        }

        return -1;
    }
}