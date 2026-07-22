import java.util.*;

class Solution {
    static int m; // 행
    static int n; // 열

    static int[][] maps;
    static int[] arr1 = {0, -1, 1, 0};
    static int[] arr2 = {-1, 0, 0, 1};

    public int solution(int[][] maps) { // 미로는 BFS 겠지…
        Solution.maps = maps;
        m = maps.length; // 행
        n = maps[0].length; // 열

        int answer = 0;

        bfs(new int[]{0,0});

        if(maps[m-1][n-1] != 1) answer = maps[m-1][n-1]; // 1이면 도달하지 못한 것이므로…
        else answer = -1;
        return answer;
    }

    static void bfs(int[] position) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(position);

        while(!q.isEmpty()){
            int[] tmp = q.poll();
            int curr1 = tmp[0]; int curr2 = tmp[1];

            // 사방탐색
            for(int i = 0; i < 4; i++) {
                int next1 = curr1 + arr1[i];
                int next2 = curr2 + arr2[i];

                if(next1 < 0 || next1 >= m || next2 < 0 || next2 >= n) continue;
                if(next1 == 0 && next2 == 0) continue;
                if(maps[next1][next2] == 1) {
                    q.offer(new int[] {next1, next2});
                    maps[next1][next2] = maps[curr1][curr2] + 1;
                }
            }
        }

    }
}