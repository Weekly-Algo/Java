import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_G5_10026_적록색약 {
    static char[][] arr;
    static Queue<int[]> queue;
    static boolean[][] visited; //방문배열
    static int N;

    static int[] dr = {-1, 1, 0, 0}; //상하좌우
    static int[] dc = {0, 0, -1, 1}; //상하좌우

    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        visited = new boolean[N][N];

        //배열 입력
        for(int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j]) {
                    bfs(i, j);
                    count++;
                }
            }
        }

        System.out.print(count + " ");

        //적록색약
        visited = new boolean[N][N];
        count = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(arr[i][j] == 'R') {
                    arr[i][j] = 'G';
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visited[i][j]) {
                    bfs(i, j);
                    count++;
                }
            }
        }

        System.out.print(count);
    }

    public static void bfs(int x, int y) {
        queue = new ArrayDeque<>(); //큐 생성
        queue.add(new int[]{x, y}); //큐에 넣기
        visited[x][y] = true; //방문처리

        while(!queue.isEmpty()) {
            int[] curr = queue.poll(); //현재 좌표 꺼내기
            int r = curr[0];
            int c = curr[1];

            for(int i = 0; i < 4; i++) { //4방탐색
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr >= N || nc >= N || nr < 0 || nc < 0) continue; //범위를 벗어났니?
                if(arr[nr][nc] != arr[r][c]) continue; //다르니?
                if(visited[nr][nc]) continue; //방문했니?
                queue.add(new int[]{nr, nc}); //큐에 넣기
                visited[nr][nc] = true; //방문처리
            }
        }
    }
}
