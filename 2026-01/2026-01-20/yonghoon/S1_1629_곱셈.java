import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_1629_곱셈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        long ans = power(A, B, C);
        System.out.println(ans);
    }

    static long power(long a, long b, long c) {
        // 종료
        if(b == 0) return 1;
        if(b == 1) return a % c;

        long half = power(a, b / 2, c) % c;

        if (b % 2 == 0)
            return half * half % c;
        else
            return (half * half) % c * (a % c) % c;
    }
}
