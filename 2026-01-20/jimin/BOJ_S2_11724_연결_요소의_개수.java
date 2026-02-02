import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S2_11724_연결_요소의_개수 {
    static int N; //정점 개수
    static int M; //간선 개수
    static List<Integer>[] adjust; //인접리스트
    static boolean[] visited; //방문배열
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjust = new ArrayList[N + 1]; //정점 + 1만큼 배열 생성
        visited = new boolean[N + 1];

        for(int i = 1; i < N + 1; i++) {
            adjust[i] = new ArrayList<>(); //리스트 생성
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); //정점
            int v = Integer.parseInt(st.nextToken()); //연결 정점
            adjust[u].add(v);
            adjust[v].add(u); //양방향
        }

        int count = 0;
        for(int i = 1; i < N + 1; i++) {
            if(!visited[i]) { //방문하지 않았다면
                bfs(i);
                count++;
            }
        }

        System.out.println(count);
    }

    public static void bfs(int x) {
        queue = new ArrayDeque<>();
        queue.add(x); //큐에 넣기
        visited[x] = true; //방문처리

        while(!queue.isEmpty()) { //큐가 빌 때까지
            int curr = queue.poll(); //하나 꺼내기
            for(int i : adjust[curr]) {
                if(!visited[i]) {
                    queue.add(i); //큐에 넣기
                    visited[i] = true; //방문 처리
                }
            }
        }
    }
}
