import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S2_21736_헌내기는_친구가_필요해 {
    static int N;
    static int M;
    static char[][] arr;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0}; //상하좌우
    static int[] dc = {0, 0, -1, 1}; //상하좌우

    static int doyeonX;
    static int doyeonY;

    static int count; //만난 사람의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(arr[i][j] == 'I') {
                    doyeonX = i;
                    doyeonY = j;
                }
            }
        }

        bfs(doyeonX, doyeonY);

        if(count == 0) {
            System.out.println("TT");
        } else {
            System.out.println(count);
        }
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();

        visited[x][y] = true; //방문처리
        queue.add(new int[]{x, y}); //큐에 넣기

        while(!queue.isEmpty()) { //큐가 빌 때까지
            int[] curr = queue.poll(); //큐에서 하나 빼기
            int r = curr[0];
            int c = curr[1];

            for(int i = 0; i < 4; i++) { //상하좌우
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue; //범위를 벗어나면
                if(arr[nr][nc] == 'X') continue; //벽이라면
                if(visited[nr][nc]) continue; //방문 했다면
                if(arr[nr][nc] == 'P') count++; //사람이라면
                visited[nr][nc] = true; //방문처리
                queue.add(new int[]{nr, nc}); //큐에 넣기
            }
        }
    }
}
