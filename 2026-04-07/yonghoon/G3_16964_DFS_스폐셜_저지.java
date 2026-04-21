import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G3_16964_DFS_스폐셜_저지 {
    static int N; // 노드의 수
    static List<Integer>[] graph; // 그래프
    static boolean[] visited; // 방문여부 및 방문 순서 저장 배열
    static int seq = 1; // 방문 순서
    static List<Integer> order = new ArrayList<>();
    static int[] rank; // 우선순위를 지정하기 위한 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];

        // 그래프 생성
        graph = new List[N + 1];
        for(int i = 0; i <= N; i++)
            graph[i] = new ArrayList<>();
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }
        Queue<Integer> q = new ArrayDeque<>();

        // 순서 입력 받기
        int[] result = new int [N];
        rank = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int cur =  Integer.parseInt(st.nextToken());
            result[i] = cur;
            rank[cur] = i;
        }

        // 그래프 정렬
        for(int i = 1; i <= N; i++)
            Collections.sort(graph[i], (a, b) -> rank[a] - rank[b]);

        // dfs
        dfs(1);

        // 순서 확인
        int ans = 1;
        for(int i = 0; i < N; i++) {
            // 주어진 값과 내가 구한 값이 다름
            if(result[i] != order.get(i)) {
                ans = 0;
                break;
            }
        }

        System.out.println(ans);
    }

    // dfs
    static void dfs(int idx) {
        // 방문 처리 및 인덱스 저장
        visited[idx] = true;
        order.add(idx);

        for (int next : graph[idx]) {
            if (visited[next]) continue; // 방문했다면 넘어가기
            dfs(next);
        }
    }
}
