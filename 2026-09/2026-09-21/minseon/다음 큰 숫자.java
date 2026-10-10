import java.util.*;

class Solution {
    public int solution(int n) {
        // 1의 개수 저장 변수
        int targetCount = countOne(n);

        int next = n + 1; // n보다 큰 숫자 확인

        while (true) {
            // 큰 숫자 중에 input 값과 1의 개수가 같으면 바로 반환
            if (countOne(next) == targetCount) {
                return next;
            }
            next++; // 아니라면 next 증가시키면서 계속 반복
        }
    }

    // 입력값을 이진수로 변환 -> 1의 개수 세는 메서드
    private int countOne(int num) {
        String binary = Integer.toBinaryString(num);

        int count = 0;

        for(int i = 0; i < binary.length(); i++) {
            if(binary.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }
}