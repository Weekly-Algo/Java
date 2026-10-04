import java.util.*;

class Solution {
    public int solution(int n) {
        
        int rest = 0;
        int value = n;
        int cnt = 0;
        while(value>0){
            rest = value%2; 
            if(rest==1) cnt++;
            value = value/2;
        }

        int value2 = ++n;
        while(true){
            int cnt2 = 0;
            int tmp = value2;
            while(value2>0){
                rest = value2%2; 
                if(rest==1) cnt2++;
                value2 = value2/2;
            }
            value2 = tmp;
            if(cnt == cnt2){
                break;
            }
            value2++;
            
        }
        return value2;
           
    }
}