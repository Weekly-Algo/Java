class Solution {
    static int m, n;
    static int[] arr1 = {-1, 0, 0, 1};
    static int[] arr2 = {0, -1, 1, 0};
    static char[][] maps;

    public int numIslands(char[][] grid) {
        // 하필 char 배열이네 이거... 정수면 좋겠다 거슬림...ㅎ

        m = grid.length;
        n = grid[0].length;
        maps = grid;
        int cnt = 0;


        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(maps[i][j] == '0') continue;
                else{
                    cnt++;
                    maps[i][j] = '0';
                    bfs(i, j);
                };
            }
        }

        return cnt;
    }

    static void bfs(int i, int j){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {i, j});
        while(!q.isEmpty()) {
            int[] tmp = q.poll();
            int curr1 = tmp[0]; int curr2 = tmp[1];

            // 사방탐색 레츠고
            for(int k = 0; k < 4; k++) {
                int next1 = curr1 + arr1[k];
                int next2 = curr2 + arr2[k];

                if(next1 < 0 || next1 >= m || next2 < 0 || next2 >= n) continue;
                if(maps[next1][next2] == '0') continue;
                q.offer(new int[] {next1, next2});
                maps[next1][next2] = '0';
            }
        }
    }
}