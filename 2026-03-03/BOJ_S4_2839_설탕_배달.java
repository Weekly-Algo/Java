import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S4_2839_설탕_배달 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count = 0;

        while(N >= 0) {
            if(N % 5 == 0) { //5로 나누어진다면
                count += N / 5; //3으로 나눈 수 더하기
                System.out.println(count);
                return;
            } else { //아니라면
                N -= 3; //3을 하나 세기
                count++;
            }
        }

        System.out.println(-1);
    }
}
