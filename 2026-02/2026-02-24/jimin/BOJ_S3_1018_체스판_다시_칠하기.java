import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S3_1018_체스판_다시_칠하기 {
    static int[] dr = {-1, 1, 0, 0}; //상하좌우
    static int[] dc = {0, 0, -1, 1}; //상하좌우
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];

        for(int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int min = Integer.MAX_VALUE;

        //시작점 범위만큼 반복
        for(int i = 0; i <= N-8; i++) {
            for(int j = 0; j <= M-8; j++) {
                int countW = 0;
                int countB = 0;

                for(int n = 0; n < 8; n++) {
                    for(int m = 0; m < 8; m++) {

                        //시작이 W라고 가정
                        if((n+m) % 2 == 0 && arr[i + n][j + m] != 'W'
                                || (n+m) % 2 == 1 && arr[i + n][j + m] == 'W') {
                            countW++;
                        }

                        //시작이 B라고 가정
                        if((n+m) % 2 == 0 && arr[i + n][j + m] != 'B'
                                || (n+m) % 2 == 1 && arr[i + n][j + m] == 'B') {
                            countB++;
                        }

                    }
                }

                //그 중 최소 변경 개수 구하기
                int localMin = Math.min(countW, countB);
                min = Math.min(min, localMin);
            }
        }

        System.out.println(min);

    }
}
