import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new ArrayDeque<>();

        // 1번부터 N번까지 카드를 순서대로 넣는다.
        for (int i = 1; i <= N; i++) {
            queue.offer(i);
        }

        // 카드가 한 장 남을 때까지 반복한다.
        while (queue.size() > 1) {
            // 가장 위의 카드를 버린다.
            queue.poll();

            // 그다음 카드를 꺼내 맨 아래로 옮긴다.
            int card = queue.poll();
            queue.offer(card);
        }

        // 마지막으로 남은 카드 출력
        System.out.println(queue.peek());
    }
}