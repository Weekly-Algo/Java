import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_S2_24479_알고리즘_수업_깊이_우선_탐색_1 {

    static int N; //정점의 수
    static int M; //간선의 수
    static List<Integer>[] adjust; //인접리스트
    static boolean[] visited; //방문배열
    static int[] order; //방문 순서 배열
    static int count; //방문 순서
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //정점의 수
        M =  Integer.parseInt(st.nextToken()); //간선의 수
        int  R = Integer.parseInt(st.nextToken()); //시작 정점

        adjust = new ArrayList[N + 1]; //정점의 수+1만큼 생성
        for(int i = 1; i < N + 1; i++) {
            adjust[i] = new ArrayList<>(); //각 리스트마다 배열 생성
        }

        visited = new boolean[N + 1]; //방문 배열 생성
        order = new int[N + 1]; //방문 순서 배열 생성

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine()); //간선 수만큼 반복
            int A = Integer.parseInt(st.nextToken()); //해당 정점
            int B = Integer.parseInt(st.nextToken()); //연결 정점
            adjust[A].add(B);
            adjust[B].add(A); //양방향 연결
        }

        for(int i = 1; i < N + 1; i++) { //정점 수 만큼 반복
            Collections.sort(adjust[i]); //연결 정점을 오름차순으로 정렬
        }

        dfs(R);
        for(int i = 1; i < N + 1; i++) {
            System.out.println(order[i]);
        }

    }

    public static void dfs(int v){
        order[v] = ++count;
        visited[v] = true;//방문 처리

        for(int i : adjust[v]){ //정점의 연결 정점 수만큼 반복
            if(!visited[i]){ //그 연결 정점을 방문하지 않았다면
                dfs(i); //방문
            }
        }
    }

}
