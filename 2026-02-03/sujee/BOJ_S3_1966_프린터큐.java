import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S3_1966_프린터큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());
        for(int i = 0; i < tc; i++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int dest = -1;

            Queue<int[]> q = new ArrayDeque<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int x = Integer.parseInt(st.nextToken());
                q.offer(new int[] {j, x});
                pq.offer(x);
            }

            int ans = 0;
            while(!q.isEmpty()){
                int[] curr = q.poll();
                int currIdx = curr[0];
                int currCost = curr[1];

                if(currCost == pq.peek()){
                    pq.poll();
                    ans++;
                    if(currIdx == M){
                        System.out.println(ans);
                        break;
                    }
                } else{
                    q.offer(curr);
                }

            }
        }
    }
}
