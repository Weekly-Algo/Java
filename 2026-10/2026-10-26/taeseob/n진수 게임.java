import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();

        int now = 0;  // 지금까지 말한 문자 수
        int nums = 0; // 현재 변환할 10진수 숫자

        while(sb.length() < t) {

            // 현재 숫자를 n진수 문자열로 변환
            String str = Integer.toString(nums, n).toUpperCase();

            // 한 글자씩 근데 sb.length 아래에서도 안하면 now ++ 돼서 한 번 더 할 때가 있음 2번 할 때
            for(int i = 0; i < str.length() && sb.length() < t; i++) {
                now++;

                //튜브의 차례인지 확인
                if((now - 1) % m + 1 == p) {
                    sb.append(str.charAt(i));

                }
            }

            nums++; //다음 숫자로 이동
        }

        return sb.toString();
    }
}