import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_16953_A_화살표_B {
    //B -> A로 생각해보기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int cnt = 1;

        while(B > A) {
            if (B % 10 == 1) { // 마지막 숫자가 1이라면
                B /= 10;
                cnt++;
            } else if(B % 2 == 0) { // 짝수라면
                B /= 2;
                cnt++;
            } else { // 만들 수 없다면
                break;
            }
        }

        System.out.println(B == A ? cnt : -1);
    }
}
