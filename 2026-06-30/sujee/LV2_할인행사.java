import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int len = want.length;

        // 원하는 품목별로 10일간 할인된 개수를 셀 배열
        int[] tmp = new int[len];

        // 첫 10일치 윈도우 세팅!! -> discount 0~9일 확인
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < len; j++) {
                if(discount[i].equals(want[j])) {
                    tmp[j]++;   // 원하는 품목이면 개수 +1
                    break;      // 찾았으면 더 볼 필요 없음
                }
            }
        }

        // 원하는 수량과 딱 맞으면 카운트
        if(Arrays.equals(tmp, number)) answer++;

        // 하루씩 밀면서 확인 (슬라이딩 윈도우)
        for(int i = 10; i < discount.length; i++) {
            String out = discount[i - 10];  // 윈도우에서 빠지는 날
            String in = discount[i];        // 윈도우에 새로 들어오는 날

            for(int j = 0; j < len; j++) {
                if(out.equals(want[j])) tmp[j]--;   // 빠지는 품목 -1
                if(in.equals(want[j])) tmp[j]++;    // 들어오는 품목 +1
            }

            // 수량 맞으면 카운트
            if(Arrays.equals(tmp, number)) answer++;
        }

        return answer;
    }
}