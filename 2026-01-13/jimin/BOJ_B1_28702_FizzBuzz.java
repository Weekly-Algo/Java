import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_B1_28702_FizzBuzz {
    public static void main(String[] args) throws IOException {
        String[] strArr = new  String[3];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //배열에 값 넣기
        for(int i = 0; i < strArr.length; i++){
            strArr[i] = br.readLine().trim();
        }

        int intNum = 0; //배열 중 숫자
        int idx = 0; //배열 중 숫자의 인덱스
        
        //문자열 중 숫자 구하기
        for(int i = 0; i < strArr.length; i++){
            try {
                int num =  Integer.parseInt(strArr[i]);
                idx = i; //인덱스 구하기
                intNum = num;
                break;
            } catch (NumberFormatException e) { //숫자가 아니면 계속
                continue;
            }
        }

        int fourthNum = intNum + (3 - idx); //네 번째 숫자 구하기

        //규칙에 따라 네 번째 숫자 출력하기
        if(fourthNum % 3 == 0 && fourthNum % 5 == 0) {
            System.out.println("FizzBuzz");
        } else if(fourthNum % 3 == 0) {
            System.out.println("Fizz");
        } else if(fourthNum % 5 == 0) {
            System.out.println("Buzz");
        } else {
            System.out.println(fourthNum);
        }
    }
}
