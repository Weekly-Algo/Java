import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {0,1000001};
        int left = 0;
        int res = 0;
        int right = 0;
        while(left < sequence.length){
            while(res < k && right < sequence.length) res += sequence[right++];
            
            if(res == k){
                int tmpSub = right-left-1;
            
                if(answer[1]-answer[0] > tmpSub){
                    answer = new int[]{left, right-1};
                }
            }
            
            res -= sequence[left];   
            left++;
            
        }                                                     
                                                      
                                                      
        return answer;
    }
}