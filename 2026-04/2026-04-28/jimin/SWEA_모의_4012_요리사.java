import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_모의_4012_요리사 {
    static int N;
    static int[][] map;
    static boolean[] visited;
    static int min; //최솟값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            visited = new boolean[N];
            min = Integer.MAX_VALUE;

            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            comb(0, 0);

            System.out.println("#" + t + " " + min);
        }

    }

    public static void comb(int idx, int count) {
        //기저조건
        if(count == N/2) {
            calc(); //계산
            return;
        }

        if(idx == N) return;

        //재귀
        visited[idx] = true; //선택
        comb(idx+1, count + 1);

        visited[idx] = false; //미선택
        comb(idx+1, count);
    }

    public static void calc() {
        int sumA = 0;
        int sumB = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i] && visited[j]) { //선택된 인덱스라면
                    sumA += map[i][j];
                } else if(!visited[i] && !visited[j]) { //선택되지 않은 인덱스라면
                    sumB += map[i][j];
                }
            }
        }
        min = Math.min(min, Math.abs(sumA - sumB));
    }
}
