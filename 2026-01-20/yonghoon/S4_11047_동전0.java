import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class S4_11047_동전0 {
    static int N, K; // 동전의 종류, 총 금액
    static ArrayList<Integer> list = new ArrayList<>();
    static int total = 0; // 총 동전의 개수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            list.add(Integer.parseInt(br.readLine()));
        }

        count();
        System.out.println(total);
    }

    static void count() {
        for(int i = list.size() - 1; i >= 0; i--) {
            total += K / list.get(i);
            K %= list.get(i);
        }
    }
}
