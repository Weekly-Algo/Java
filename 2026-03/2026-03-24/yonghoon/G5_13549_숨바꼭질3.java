import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G5_13549_숨바꼭질3 {
    static int N, K; // N: 수빈이의 위치, K: 동생의 위치
    static int ans = 0; // 가장 빠른 시간
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();

        System.out.println(ans);
    }

    static void  bfs() {
        Deque<Integer> dq = new ArrayDeque<>();
        int[] time = new int[100001]; // 해당 위치 까지 걸리는 시간에 대한 배열
        Arrays.fill(time, -1); // time을 -1로 채움
        time[N] = 0; // 시작위치는 0초
        dq.offer(N);

        while(!dq.isEmpty()) {
            int curr = dq.poll();
            // 동생의 위치에 도달하면 종료
            if(curr == K) {
                ans = time[curr];
                return;
            }

            // 다음 위치로 이동한 후 큐에 넣기
            // 순간이동은 0초이므로 1초걸리는 로직과 분리가 필요
            int tp = curr * 2;
            // 범위 안에 들어오고 방문x or 다음 위치의 시간이 현재보다 클 때 이동
            if(tp >= 0 && tp <= 100000 && (time[tp] == -1 || time[tp] > time[curr])) {
                time[tp] = time[curr];
                dq.addFirst(tp);
            }

            // 걸어서 가기
            int[] walk = new int[] {curr - 1, curr + 1};
            for(int next : walk) {
                // 범위 안에 들어오고 방문x or 다음 위치의 시간이 현재보다 클 때 이동
                if(next >= 0 && next <= 100000 && (time[next] == -1 || time[next] > time[curr] + 1)) {
                    time[next] = time[curr] + 1;
                    dq.addLast(next);
                }
            }
        }
    }
}
