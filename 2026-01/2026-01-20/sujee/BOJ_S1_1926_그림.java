import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_S1_1926_그림 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 사방탐색용 배열
        int[] arr1 = {-1, 0, 0, 1};
        int[] arr2 = {0, -1, 1, 0};

        int[][] paper = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 기본 정보 입력 완료

        int cntPicture = 0; // 그림 개수
        int max = 0; // 최대 그림 사이즈 측정

        // BFS 시작해보자
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(paper[i][j] == 1 && !visited[i][j]){
                    cntPicture++;

                    Queue<int[]> q = new ArrayDeque<>();
                    q.offer(new int[]{i, j});
                    visited[i][j] = true;

                    int size = 1; // 그림 사이즈 측정

                    while(!q.isEmpty()){
                        int[] curr = q.poll();
                        int c1 = curr[0];
                        int c2 = curr[1];

                        for(int k = 0; k < 4; k++){
                            int n1 = c1 + arr1[k];
                            int n2 = c2 + arr2[k];

                            if(n1 >= 0 && n1 < N && n2 >= 0 && n2 < M){
                                if(!visited[n1][n2] && paper[n1][n2] == 1){
                                    visited[n1][n2] = true;
                                    q.offer(new int[]{n1, n2});
                                    size++;
                                }
                            }
                        }
                    }
                max = Math.max(max, size);
                }
            }
        }

        System.out.println(cntPicture);
        System.out.println(max);
    }
}
