import java.util.*;

class Solution {

    public int numIslands(char[][] grid) {

        int answer = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        // 상하좌우
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};


        // 전체 grid 탐색
        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                // 아직 방문하지 않은 땅 발견
                if (grid[row][col] == '1') {

                    // 새로운 섬 발견
                    answer++;

                    Queue<int[]> queue = new LinkedList<>();

                    // 현재 위치를 큐에 넣기
                    queue.offer(new int[]{row, col});

                    // 방문 처리
                    grid[row][col] = '0';


                    // 현재 섬에 연결된 땅을 모두 탐색
                    while (!queue.isEmpty()) {

                        int[] current = queue.poll();

                        int currentRow = current[0];
                        int currentCol = current[1];


                        // 상하좌우 확인
                        for (int i = 0; i < 4; i++) {

                            int nextRow = currentRow + dr[i];
                            int nextCol = currentCol + dc[i];


                            // 배열 범위 밖이면 건너뛰기
                            if (nextRow < 0 ||
                                    nextRow >= rows ||
                                    nextCol < 0 ||
                                    nextCol >= cols) {

                                continue;
                            }


                            // 아직 방문하지 않은 땅이면
                            if (grid[nextRow][nextCol] == '1') {

                                // 방문 처리 (0이면 이제 조건 충족 X)
                                grid[nextRow][nextCol] = '0';

                                // 다음 탐색을 위해 큐에 추가
                                queue.offer(
                                        new int[]{nextRow, nextCol}
                                );
                            }
                        }
                    }
                }
            }
        }

        return answer;
    }
}