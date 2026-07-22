import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class S2_1541_잃어버린_괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 핵심 아이디어: 더할 수 있는 수는 다 더하고 그 후에 빼는 게 가장 작은 수가 됨

        // '-' 기준으로 쪼갬
        String[] subStr = br.readLine().split("-");

        int ans = Integer.MAX_VALUE;
        // subStr의 각 요소에는 '+'와 숫자만 존재함
        for(int i = 0; i < subStr.length; i++) {
            int temp = 0;

            // '+' 기준으로 쪼갬
            String[] addStr = subStr[i].split("\\+");
            for(int j = 0; j < addStr.length; j++) {
                temp += Integer.parseInt(addStr[j]);
            }

            // 처음 값이라면 그대로고 이후에 다 빼주기
            if(ans == Integer.MAX_VALUE)
                ans = temp;
            else
                ans -= temp;
        }

        System.out.println(ans);
    }
}
