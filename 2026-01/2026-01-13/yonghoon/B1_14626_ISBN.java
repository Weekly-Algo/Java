import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1_14626_ISBN {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String ISBN = st.nextToken();
        int total = 0;
        int idx = 0;
        for(int i = 0; i < ISBN.length(); i++) {
            char c = ISBN.charAt(i);
            // *이 아닐 경우에만 체크
            if(c == '*') {
                idx = i;
                continue;
            }

            // 짝수일때 1, 홀수일때 3
            int num = c - '0';
            if(i % 2 == 0)
                total += num;
            else
                total += num * 3;
        }

        int ans = 0;
        for(int i = 0; i < 10; i++) {
            // 짝수일때 1, 홀수일때 3
            if(idx % 2 == 0) {
                if((total % 10 + i) % 10 == 0) {
                    ans = i;
                    break;
                }
            }
            else
                if((total % 10 + i * 3) % 10 == 0) {
                    ans = i;
                    break;
            }

        }
        System.out.println(ans);
    }
}
