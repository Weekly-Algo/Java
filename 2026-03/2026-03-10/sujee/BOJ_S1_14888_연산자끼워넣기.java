import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S1_14888_연산자끼워넣기 {
    static int N;
    static int[] arr; // 숫자배열
    static int[] op; // 연산자 개수 배열 : +, -, *, /
    static int max = Integer.MIN_VALUE; // 결과의 최댓값 -> 일단 최솟값으로 초기화
    static int min = Integer.MAX_VALUE; // 결과의 최솟값 -> 일단 최댓값으로 초기화


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        op = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        test(0, arr[0], new int[4]);
        System.out.println(max);
        System.out.println(min);
    }

    static void test(int idx, int tmp, int[] cnt){
        if(idx == N-1) {
            min = Math.min(min, tmp);
            max = Math.max(max, tmp);
            return;
        }

        for(int i = 0; i < 4; i++) {
            if(cnt[i] == op[i]) continue;
            cnt[i]++;
            switch(i){
                case 0:
                    test(idx+1, tmp+arr[idx+1], cnt);
                    break;
                case 1:
                    test(idx+1, tmp-arr[idx+1], cnt);
                    break;
                case 2:
                    test(idx+1, tmp*arr[idx+1], cnt);
                    break;
                case 3:
                    test(idx+1, tmp/arr[idx+1], cnt);
                    break;
            }
            cnt[i]--;
        }

    }

}
