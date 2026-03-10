import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_2961_도영이가_만든_맛있는_음식 {
    static int N; //재료의 수
    static boolean[] sel; //선택 배열
    static int[] sour; //신맛 배열
    static int[] bitter; //쓴맛 배열
    static int minSum = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        sel = new boolean[N];
        sour = new int[N];
        bitter = new int[N];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sourNum = Integer.parseInt(st.nextToken());
            int bitterNum = Integer.parseInt(st.nextToken());
            sour[i] = sourNum;
            bitter[i] = bitterNum;
        }

        subset(0);
        System.out.println(minSum);
    }

    public static void subset(int idx) {
        //종료 파트
        if(idx == N){ //모든 재료를 판단했다면
            int sourSum = 1;
            int bitterSum = 0;
            for(int i = 0; i < N; i++) {
                if(sel[i]) { //배열의 값이 true라면
                    sourSum *= sour[i];
                    bitterSum += bitter[i];
                }
            }

            if(sourSum > 1) { //sourSum이 초기값이 아니라면
                int diff = Math.abs(sourSum - bitterSum);
                if (diff < minSum) {
                    minSum = diff;
                }
                return;
            } else {
                return;
            }
        }

        //재귀 파트
        sel[idx] = true; //이번 재료 선택
        subset(idx + 1); //다음 재료로

        sel[idx] = false; //이번 재료 미선택
        subset(idx + 1); //다음 재료로
    }
}
