import java.util.*;
// 이진변환 횟수 & 변환 과정에서 제거된 0의 개수
class Solution {
    public int[] solution(String s) {

        int changeCnt = 0;
        int removeZeroCnt = 0;

        // 우선 0을 제거
        // 길이 측정
        // 이를 이진수로 변환

        while(!s.equals("1")){
            int number1 = 0;

            for(char c : s.toCharArray()){
                if(c == '0') removeZeroCnt++;
                else number1++;
            }

            s = Integer.toBinaryString(number1);
            changeCnt++;
        }
        return new int[]{changeCnt, removeZeroCnt};
    }
}