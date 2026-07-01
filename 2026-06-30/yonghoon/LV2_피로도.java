import java.util.*;

class LV2_피로도 {
    static int answer = 0;
    public int solution(int k, int[][] dungeons) {
        // 순열로 모든 조합의 경우의 수를 체크
        boolean[] visited = new boolean[dungeons.length];
        perm(k, 0, dungeons, visited);

        return answer;
    }

    // 순열
    static void perm(int k, int cnt, int[][] dungeons, boolean[] visited) {
        answer = Math.max(answer, cnt);

        for(int i = 0; i < dungeons.length; i++) {
            int need = dungeons[i][0];
            int cost = dungeons[i][1];

            // 방문하지 않았으면서 필요 피로도가 현재 가진 피로도보다 작은 곳 탐색
            if(!visited[i] && k >= need) {
                visited[i] = true;
                perm(k - cost, cnt + 1, dungeons, visited);
                visited[i] = false;
            }
        }
    }
}