import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[][] arr;
    static int[][][] dist;
    static int N, Q;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        
        //배열 입력 받기
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        Q = Integer.parseInt(br.readLine());
        for(int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            dijkstra(r1 - 1, c1 - 1, r2 - 1, c2 - 1);
        }
        
    }

    public static void dijkstra(int startR, int startC, int endR, int endC) {
        //위치, 점프력별 최소시간 저장
        dist = new int[N][N][6];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE); //최대값으로 초기화
            }
        }
        
        //시간이 가장 적은 상태부터 꺼내기 위한 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        //초기시간 0, 초기점프력1
        dist[startR][startC][1] = 0;
        pq.offer(new int[] {0, startR, startC, 1}); //현재까지 걸린 시간, 행, 열, 점프력

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0]; //현재까지 걸린 시간
            int r = curr[1]; //현재 행
            int c = curr[2]; //현재 열
            int power = curr[3]; //현재 점프력

            //이미 더 빠르게 도착한 경우가 있다면
            if(time > dist[r][c][power]) continue;
            //도착했다면
            if(r == endR && c == endC) {
                System.out.println(time);
                return;
            }

            //1. 점프하기
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d] * power;
                int nc = c + dc[d] * power;

                if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if(arr[nr][nc] != '.') continue;
                boolean possible = true;

                //점프 중 천적이 사는 돌이 있는지 확인
                for(int k = 1; k <= power; k++) {
                    int checkR = r + dr[d] * k;
                    int checkC = c + dc[d] * k;

                    if(arr[checkR][checkC] == '#') {
                        possible = false;
                        break;
                    }
                }

                if(!possible) continue;

                // 점프에 걸리는 시간을 더하기
                int jumpCost = 1;
                int nextTime = time + jumpCost;

                //기존에 기록된 시간보다 빠르다면 갱신
                if(nextTime < dist[nr][nc][power]) {
                    dist[nr][nc][power] = nextTime;

                    //점프 후 위치와 점프력 저장
                    pq.offer(new int[] {nextTime, nr, nc, power});
                }
            }

            //2. 점프력 증가하기
            if(power < 5) {
                int nextPower = power + 1;
                int nextTime = time + nextPower * nextPower;

                if(nextTime < dist[r][c][nextPower]) {
                    dist[r][c][nextPower] = nextTime;
                    pq.offer(new int[] {nextTime, r, c, nextPower});
                }
            }

            //3. 점프력 감소하기
            for(int nextPower = 1; nextPower < power; nextPower++) {
                int nextTime = time + 1;

                if(nextTime < dist[r][c][nextPower]) {
                    dist[r][c][nextPower] = nextTime;
                    pq.offer(new int[] {nextTime, r, c, nextPower});
                }
            }
        }

        //도착하지 못했다면
        System.out.println(-1);
    }
}