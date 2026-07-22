import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S2_2108_통계학 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        double sum = 0;
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
        }

        Arrays.sort(arr);
        System.out.println(Math.round(sum / N));
        System.out.println(arr[N/2]);

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++){
            if(map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])+1);
            }
            else{
                map.put(arr[i], 1);
            }
        }

        int maxCnt = 0;
        for(int x : map.values()){
            if (x > maxCnt) maxCnt = x;
        }
        List<Integer> ans = new ArrayList<>();
        for(int x : map.keySet()){
            if(map.get(x) == maxCnt){
                ans.add(x);
            }
        }

        if(ans.size() == 1){
            System.out.println(ans.get(0));
        }
        else{
            Collections.sort(ans);
            System.out.println(ans.get(1));
        }
        System.out.println(arr[N-1] - arr[0]);

    }
}
