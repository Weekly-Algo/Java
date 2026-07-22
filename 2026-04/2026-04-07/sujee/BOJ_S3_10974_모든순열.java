import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_S3_10974_모든순열 {
    static int N;
    static int[] sel;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        sel = new int[N];
        visited = new boolean[N];

        perm(0);

    }

    static void perm(int idx) {
        if(idx == N) {
            for(int k : sel) System.out.print(k + " ");
            System.out.println();
            return;
        }

        for(int i = 1; i <= N; i++) {
            if(visited[i-1]) continue;

            visited[i-1] = true;
            sel[idx] = i;
            perm(idx+1);
            visited[i-1] = false;
        }

    }
}
