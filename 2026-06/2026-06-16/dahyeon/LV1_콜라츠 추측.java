import java.util.*;
// 조건 cnt 500 이면 -1, 그냥 1일때는 0 반환
// 짝수는 %2 , 홀수는 곱하기 3+1
class Solution {
    public int solution(int num) {
        long n = num;
        int cnt = 0;

        // n 이 1일때와 횟수가 500번 반복되기 전까지 반복
        while(n!= 1 && cnt < 500){
            n = calc(n);
            cnt ++;

        }

        return n == 1 ? cnt : -1;


    }

    static long calc(long n){

        if (n % 2 == 0) {
            return n / 2;
        }

        return n * 3 + 1;
    }

}