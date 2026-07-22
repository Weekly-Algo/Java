import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_G5_10026_적록색약 {
    static int N;
    static char[][] arr;
    static boolean[][] visited_GB; // 적록색약
    static boolean[][] visited_normal; // not 적록색약
    static int cnt_GB = 0;
    static int cnt_normal = 0;
    static int[] arr1 = {-1, 0, 0, 1}; // 사방탐색용...
    static int[] arr2 = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        visited_GB = new boolean[N][N];
        visited_normal = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited_GB[i][j]) {
                    bfs_GB(i, j);
                    cnt_GB++;
                }
                if(!visited_normal[i][j]){
                    bfs_normal(i, j);
                    cnt_normal++;
                }
            }
        }


        System.out.println(cnt_normal + " " + cnt_GB);
    }

    static void bfs_GB(int s1, int s2) { // 적록색약O
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{s1, s2});
        visited_GB[s1][s2] = true;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int c1 = curr[0];
            int c2 = curr[1];
            for (int i = 0; i < 4; i++) {
                int n1 = curr[0] + arr1[i];
                int n2 = curr[1] + arr2[i];
                if (n1 >= 0 && n1 < N && n2 >= 0 && n2 < N && !visited_GB[n1][n2]) {

                    if (arr[n1][n2] == arr[c1][c2] || (arr[n1][n2] == 'G' && arr[c1][c2] == 'R') || (arr[n1][n2] == 'R' && arr[c1][c2] == 'G')) {
                        q.offer(new int[]{n1, n2});
                        visited_GB[n1][n2] = true;
                    }
                }
            }
        }
    }
    static void bfs_normal(int s1, int s2){ // 적록색약X
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{s1, s2});
        visited_normal[s1][s2] = true;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int c1 = curr[0];
            int c2 = curr[1];
            for (int i = 0; i < 4; i++) {
                int n1 = curr[0] + arr1[i];
                int n2 = curr[1] + arr2[i];
                if (n1 >= 0 && n1 < N && n2 >= 0 && n2 < N && !visited_normal[n1][n2]) {
                    if (arr[n1][n2] == arr[c1][c2]) {
                        q.offer(new int[]{n1, n2});
                        visited_normal[n1][n2] = true;
                    }
                }
            }
        }
    }
}



