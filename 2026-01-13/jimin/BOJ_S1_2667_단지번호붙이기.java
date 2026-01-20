import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S1_2667_단지번호붙이기 {
    static int N; //배열의 크기
    static char[][] arr; //배열
    static boolean[][] visited; //방문 배열
    static Queue<int[]> queue;
    static int count;
    
    static int[] dr = {-1, 1, 0, 0}; //상하좌우
    static int[] dc = {0, 0, -1, 1}; //상하좌우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new char[N][N]; //배열 생성
        visited = new boolean[N][N]; //방문 배열 생성

        for(int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        List<Integer> countList = new ArrayList<>(); //리스트 생성
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(arr[i][j] == '1' && !visited[i][j]) {
                    count = 0;
                    bfs(i, j);
                    countList.add(count);
                }
            }
        }

        Collections.sort(countList);
        System.out.println(countList.size()); //총 단지 수
        for(int count :  countList) {
            System.out.println(count); //각 단지의 집의 수
        }
    }

    public static void bfs(int x, int y) {
        queue = new ArrayDeque<>();
        queue.add(new int[]{x, y}); //큐에 넣기
        visited[x][y] = true; //방문 처리
        count++;

        while(!queue.isEmpty()) { //큐가 빌 때까지
            int[] curr = queue.poll();
            int r =  curr[0];
            int c = curr[1];

            for(int i = 0; i < 4; i++) { //사방탐색
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue; //범위 안에 있니?
                if(arr[nr][nc] == '1' && !visited[nr][nc]) { //방문하지 않았다면
                    queue.add(new int[]{nr, nc}); //큐에 넣기
                    visited[nr][nc] = true; //방문처리
                    count++;
                }

            }

        }
    }
}
