import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_14889_스타트와_링크 {
    static int N;
    static int M; //선택 개수
    static boolean[] visited;
    static int[][] arr;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = N / 2;

        visited = new boolean[N];
        arr = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0);
        System.out.println(min);
    }

    public static void comb(int idx, int count) {
        //조건파트
        if(count == M) {
            calc(); //계산
            return;
        }

        if(idx == N) return;

        //재귀파트
        visited[idx] = true; //선택
        comb(idx + 1, count + 1); //다음으로

        visited[idx] = false; //미선택
        comb(idx + 1, count); //다음으로
    }

    public static void calc() {
        int startSum = 0; //스타트팀
        int linkSum = 0; //링크팀

        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                //둘다 스타트팀
                if(visited[i] && visited[j]) {
                    startSum += arr[i][j] + arr[j][i];
                }

                //둘다 링크팀
                else if(!visited[i] && !visited[j]) {
                    linkSum += arr[i][j] + arr[j][i];
                }
            }
        }

        int diff = Math.abs(startSum - linkSum);
        min = Math.min(min, diff);
    }
}
