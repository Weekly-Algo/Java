import java.util.*;

class Solution {
    public String solution(String number, int k) {
        //큰 숫자가 앞에 위치해야 됨
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < number.length(); i++) {
            char curr = number.charAt(i);

            //더 추가 가능하고
            //뒤에 있는 숫자가 현재 숫자보다 작다면
            while (k > 0 && sb.length() > 0 && sb.charAt(sb.length() - 1) < curr) {
                sb.deleteCharAt(sb.length() - 1); // 더 작은 숫자 지우기
                k--; //제거할 때마다 k 감소
            }

            sb.append(curr); //현재 숫자 sb에 넣기
        }

        //뒤로 갈수록 숫자가 작아져서 지울 수 없는 경우
        //뒤의 숫자가 k만큼 지워줌
        if (k > 0) {
            sb.delete(sb.length() - k, sb.length());
        }

        return sb.toString();
    }
}