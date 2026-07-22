import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_D4_2819_격자판의_숫자_이어_붙이기 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static String[][] map;
    static int N = 4;
    static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <=T; t++) {
            map = new String[N][N];
            set = new HashSet<>();
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = st.nextToken();
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(map[i][j]);
                    dfs(i, j, sb);
                }
            }

            System.out.println("#" + t + " " + set.size());
        }
    }

    public static void dfs(int r, int c, StringBuilder sb) {
        if(sb.length() == 7) {
            set.add(sb.toString());
            return;
        }

        for(int i = 0; i < 4; i++) { //사방탐색
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue; //벗어났다면
            sb.append(map[nr][nc]);
            dfs(nr, nc, sb);
            sb.deleteCharAt((sb.length()-1)); //원상복구 - 맨 마지막 글자 제거
        }
    }
}
