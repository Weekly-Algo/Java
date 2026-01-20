import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_2579_계단오르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        int[] score = new int[N + 1];

        for (int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        // 1과 2 채워두기
        if(N >= 1) score[1] = arr[1];
        if(N >= 2) score[2] = arr[1] + arr[2];

        // 해당 칸에 도달할 수 있는 경우의 수
        for(int i = 3; i <= N; i++){
            score[i] = Math.max(score[i-2], score[i-3] + arr[i-1]) + arr[i];
        }

        System.out.println(score[N]);
    }
}
