import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S1_14889_스타트와링크 {
    static int N;
    static int[][] ability; // 능력치!
    static int min = Integer.MAX_VALUE;
    static int[] start; // 스타트 팀
    static int[] link; // 링크 팀
    static boolean[] visited; // 중복 안되므로 -> 방문체크용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        ability = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N];
        start = new int[N/2];
        link = new int[N/2];
        findMin(0, 0);
        System.out.println(min);
    }

    static void findMin(int idx, int s) { // 스타트팀 인덱스
        if(idx == N/2){
            int tmp = 0;
            for(int i = 0; i < N; i++) {
                if(!visited[i]) link[tmp++] = i;
            }

            int startA = 0; // 스타트팀 능력치
            int linkA = 0; // 링크팀 능력치

            for(int i = 0; i < N/2; i++) {
                for(int j = 0; j < N/2; j++){
                    startA += ability[start[i]][start[j]];
                    linkA += ability[link[i]][link[j]];
                }
            }

            min = Math.min(min, Math.abs(startA - linkA));
            return;
        }

        for(int i = s; i < N; i++) {
            if(visited[i]) continue;
            if(start[0] >= N/2) continue;
            start[idx] = i;
            visited[i] = true;
            findMin(idx+1, i+1);
            visited[i] = false;
        }

    }
}
