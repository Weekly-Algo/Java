import java.util.*;

class LV2_n진수_게임 {
    public String solution(int n, int t, int m, int p) {
        // n: 진법, t: 미리 구할 숫자의 갯수
        // m: 게임 인원, p: 순서
        StringBuilder answer = new StringBuilder();

        // t * m개의 숫자 n진수로 변환
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t * m; i++) {
            // n 진수로 변환
            // 10이상부터는 대문자로 변환
            String s = Integer.toString(i, n).toUpperCase();
            sb.append(s);
        }

        // 말해야하는 숫자 출력
        for(int i = 0; i < t; i++) {
            int order = (p - 1) + m * i;
            String num = sb.toString().charAt(order) + "";

            answer.append(num);
        }

        return answer.toString();
    }
}