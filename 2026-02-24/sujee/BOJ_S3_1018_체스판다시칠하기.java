import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S3_1018_체스판다시칠하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] board = new char[N][M];
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < M; j++){
                board[i][j] = str.charAt(j);
            }
        } // 기본 보드 정보 입력 완료!!


        int min = N*M; // 정답이 될 다시 칠해야할 정사각형 개수의 최솟값
        for(int i = 0; i <= N-8; i++){
            for(int j = 0; j <= M-8; j++){
                int cntW = 0; // 젤 왼쪽이 흰색일 경우
                int cntB = 0; // 제일 왼쪽이 검정색일 경우
                // 잘라낸 8x8 보드 확인...
                for(int a = 0; a < 8; a++) {
                    for(int b = 0; b < 8; b++){
                        char curr = board[i+a][j+b];
                        if ((a + b) % 2 == 0) {
                            // 시작색과 같은 색이어야 하는 칸
                            if(curr != 'W') cntW++;
                            if(curr != 'B') cntB++;
                        } else {
                            // 시작색과 반대 색이어야 하는 칸
                            if(curr != 'B') cntW++;
                            if(curr != 'W') cntB++;
                        }
                    }
                }

                min = Math.min(cntW, min);
                min = Math.min(cntB, min);
            }
        }

        System.out.println(min);
    }
}
