import java.util.*;

class Solution {
    public int solution(int num) {

        long n = num;
        int count = 0;

        while (true) {

            if (n == 1) break;
            // 500까지 돌아도 안 되면 -1 반환
            if (count == 500) return -1;

            if (n % 2 == 0) {
                n /= 2; // 짝
            } else {
                n = n * 3 + 1; // 홀
            }

            count++;
        }

        return count;
    }
}