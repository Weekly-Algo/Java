import java.util.*;

class LV2_다음_큰_숫자 {
    public int solution(int n) {
        int answer = 0;
        int one = countOne(n);

        // n보다 큰 수 중에서 1의 개수가 같은 것
        while(true) {
            int one2 = countOne(++n);

            if(one == one2) {
                answer = n;
                break;
            }
        }

        return answer;
    }

    // 2진수에서 1몇개인지 세기
    int countOne(int num) {
        int cnt = 0;

        while(num >= 1) {
            if(num % 2 == 1)
                cnt++;

            num /= 2;
        }

        return cnt;
    }
}