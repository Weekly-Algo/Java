class Solution {
    public int solution(int num) {
        return recur(num, 0); 
    }
    
    // 재귀함수 선언
    public int recur(long n, int cnt) {
        
        //종료조건
        if (n == 1) {
            return cnt;
        }

        if (cnt >= 500) {
            return -1;
        }
        
        // 재귀 호출
        if (n % 2 == 0) {
            return recur(n / 2, cnt + 1);
        } else {
            return recur(n * 3 + 1, cnt + 1);
        }
    }
}