import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_S5_1316_그룹단어체커 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int ans = 0;
        for(int i = 0; i < N; i++){
            String str = br.readLine();
            List<Character> list = new ArrayList<>();

            for(int k = 0; k < str.length(); k++) {
                char ch = str.charAt(k);

                if(k != 0 && ch == str.charAt(k-1)) continue;
                if(list.contains(ch)) {
                    ans -= 1;
                    break;
                }
                list.add(ch);
            }
            ans += 1;
        }
        System.out.println(ans);
    }
}
