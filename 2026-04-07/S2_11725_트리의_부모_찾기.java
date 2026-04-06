import java.io.*;
import java.util.*;

public class S2_11725_트리의_부모_찾기 {
    static int N; // 노드의 개수
    static List<Integer>[] tree; // 트리
    static boolean[] visited; // 방문여부
    static int[] parents; // 부모 집합
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 트리 생성
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        parents = new int[N + 1];
        tree = new List[N + 1];
        for(int i = 0; i <= N; i++)
            tree[i] = new ArrayList<>();

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        // bfs 호출
        bfs();

        for(int i = 2; i <= N; i++)
            sb.append(parents[i]).append("\n");

        System.out.println(sb);
    }

    // bfs로 부모 찾기
    static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        // 루트부터 시작
        q.offer(1);
        visited[1] = true;

        while(!q.isEmpty()) {
            int curr = q.poll();
            for(int child : tree[curr]) {
                // 방문했던 노드라면 넘어감
                if(visited[child]) continue;

                // 다음 노드의 부모는 반드시 현재 노드임
                visited[child] = true;
                parents[child] = curr;

                q.offer(child);
            }
        }
    }
}