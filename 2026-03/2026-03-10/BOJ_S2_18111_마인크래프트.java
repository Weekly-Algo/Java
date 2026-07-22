import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_18111_마인크래프트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        int minTime = Integer.MAX_VALUE;
        int maxHeight = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i <= 256; i++) {
            int height = i; //기준 높이
            int remove = 0; //제거 수
            int build = 0; //쌓을 수
            int time = 0; //걸린 시간
            
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < M; c++) { //모든 칸 하나씩을 반복하며
                    if(arr[r][c] < height) { //기준 높이보다 낮다면
                        build += height - arr[r][c]; //쌓을 수
                    } else if(arr[r][c] > height) { //기준 높이보다 높다면
                        remove += arr[r][c] - height; //제거 수
                    }
                }
            }

            if(B + remove >= build) { //인벤토리로 기준 높이를 만들 수 있는 경우에만
                time = remove * 2 + build;

                if(time < minTime) {
                    minTime = time;
                    maxHeight = height;
                } else if(time == minTime) {
                    maxHeight = Math.max(maxHeight, height);
                }
            }
        }

        System.out.println(minTime + " " + maxHeight);
    }
}
