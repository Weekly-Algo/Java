import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class S2_16953_A_B {
    static long ans = -1; // 정답 카운트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Integer.parseInt(st.nextToken());
        long B = Integer.parseInt(st.nextToken());

        // bfs호출
        bfs(A, B);

        System.out.println(ans);
    }

    // bfs
    static void bfs(long A, long B) {
        Queue<long[]> q = new ArrayDeque<>();
        q.offer(new long[]{A, 1});
        long num = 0; // 현재 수
        long cnt = 1; // 현재 카운트

        while(!q.isEmpty()) {
            long[] cur = q.poll();
            num = cur[0];
            cnt = cur[1];

            // B랑 같으면 카운트 반환
            if(num == B) {
                ans = cnt;
                return;
            }

            // 아니라면 뒤에 1붙이거나 *2 체크
            // 1을 붙인게 B보다 길이가 길어지면 B가 될 수 없으므로 continue
            long next = num * 10 + 1;
            if( next <= B )
                q.offer(new long[] {next, cnt + 1 });

            // 2를 곱한게 B보다 커지면 B가 될 수 없으므로 continue
            if(num * 2 <= B)
                q.offer(new long[] {num * 2, cnt + 1 });
        }
    }
}
