import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S1_1389_케빈_베이컨의_6단계_법칙 {
    static int N, M;
    static List<Integer>[] graph;
    static int[] dist; //누적 거리
    static int min = Integer.MAX_VALUE; //최소값
    static int minNum; //최소값을 가진 정점 번호

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //정점 수
        M = Integer.parseInt(st.nextToken()); //간선 수

        graph = new ArrayList[N + 1];
        for(int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        dist = new int[N + 1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y); //양방향
            graph[y].add(x);
        }

        for(int i = 1; i < N + 1; i++) {
            Arrays.fill(dist, -1);
            bfs(i);
            int sum = 0;
            for(int j = 1; j < N + 1; j++) {
                if(dist[j] > 0) sum += dist[j];
            }
            if(sum < min) {
                min = sum;
                minNum = i;
            }
        }
        System.out.println(minNum);
    }

    public static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start); //큐에 넣기
        dist[start] = 0; //시작점은 방문제외

        while(!queue.isEmpty()) { //큐가 빌 때까지
            int curr = queue.poll();
            for(int next : graph[curr]) { //연결된 정점
                if(dist[next] != -1) continue; //방문했다면
                queue.offer(next); //큐에 넣기
                dist[next] = dist[curr] + 1;
            }
        }
    }
}
