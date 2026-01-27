import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S2_11724_연결_요소의_개수 {
    static List<Integer>[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N+1];
        for(int i = 1; i <= N; i++)
            arr[i] = new ArrayList<>();
        visited = new boolean[N+1];

        // 간선 정보 저장
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            arr[u].add(v);
            arr[v].add(u);
        }

        // 연결 요소 개수 구하기
        int cnt = 0;
        for(int i = 1; i <= N; i++) {
            if(!visited[i]) {
                bfs(i);
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    static void bfs(int pos) {
        Queue<Integer> q = new PriorityQueue<>();
        q.offer(pos);
        visited[pos] = true;

        while(!q.isEmpty()) {
            int curr = q.poll();
            for(int i = 0; i < arr[curr].size(); i++) {
                int next = arr[curr].get(i); // 연결된 요소
                if(!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}
