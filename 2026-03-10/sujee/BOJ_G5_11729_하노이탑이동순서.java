import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_11729_하노이탑이동순서 {
    static int K = 0;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        sb = new StringBuilder();
        hanoi(1, 2, 3, N);
        System.out.println(K);
        System.out.println(sb);

    }


    static void hanoi(int from, int by, int to, int n) {
        K++;
        if(n == 1) {
            String tmp = from + " " + to + "\n";
            sb.append(tmp);
            return;
        }

        hanoi(from, to, by, n-1);
        String tmp = from + " " + to + "\n";
        sb.append(tmp);
        hanoi(by, from, to, n-1);
    }
}
