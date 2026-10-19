import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Q = Integer.parseInt(st.nextToken())-1; //그냥 초기작업 빼고 받기
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        //초기작업 1회 어차피 작업 번호는 몰라도 됨 1번이니까
        Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] jewelry = new int[N][2];
        for(int i = 0; i < N*2; i++) jewelry[i/2][i%2] = Integer.parseInt(st.nextToken());
        for(int t = 0; t < Q; t++){
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());

            if(T == 2){
                int w = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int[][] tmpJ = new int[jewelry.length+1][2];
                tmpJ = add(jewelry,w,v);
                jewelry = new int[tmpJ.length][2];
                jewelry = tmpJ;

            }else if (T == 3){
                int idx = Integer.parseInt(st.nextToken())-1; //인덱스화
                int r = sell(jewelry,idx);
                if(r == -1 ) sb.append(-1).append("\n");
                else sb.append(r).append("\n");

            }else if (T == 4){
                int W = Integer.parseInt(st.nextToken());
                sb.append(show(jewelry,W)).append("\n");

            }else{
                int D = Integer.parseInt(st.nextToken());
                sb.append(set(jewelry,D)).append("\n");
            }
        }

        System.out.print(sb);

    }
    public static int[][] add(int[][] jewelry,int w,int v){ //2번 더하기
        int[][] result = new int[jewelry.length+1][2];
        for(int i = 0 ; i < jewelry.length; i++){
            result[i][0] = jewelry[i][0];
            result[i][1] = jewelry[i][1];
        }
        result[jewelry.length][0] = w;
        result[jewelry.length][1] = v;
        return result;
    }

    public static int sell(int[][] jewelry, int idx){ //3번 팔기
        
        if(idx >= jewelry.length || idx < 0) return -1;
        else if(jewelry[idx][1] == -1) return -1;
        else {
            int result = jewelry[idx][1];
            jewelry[idx][1] = -1;
            jewelry[idx][0] = -1;
            return result;
        }
    }

    public static int show(int[][] jewelry, int W){ //4번 진열
          int[] DP = new int[W + 1];

    for (int i = 0; i < jewelry.length; i++) {
        if (jewelry[i][0] == -1) continue; // 이미 판매된 보석

        int weight = jewelry[i][0];
        int value = jewelry[i][1];

        if (weight > W) continue;

        for (int w = W; w >= weight; w--) {
            DP[w] = Math.max(
                DP[w],
                DP[w - weight] + value
            );
        }
    }
        return DP[W];
    }

    public static int set(int[][]jewelry, int D){ //5번 세트
        int result = 0;
        int[][] tmpJw = jewelry.clone();
        Arrays.sort(tmpJw,(a,b) -> Integer.compare(a[0],b[0]));
        int flag = 0;
        for(int i = 0 ; i < tmpJw.length; i++){
            if(tmpJw[i][0]==-1) continue;
            while(flag < tmpJw.length && tmpJw[flag][0] - tmpJw[i][0] <= D) flag ++;
            
            result += flag-i-1;
        }
        return result;
    }

}