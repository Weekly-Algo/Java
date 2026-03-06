import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S3_9375_패션왕_신해빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            Map<String, Integer> map = new HashMap<>();
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String sort = st.nextToken();

                // 해당 옷 종류가 원래 있었다면 +1, 아니면 새로 추가
                if(map.containsKey(sort))
                    map.put(sort, map.get(sort) + 1);
                else
                    map.put(sort, 1);
            }

            // 의상을 입을 수 있는 개수
            // headgear 2개, eyewear 1개일 경우
            // x / 1 / 2  , x / 1 => 3 * 2 = 6개
            int total = 1;
            Set<String> sorts = map.keySet();
            for(String sort : sorts) {
                total *= (map.get(sort) + 1);
            }

            sb.append(total - 1).append("\n");
        }
        System.out.println(sb);
    }
}
