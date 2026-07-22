package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;


public class G5_10026_적록색약 {
	
	static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;	
		}
	}
	
	static int N;
	static char[][] map;
	static boolean[][] visited;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		
		for (int i=0; i<N; i++) {
				String line = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		visited = new boolean[N][N];
		int count = 0;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visited[i][j]) {
					bfs(i,j);
					count++;
				}
			}
		} // 일반인 bfs
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j] == 'G') {
					map[i][j] = 'R';
				}
			}
		} // 적녹색약 맵 변환
		
		visited = new boolean[N][N];
		int count2 = 0;
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if(!visited[i][j]) {
					bfs(i,j);
					count2++;
				}
			}
		} // 적록색약 bfs
		
		System.out.println(count+ " " + count2);
		
	} // main
	
	static void bfs(int x, int y) {
		Deque<Point> deque = new ArrayDeque<>();
		deque.add(new Point(x, y));
		visited[x][y] = true;
		
		char color = map[x][y];
		
		while (!deque.isEmpty()) {
			Point cur = deque.poll();
			
			for (int i=0; i<4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (!visited[nx][ny] && map[nx][ny] == color) {
					visited[nx][ny] = true;
					deque.add(new Point(nx,ny));
				}
			}
			
		} // while
	} // bfs
}
