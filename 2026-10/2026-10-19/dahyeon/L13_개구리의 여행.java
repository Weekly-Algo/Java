import java.util.*;
// 안전 . 미끄러운 s 천적 #
// 좌 우 상 하 가능
// 안전한 돌만 가능, 미끄러운 돌은 도착 ㄴ, 천적 돌은 지나치는 경로도 안됨
// 증가, k는 1-5까지 k^2 시간 소요 / 감소, 1만큼 소요 / 기본 1시간 소요
public class Main {
    public static void main(String[] args) {
        // Please write your code here.
         BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

         int N = Integer.parseInt(br.readLine());
         char[][] map = new char[N][N];

         for(int i = 0; i < N; i++){
             map[i] = br.readLine().toCharArray();
         }

         int Q = Integer.parseInt(br.readLine());

         for(int i = 0; i < Q; i++){
             StringTokenizer st = new StringTokenzier(br.readLine());
             int sr = Integer.parseInt(st.nextToken()) - 1;
             int sc = Integer.parseInt(st.nextToken()) - 1;
             int er = Integer.parseInt(st.nextToken()) - 1;
             int ec = Integer.parseInt(st.nextToken()) - 1;

         }

        return -1;
    }
}