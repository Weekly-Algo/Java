import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S2_2630_색종이만들기 {
    static int[][] paper;
    static int Blue = 0;
    static int White = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        paper = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        count(N, 0, 0);
        System.out.println(White);
        System.out.println(Blue);

    }

    public static void count(int N, int sX, int sY){
        int color = paper[sX][sY];
        if(N == 1) {
            if(color == 1) Blue++;
            else if(color == 0) White++;
            return;
        };
        for(int i = sX; i < sX+N; i++){
            for(int j = sY; j < sY+N; j++){
                if(color != paper[i][j]){
                    count(N/2, sX, sY);
                    count(N/2, sX + N/2, sY);
                    count(N/2, sX, sY + N/2);
                    count(N/2, sX + N/2, sY + N/2);
                    return;
                }
            }
        }
        if(color == 1) Blue++;
        else if(color == 0) White++;
    }
}
