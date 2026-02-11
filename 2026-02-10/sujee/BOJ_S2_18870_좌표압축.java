import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S2_18870_좌표압축 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] sortArr = new int[N];
        Map<Integer, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            arr[i] = x;
            sortArr[i] = x;
        }

        Arrays.sort(sortArr);

        for(int i = 0; i < N; i++){
            if(i == 0) map.put(sortArr[i], 0);
            else if(sortArr[i] != sortArr[i-1]) map.put(sortArr[i], map.get(sortArr[i-1]) + 1);
            else if(sortArr[i] == sortArr[i-1]) map.put(sortArr[i], map.get(sortArr[i-1]));
        }

        for(int i = 0; i < N; i++){
            sb.append(map.get(arr[i]) + " ");
        }

        System.out.println(sb);
    }
}
