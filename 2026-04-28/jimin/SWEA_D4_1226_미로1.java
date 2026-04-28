import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class SWEA_D4_1226_미로1 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] arr;
    static boolean[][] visited; //방문배열
    static int startR, startC;
    static int endR, endC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int t = 1; t <= 10; t++) {
            int T = Integer.parseInt(br.readLine());
            char[][] map = new char[16][16];
            arr = new int[16][16];
            visited = new boolean[16][16];

            //값 넣기
            for(int i = 0; i < 16; i++) {
                map[i] = br.readLine().toCharArray();
            }

            //숫자로 변환하기
            for(int i = 0; i < 16; i++) {
                for(int j = 0; j < 16; j++) {
                    arr[i][j] = map[i][j] - '0';
                    if(arr[i][j] == 2) { //시작점이라면
                        startR = i;
                        startC = j;
                    }
                    if(arr[i][j] == 3) { //도착점이라면
                        endR = i;
                        endC = j;
                    }
                }
            }

            int result = bfs();

            System.out.println("#" + T + " " + result);
        }
    }

    public static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC}); //시작점 넣기
        visited[startR][startC] = true; //방문처리

        while(!queue.isEmpty()) {
            int[] curr = queue.poll(); //하나 꺼내기
            int r =  curr[0];
            int c = curr[1];
            for(int i = 0; i < 4; i++) { //사방탐색
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr == endR && nc == endC) {
                    return 1;
                }
                if(nr < 0 || nc < 0 || nr >= 16 || nc >= 16) continue; //범위를 벗어났다면
                if(visited[nr][nc]) continue; //방문했다면
                if(arr[nr][nc] == 1) continue; //벽이라면
                queue.offer(new int[]{nr, nc}); //큐에 넣기
                visited[nr][nc] = true; //방문처리
            }
        }

        return 0;
    }
}
