import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_B1_14626_ISBN {
    public static void main(String[] args) throws IOException {
        char[] cArr;
        int[] intArr = new int[13];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cArr = br.readLine().trim().toCharArray();

        //* 자리 인덱스 구하기
        int idx = 0;
        for(int i = 0; i < cArr.length; i++){
            if(cArr[i] == '*'){
                idx = i;
            }
        }

        //조건에 따른 합 구하기
        int sum = 0;
        for(int i = 0; i < cArr.length; i++){
            //*라면 continue
            if(i == idx) {
                continue;
            }
            //숫자라면
            if(i % 2 == 1) {
                sum += 3 * (cArr[i] - '0');
            } else {
                sum += cArr[i] - '0';
            }
        }

        //훼손된 숫자 구하기
        int weight = (idx % 2 == 1) ? 3 : 1; //홀수면 3 곱하기

        for(int i = 0; i < 10; i++) {
            if((sum + i * weight) % 10 == 0) {
                System.out.println(i);
                break;
            }
        }
    }
}
