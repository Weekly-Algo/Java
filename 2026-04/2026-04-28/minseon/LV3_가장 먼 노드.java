import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {

        ArrayList<Integer>[] list = new ArrayList[n + 1];

        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }

        //edge 배열로 들어오는 값들 e 배열에 저장
        //방향 없는 그래프라 양쪽 다 저장
        for(int[] e : edge) {
            int a = e[0];
            int b = e[1];

            list[a].add(b);
            list[b].add(a);
        }

        int[] dist = new int[n + 1]; //거리 저장 배열
        Arrays.fill(dist, -1); //아직 다 방문 x -> -1로 채워넣음


        //BFS 시작 -> 각 노드들 간의 거리를 dist[]에 저장
        //가장 큰 dist[] 값을 가지고 있는 노드들이 멀리 떨어진 노드
        Queue<Integer> q = new LinkedList<>();

        q.add(1); //1번 노드부터 시작
        dist[1] = 0; //방문 표시

        while(!q.isEmpty()) {
            int curr = q.poll();

            //curr: 1
            //list[1]: 연결돼 있는 노드들 -> next로 가져옴
            for(int next : list[curr]) {
                //아직 방문하지 않았다면
                if(dist[next] == -1) {
                    dist[next] = dist[curr] + 1;
                    q.add(next);
                }
            }
        }

        int maxDist = 0;

        for(int i=1; i<=n; i++) {
            maxDist = Math.max(maxDist, dist[i]);
        }

        int ans = 0;

        for(int i=1; i<=n; i++) {
            if(dist[i] == maxDist) {
                ans++;
            }
        }

        return ans;
    }
}