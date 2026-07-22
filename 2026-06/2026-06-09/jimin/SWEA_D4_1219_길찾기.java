import java.util.*;
import java.io.FileInputStream;

class Solution {
    static int[] graph1;
    static int[] graph2;
    static int answer;
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		for(int t = 1; t <= 10; t++) { //테스트케이스만큼 반복
            answer = 0; //초기화
            int testCase = sc.nextInt(); //테스트케이스 번호
            int N = sc.nextInt(); //길의 총 개수 = 간선의 개수
            
            graph1 = new int[100];
            graph2 = new int[100];
            
			Arrays.fill(graph1, -1); //-1로 채우기
            Arrays.fill(graph2, -1);
            
            for(int i = 0; i < N; i++) { //간선의 개수만큼 반복
                int from = sc.nextInt(); //출발정점
                int to = sc.nextInt(); //도착정점
                
                if(graph1[from] == -1) { //graph1이 초기상태라면
					graph1[from] = to;
                } else { //아니라면 graph2에
                    graph2[from] = to;
                }
            }
            
            dfs(0);
            System.out.println("#" + testCase + " " + answer);
		}
	}
    
    public static void dfs(int v) {
        //기저조건
        if(v == 99) { //도착점이라면
            answer = 1;
            return;
        }
        
        //재귀파트
        if(graph1[v] != -1) { //연결된 정점이 있다면
         	dfs(graph1[v]);
        }
        
        if(graph2[v] != -1) {
         	dfs(graph2[v]);   
        }
    }
}