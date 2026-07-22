import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S5_1436_영화감독_숌 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int num = 0;
        int count = 0;

        while(true) {
            if(Integer.toString(num).contains("666")) {
                count++;
            }

            if(count == N) {
                System.out.println(num);
                break;
            }

            num++;
        }
    }
}
