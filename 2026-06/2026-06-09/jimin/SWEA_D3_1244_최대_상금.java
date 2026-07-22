import java.util.*;
import java.io.FileInputStream;

class Solution {
    static int[] arr;
    static int count;
    static int answer;
    static Set<String>[] visited; //방문 체크로 중복 확인

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            answer = 0;

            String str = sc.next();
            count = sc.nextInt();
            
            visited = new HashSet[count + 1];
            
            for(int i = 0; i <= count; i++) {
             	visited[i] = new HashSet<>();   
            }

            char[] cArr = str.toCharArray();
            arr = new int[cArr.length];

            for (int i = 0; i < cArr.length; i++) {
                arr[i] = cArr[i] - '0';
            }

            dfs(0);

            System.out.println("#" + test_case + " " + answer);
        }
    }

    public static void dfs(int depth) {
        //배열을 문자열 형태로 만들기
        StringBuilder sb = new StringBuilder();
        for(int i = 0;  i < arr.length; i++) {
            sb.append(arr[i]);
        }
        String now = sb.toString();
        
        if(visited[depth].contains(now)) { //문자열이 중복된다면
         	return; //바로 빠져나가기
        }
        
        visited[depth].add(now); //아니면 방문배열에 넣기
        
        // 기저조건
        if (depth == count) {
			int num = Integer.parseInt(now);
            answer = Math.max(answer, num);
            return;
        }

        // 재귀조건
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                swap(i, j);
                dfs(depth + 1);
                swap(i, j); // 원상복구
            }
        }
    }

    // 교환
    public static void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}