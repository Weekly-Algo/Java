package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_16928_뱀과사다리게임 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 사다리
        int M = Integer.parseInt(st.nextToken()); // 뱀
        
        int[] board = new int[101];
        // 기본값: 자기 자신의 칸으로 이동
        for (int i = 1; i <= 100; i++) {
            board[i] = i;
        }
        
        // 사다리와 뱀의 정보 입력 (해당 칸에 도착하면 이동할 목적지 갱신)
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            board[u] = v; 
        }
        
        System.out.println(bfs(board));
        
	} // main 
	
	private static int bfs(int[] board) {
        Queue<Integer> queue = new LinkedList<>();
        int[] rolls = new int[101];           // 해당 칸에 도달하기 위해 주사위를 굴린 횟수
        boolean[] visited = new boolean[101]; // 방문 여부 체크

        // 1번 칸에서 시작
        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 100번 칸에 도착했다면 기록된 굴림 횟수 반환 후 종료
            if (current == 100) {
                return rolls[100];
            }

            // 주사위 1부터 6까지 굴리기
            for (int dice = 1; dice <= 6; dice++) {
                int next = current + dice;

                // 100번 칸을 넘어가면 무시
                if (next > 100) continue;

                // 사다리나 뱀이 있다면 그 목적지로 이동, 없다면 현재 next 유지
                next = board[next];

                // 아직 방문하지 않은 칸이라면 큐에 추가하고 횟수 증가
                if (!visited[next]) {
                    visited[next] = true;
                    rolls[next] = rolls[current] + 1;
                    queue.offer(next);
                }
            }
        }
        return 0;
    }

}
