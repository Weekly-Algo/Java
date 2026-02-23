import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_30804_과일_탕후루 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N]; // 탕후루 배열
        int[] fruit = new int[10]; // 1 ~ 9까지의 과일

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int left = 0, right = 0, max = 0, kind = 0;

        while(right < N) {
            // 해당 과일이 처음 나온거라면 종류 증가시킴
            // 갯수 증가
            if(fruit[arr[right]] == 0) kind++;
            fruit[arr[right]]++;

            // 종류가 2개보다 많다면 왼쪽을 움직이면서 과일 감소시킴
            // 0이되면 종류도 1줄어듦
            while(kind > 2) {
                fruit[arr[left]]--;
                if(fruit[arr[left]] == 0) kind--;
                left++;
            }

            // 최대 길이 갱신
            max = Math.max(max, right - left + 1);
            right++;
        }

        System.out.println(max);
    }
}
