import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S1_1389_케빈_베이컨의_6단계_법칙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int kb = Integer.MAX_VALUE; // 케빈베이컨 수
        int ans = 0; // 케빈베이컨 가장 작은 사람
        // 그래프 생성
        List<Integer>[] adjList = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++)
            adjList[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adjList[A].add(B);
            adjList[B].add(A);
        }

        for(int i = 1; i <= N; i++) {
            int cnt = 0;
            boolean[] visited = new boolean[N + 1];
            visited[i] = true;

            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[] {i, 0});

            while(!q.isEmpty()) {
                int[] curr = q.poll();
                int pos = curr[0]; // 현재 사람
                int nowKb = curr[1];

                for(int j : adjList[pos]) {
                    if(!visited[j]) { // 방문 안한곳만
                        visited[j] = true;
                        cnt += nowKb + 1;
                        q.offer(new int[] {j, nowKb + 1});
                    }
                }
            }

            if(cnt < kb) {
                kb = cnt;
                ans = i;
            }
        }

        System.out.println(ans);
    }
}
