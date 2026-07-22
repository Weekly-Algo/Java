import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class B1_28702_FizzBuzz {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int nextIdx = 0;

        // 인덱스 찾기
        for(int i = 0; i < 3; i++) {
            String s = br.readLine();
            if(!s.equals("Fizz") && !s.equals("Buzz") && !s.equals("FizzBuzz")) {
                nextIdx = Integer.parseInt(s) + 3 - i;
                break;
            }
        }

        // 출력
        String ans;
        if(nextIdx % 3 == 0 && nextIdx % 5 == 0)
            ans = "FizzBuzz";
        else if(nextIdx % 3 == 0)
            ans = "Fizz";
        else if(nextIdx % 5 == 0)
            ans = "Buzz";
        else
            ans = nextIdx + "";

        System.out.println(ans);
    }
}
