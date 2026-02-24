import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S2_21736_헌내기는_친구가_필요해 {
    static int N, M, cnt;
    static char[][] arr;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static class Pos {
        int row, col;
        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        visited = new boolean[N][M];

        int row = 0;
        int col = 0;

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                arr[i][j] = line.charAt(j);
                // 도연의 위치 저장
                if(arr[i][j] == 'I') {
                    row = i;
                    col = j;
                }
            }
        }

        bfs(row, col);

        // 만난 사람이 없으면 TT출력
        // 있으면 만난 사람의 수
        if(cnt == 0)
            System.out.println("TT");
        else
            System.out.println(cnt);
    }

    static void bfs(int row, int col) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(row, col));
        visited[row][col] = true;
        while(!q.isEmpty()) {
            Pos p = q.poll();
            for(int i = 0; i < 4; i++) {
                int nx = p.row + dx[i];
                int ny = p.col + dy[i];

                // 범위 벗어나거나 방문했거나 벽이면 갈 수 없음
                if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || arr[nx][ny] == 'X')
                    continue;

                // 다음 칸에 사람이 있음
                if(arr[nx][ny] == 'P') cnt++;
                visited[nx][ny] = true;
                q.offer(new Pos(nx, ny));
            }
        }
    }
}
