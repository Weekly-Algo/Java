import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S1_11286_절댓값_힙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // 절댓값 힙 생성
        Queue<Integer> pq = new PriorityQueue<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        int abs1 = Math.abs(o1);
                        int abs2 = Math.abs(o2);
                        // 절댓값이 같으면 작은 값이 앞에가도록
                        if(abs1 == abs2)
                            // o1이 음수면 앞으로, 양수면 뒤로 가게 됨
                            return o1;

                        return abs1 - abs2;
                    }
                }
        );

        for(int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if(x == 0) {
                if(pq.isEmpty())
                    sb.append(0).append("\n");
                else
                    sb.append(pq.poll()).append("\n");
            } else
                pq.offer(x);
        }
        System.out.println(sb);
    }
}
