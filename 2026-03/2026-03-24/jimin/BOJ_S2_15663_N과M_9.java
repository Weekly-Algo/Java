import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S2_15663_N과M_9 {
    static int N, M;
    static int[] arr;
    static int[] sel; //선택배열
    static boolean[] visited; //방문배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        sel = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr); //정렬

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

        int prev = -1;
        //재귀파트
        for(int i = 0; i < N; i++) {
            if(visited[i]) continue; //방문했다면
            if(arr[i] == prev) continue; //이전에 사용한 숫자라면

            visited[i] = true; //방문체크
            sel[count] = arr[i]; //선택
            prev = arr[i]; //선택한 숫자
            perm(count + 1); //다음으로

            visited[i] = false; //방문해제
        }

    }
}
