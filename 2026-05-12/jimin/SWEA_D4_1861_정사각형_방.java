import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D4_1861_정사각형_방 {
    static int N;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int count;
    static int max;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            max = Integer.MIN_VALUE;
            result = 0;

            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    count = 1;
                    dfs(i, j);
                    if(count > max) {
                        max = count;
                        result = map[i][j];
                    }
                    if(count == max && map[i][j] < result) {
                        result = map[i][j];
                    }
                }
            }

            System.out.println("#" + t + " " + result + " " + max);
        }
    }

    public static void dfs(int r, int c) {
        for(int i = 0; i < 4; i++) { //4방 탐색
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue; //범위를 벗어났다면
            if(map[nr][nc] == map[r][c] + 1) {
                count++;
                dfs(nr, nc);
            }
        }
    }
}
