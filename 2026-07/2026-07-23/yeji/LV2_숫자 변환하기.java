import java.util.*;

class Solution {

    public int solution(int x, int y, int n) {

        // 방문 배열
        boolean[] visited = new boolean[y + 1];

        // 현재 숫자와 연산 횟수를 저장하는 큐
        ArrayDeque<int[]> q = new ArrayDeque<>();

        // 시작 숫자를 큐에 넣고 방문 처리
        q.offer(new int[]{x, 0});
        visited[x] = true;

        // 큐가 빌 때까지 반복
        while (!q.isEmpty()) {

            int[] cur = q.poll();

            int number = cur[0];
            int count = cur[1];

            // y를 만들었으면 연산 횟수 반환
            if (number == y) {
                return count;
            }

            // 현재 숫자에서 만들 수 있는 다음 숫자
            int[] nextNumbers = {
                number + n,
                number * 2,
                number * 3
            };

            for (int next : nextNumbers) {

                // y를 넘거나 이미 방문했으면 건너뜀
                if (next > y || visited[next]) {
                    continue;
                }

                // 다음 숫자 방문 처리
                visited[next] = true;

                // 다음 숫자와 증가한 연산 횟수를 큐에 추가
                q.offer(new int[]{next, count + 1});
            }
        }

        // y를 만들 수 없으면 -1 반환
        return -1;
    }
}