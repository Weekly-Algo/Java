import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S3_15652_N과M4 {
    static int N;
    static int M;
    static int[] sel;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sel = new int[M];
        sb = new StringBuilder();
        perm(0, 0);
        System.out.println(sb);
    }

    static void perm(int idx, int start){
        if(idx == M){
            for(int s : sel) {
                sb.append(s);
                sb.append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = start; i < N; i++){
            sel[idx] = i + 1;
            perm(idx + 1, i);
        }

    }
}
