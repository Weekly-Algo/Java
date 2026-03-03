import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_G5_2447_별찍기10 {
    static int N;
    static char[][] pattern;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        pattern = new char[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                pattern[i][j] = '*';
            }
        } // 시작...

        erase(0, 0, N);

        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                ans.append(pattern[i][j]);
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }

    static void erase(int X, int Y, int len) {
        len /= 3;

        if(len == 0) return;

        int x = X + len;
        int y = Y + len;

        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                pattern[x+i][y+j] = ' ';
            }
        }

        erase(X, Y, len);
        erase(X, Y+len, len);
        erase(X, Y + len*2, len);
        erase(X + len, Y, len);
        erase(X + len, Y+len*2, len);
        erase(X + len*2, Y, len);
        erase(X + len*2, Y+len, len);
        erase(X + len*2, Y+len*2, len);
    }
}
