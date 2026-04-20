import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_S5_1439_뒤집기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] cArr = br.readLine().toCharArray();
        int[] arr = new int[cArr.length];

        for(int i = 0; i < cArr.length; i++) {
            arr[i] = cArr[i] - '0';
        }

        int zeroGroup = 0;
        int oneGroup = 0;

        if(arr[0] == 0) { //첫 번째 숫자 세기
            zeroGroup++;
        } else {
            oneGroup++;
        }

        //연속 개수 세기
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] != arr[i-1]) { //현재 값이 이전 값이랑 다르다면
                if(arr[i] == 0) { //현재 값이 0이라면
                    zeroGroup++;
                } else {
                    oneGroup++;
                }
            }
        }

        System.out.println(Math.min(zeroGroup, oneGroup)); // 둘 중 최소 구간 개수 출력
    }
}
