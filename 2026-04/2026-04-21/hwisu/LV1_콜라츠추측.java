class Solution {
    public int solution(int num) {
        long n = num;
        int count = 0;
        
        if (n == 1) return count;
        
        while (n != 1) {
            if (count == 500) {
                count = -1;
                break;
            }
            
            if (n % 2 == 0) {
                n /= 2;
            }
            else {
                n = (n*3) + 1;
            }
            
            count++;
        } // while
        
        return count;
    } // main
}