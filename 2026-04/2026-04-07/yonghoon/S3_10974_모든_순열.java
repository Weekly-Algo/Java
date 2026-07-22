import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_10974_모든_순열 {
    static int N;
    static int[] arr; // 1 ~ N까지의 배열
    static int[] result; // 수열
    static boolean[] visited; // 방문여부(중복 x이기 때문)
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        result = new int[N];
        visited = new boolean[N];
        for(int i = 0; i < N; i++)
            arr[i] = i + 1;

        perm(0);
        System.out.println(sb);
    }

    static void perm(int idx) {
        // 종료 조건
        if(idx == N) {
            for(int i = 0; i < N; i++)
                sb.append(result[i]).append(" ");
            sb.append("\n");
            return;
        }

        // 반복
        for(int i = 0; i < N; i++) {
            // i번째 방문을 했으면 건너뛰기
            if(visited[i]) continue;

            result[idx] = arr[i];
            visited[i] = true;
            perm(idx + 1);
            visited[i] = false;
        }
    }
}
