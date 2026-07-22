import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_15652_N과M_4 {
    static int N, M;
    static int[] arr;
    static int[] result;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        result = new int[M];
        for(int i = 0; i < N; i++)
            arr[i] = i + 1;

        perm(0, 0);

        System.out.println(sb);
    }

    static void perm(int idx, int start) {
        // 종료조건
        if(idx == M) {
            for(int i = 0; i < M; i++)
                sb.append(result[i]).append(" ");
            sb.append("\n");
            return;
        }

        for(int i = start; i < N; i++) {
            result[idx] = arr[i];
            perm(idx + 1, i);
        }
    }
}
