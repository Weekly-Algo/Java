import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        int nCount = countBit(n);
        
        for (int i=1; true; i++) {
            int count = countBit(n+i);
            if (count == nCount) {
                answer = n+i;
                break;
            }
        } // for
        
        return answer;
        
    } //solution
    
    public int countBit(int num) {
        int nCount = 0;
        while (num>0) {
            if (num % 2 == 1) {
                nCount ++;
            }
            
            num = num/2;
        } // while
        
        return nCount;
    } // countBit
}