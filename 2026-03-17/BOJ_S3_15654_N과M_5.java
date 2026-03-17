import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S3_15654_N과M_5 {
    static int N, M;
    static int[] data;
    static int[] sel;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        data = new int[N];
        sel = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(data);
        perm(0);
    }

    public static void perm(int count) {
        //조건파트
        if(count == M) {
            for(int i = 0; i < M; i++) {
                System.out.print(sel[i] + " ");
            }
            System.out.println();
            return;
        }

        //재귀파트
        for(int i = 0; i < N; i++) {
            if(visited[i]) continue;
            visited[i] = true; //선택
            sel[count] = data[i];
            perm(count + 1);
            visited[i] = false; //선택 해제
        }
    }
}
