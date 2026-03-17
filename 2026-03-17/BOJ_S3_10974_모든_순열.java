import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S3_10974_모든_순열 {
    static int N;
    static int[] arr;
    static int[] sel;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        sel = new int[N];
        visited = new boolean[N];

        for(int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }

        perm(0);
    }

    public static void perm(int count) {
        //조건파트
        if(count == N) {
            for(int i = 0; i < N; i++) {
                System.out.print(sel[i] + " ");
            }
            System.out.println();
            return;
        }

        //재귀파트
        for(int i = 0; i < N; i++) {
            if(visited[i]) continue; //선택했다면
            visited[i] = true; //선택 체크
            sel[count] = arr[i];
            perm(count + 1); //다음으로
            visited[i] = false; //선택 해제
        }
    }
}
