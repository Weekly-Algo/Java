/*
 * [문제 정보] 개구리의 여행
 * [사용 알고리즘] 다익스트라, 3차원 상태 관리
 *
 * [풀이 핵심]
 * 1. 개구리의 상태를 단순한 위치 (r, c)가 아니라 (r, c, 현재 점프력)으로 관리한다.
 * 2. dist[r][c][jump]에 해당 위치에 해당 점프력으로 도달하는 최소 시간을 저장한다.
 * 3. 현재 점프력만큼 상하좌우로 점프하며, 
 *    점프 경로에 '#'이 있거나 도착 위치가 'S'이면 이동할 수 없다. 
 *    일반 점프의 비용은 1이다.
 * 4. 점프력이 5보다 작다면 jump + 1로 증가시킬 수 있으며,
 *    증가 후 점프력이 k일 때 k^2의 시간이 필요하다.
 * 5. 점프력이 1보다 크다면 1 ~ jump-1 중 원하는 점프력으로 감소시킬 수 있으며,
 *    감소 비용은 항상 1이다.
 * 6. 행동마다 필요한 시간이 서로 다르므로 PriorityQueue를 이용한 다익스트라로
 *    누적 시간이 가장 작은 상태부터 탐색한다.
 *
 * [시간 복잡도]
 * O(Q * N^2 log(N^2))
 * - 점프력은 최대 5로 고정되어 있으므로 상태 수는 O(N^2)
 * - 각 상태에서 최대 4방향 이동과 점프력 변경을 확인한다.
 */
import java.io.*;
import java.util.*;

public class CT_L13_개구리의여행 {
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int N;
	static int inf = Integer.MAX_VALUE;
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		/* 
		 * map: 돌이 있는 정사각형 맵, 0~N-1 인덱스 사용
		 * '.': 안전한 돌
		 * 'S': 미끄러운 돌
		 * '#': 천적이 사는 돌
		 */
		map = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int Q = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= Q; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int r1 = Integer.parseInt(st.nextToken()) - 1;
			int c1 = Integer.parseInt(st.nextToken()) - 1;
			int r2 = Integer.parseInt(st.nextToken()) - 1;
			int c2 = Integer.parseInt(st.nextToken()) - 1;
			
			sb.append(dijkstra(r1, c1, r2, c2)).append("\n");
		}// tc
		
		System.out.print(sb);
	}
	
	// BFS + Dijkstra
	// (r1, c1): 출발, (r2, c2): 도착
	private static int dijkstra(int r1, int c1, int r2, int c2) {
		// Dijkstra 를 위한 우선순위 큐
		PriorityQueue<int[]> pq = new PriorityQueue<>(
				(a, b) -> Integer.compare(a[0], b[0])
		);
		
		// (r, c)에 점프력(jump)인 상태로 도달하는 최소 시간
		int[][][] dist = new int[N][N][6];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(dist[i][j], inf);
			}
		}
		
		// 출발은 점프력 1로 시작
		dist[r1][c1][1] = 0;
		
		// {time, r, c, jump}
		pq.offer(new int[] {0, r1, c1, 1});
		
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int currTime = curr[0];  // 누적 시간
			int currR = curr[1];
			int currC = curr[2];  
			int currJump = curr[3];  // 점프력
			
			if (currTime > dist[currR][currC][currJump]) continue;
			
			// 다익스트라는 최초 도착 순간이 최소 시간
			if (currR == r2 && currC == c2)
				return currTime;
			
			// 현재 점프력으로 상하좌우 점프
			for (int d = 0; d < 4; d++) {
				int nr = currR + dr[d] * currJump;
				int nc = currC + dc[d] * currJump;
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				// 점프 경로에 천적(#)이 있는지 확인
				boolean canMove = true;
				
				for (int step = 1; step <= currJump; step++) {
					int checkR = currR + dr[d] * step;
					int checkC = currC + dc[d] * step;
					
					if (map[checkR][checkC] == '#') {
						canMove = false;
						break;
					}
				}
				
				if (!canMove) continue;
				
				// 미끄러운 돌에는 착지할 수 없음
                if (map[nr][nc] == 'S') continue;
                
                // 점프 비용은 1
                int nextTime = currTime + 1;
                
                if (nextTime < dist[nr][nc][currJump]) {
                    dist[nr][nc][currJump] = nextTime;
                    pq.offer(new int[] {nextTime, nr, nc, currJump});
                }
			}
			
			// 점프력 증가
			if (currJump < 5) {
				int nextJump = currJump + 1;
				
				// 점프력 증가 후, 점프력이 k라면 k^2 시간이 필요
				int nextTime = currTime + nextJump * nextJump;
				
				if (nextTime < dist[currR][currC][nextJump]) {
					dist[currR][currC][nextJump] = nextTime;
					pq.offer(new int[] {nextTime, currR, currC, nextJump});
				}
			}
			
			// 점프력 감소
			if (currJump > 1) {
				// 현재 점프력보다 작은 어떤 값으로든 감소 가능
				for (int nextJump = 1; nextJump < currJump; nextJump++) {
					// 감소 비용은 항상 1
					int nextTime = currTime + 1;
					
					if (nextTime < dist[currR][currC][nextJump]) {
						dist[currR][currC][nextJump] = nextTime;
						pq.offer(new int[] {nextTime, currR, currC, nextJump});
					}
				}
			}
		}
		
		return -1;
	}
}
