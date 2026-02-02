import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class S2_11279_최대힙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < N; i++){
            int x = Integer.parseInt(br.readLine());
            // 큐가 비어있고 출력해야하면 0 출력
            if(q.isEmpty() && x == 0)
                sb.append(0).append("\n");
            // 비어있지 않고 0이면 하나 꺼내서 출력
            else if(x == 0)
                sb.append(q.poll()).append("\n");
            // 0이 아니면 수 넣기
            else
                q.offer(x);
        }

        System.out.println(sb);
    }
}
