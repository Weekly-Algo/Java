import java.util.*;

// 어떻게 해야 최소로 하지..!!
// 반올림 기준으로..?
class Solution {
    public int solution(int storey) {

        int answer = 0;

        while (storey > 0) {
            // 일의 자리
            int num1 = storey % 10;
            // 십의 자리
            int num10 = (storey / 10) % 10;

            // 일의 자리 수가 5보다 크거나 5이면서 십의 자리 수가 5보다 클때
            if (num1 > 5 || num1 == 5 && num10 >= 5) {
                // 올려주고!
                answer += (10 - num1);
                // 앞자리 수 올라갔으니까 1더 해주고
                storey = (storey / 10) + 1;
            }

            // 그 외, 일의 자리 더해주고 그 자리 없애기
            else {
                answer += num1;
                storey /= 10;
            }

        }
        return answer;
    }
}