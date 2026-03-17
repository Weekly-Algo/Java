import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S2_6603_로또 {
    static int N = 6; //선택 개수
    static int k; //전체 배열의 수
    static int[] num; //전체 배열
    static int[] sel; //선택 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            k = Integer.parseInt(st.nextToken());
            if(k == 0) break;
            num = new int[k];
            sel = new int[N];

            for(int i = 0; i < k; i++) { //선택 배열에 넣기
                num[i] = Integer.parseInt(st.nextToken());
            }

            combi(0, 0);
            System.out.println();
        }
    }

    public static void combi(int idx, int count) {
        //종료파트
        if(count == N) {
            for(int i = 0; i < N; i++) {
                System.out.print(sel[i] + " ");
            }
            System.out.println();
            return;
        }

        if(idx >= k) return; //선택하지 않고 배열의 끝에 왔다면

        //재귀파트
        sel[count] = num[idx]; //선택
        combi(idx + 1, count + 1);

        combi(idx + 1, count);//미선택
    }
}
