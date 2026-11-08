import java.io.*;
import java.util.*;

public class CT_L15_가로등설치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int Q = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 마을 상태 확인
        int work = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());  // 거리의 크기
        int M = Integer.parseInt(st.nextToken());  // 초기에 존재하는 가로등의 개수

        // 가로등 위치를 오름차순으로 저장할 트리
        TreeSet<Integer> positions = new TreeSet<>();

        // 가로등 번호 -> 위치
        int[] lampPos = new int[Q+M];

        // 우선순위 큐: pq.offer(new int[] {거리, 왼쪽 위치, 오른쪽 위치})
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) 
                return Integer.compare(b[0], a[0]);  // 거리 큰 순
                    
            return Integer.compare(a[1], b[1]);  // 거리가 같으면 왼쪽 위치 작은 순
        });

        for (int id = 1; id <= M; id++) {
            int pos = Integer.parseInt(st.nextToken());
            
            positions.add(pos);
            lampPos[id] = pos;
        }

        // 초기 인접 구간들을 pq에 넣기
        Integer prev = null;
        for (int pos : positions) {
            if (prev != null) {
                int dist = pos - prev;
                pq.offer(new int[] {dist, prev, pos});
            }

            prev = pos;
        }

        // 가로등이 비출 수 있는지 여부
        // boolean[] isLight = new boolean[M+1];

        StringBuilder sb = new StringBuilder();

        // 작업 시작
        for (int q = 2; q <= Q; q++) {
            st = new StringTokenizer(br.readLine());
            work = Integer.parseInt(st.nextToken());

            // 가로등 추가
            if (work == 200) {
                
                // 현재는 더이상 유효하지 않은 과거 구간들 제거
                while (!pq.isEmpty()) {
                    int[] longest = pq.peek();

                    int left = longest[1];
                    int right = longest[2];

                    Integer next = positions.higher(left);

                    if (positions.contains(left) && next != null && next == right) break;

                    pq.poll();
                }

                int[] longest = pq.poll();

                int left = longest[1];
                int right = longest[2];

                // 홀수 & 짝수 한 번에 처리
                int newPos = (left + right + 1) / 2;

                M++;
                positions.add(newPos);
                lampPos[M] = newPos;

                pq.offer(new int[] {newPos - left, left, newPos});
                pq.offer(new int[] {right - newPos, newPos, right});
            }

            // 가로등 제거
            if (work == 300) {
                int D = Integer.parseInt(st.nextToken());
                int pos = lampPos[D];

                Integer left = positions.lower(pos);
                Integer right = positions.higher(pos);

                positions.remove(pos);

                if (left != null && right != null) {
                    int dist = right - left;
                    pq.offer(new int[] {dist, left, right});
                }

                lampPos[D] = 0;  // 해당 가로등 제거
            }

            // 최소 전력 계산
            if (work == 400) {
                while (!pq.isEmpty()) {
                    int[] top = pq.peek();
                    int left = top[1];
                    int right = top[2];

                    Integer next = positions.higher(left);
                    
                    if (positions.contains(left) && next != null && next == right) break;

                    pq.poll();
                }

                int[] longest = pq.peek();
                int dist = longest[0];

                int ans = dist;  // r의 2배 = dist

                int firstPos = positions.first();
                ans = Math.max(ans, (firstPos - 1) * 2);

                int lastPos = positions.last();
                ans = Math.max(ans, (N - lastPos) * 2);

                sb.append(ans).append("\n");
            }
        }

        System.out.print(sb);
    }
}