import java.util.*;

class Solution {
	int N, M;
	char[][] grid;
	boolean[][] visited;
	int[] dr = { -1, 1, 0, 0 };
	int[] dc = { 0, 0, -1, 1 };

	public int numIslands(char[][] grid) {
		this.grid = grid;
		N = grid.length;
		M = grid[0].length;

		visited = new boolean[N][M];

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == '1' && !visited[i][j]) {
					cnt++;
					bfs(i, j);
				}
			}
		}
		return cnt;
	}

	void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c });
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = curr[0] + dr[i];
				int nc = curr[1] + dc[i];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && grid[nr][nc] == '1' && !visited[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}
		}
	}
}