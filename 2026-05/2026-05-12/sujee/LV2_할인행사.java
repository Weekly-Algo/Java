import java.util.*;
class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int len = want.length;
        int[] tmp = new int[len];
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < len; j++) {
                if(discount[i].equals(want[j])) {
                    tmp[j]++;
                    break;
                }
            }
        }
        
        if(Arrays.equals(tmp, number)) answer++;
        
         for(int i = 10; i < discount.length; i++) {
            String out = discount[i - 10];
            String in = discount[i];

            for(int j = 0; j < len; j++) {
                if(out.equals(want[j])) tmp[j]--;
                if(in.equals(want[j])) tmp[j]++;
            }

            if(Arrays.equals(tmp, number)) answer++;
        }
        
        return answer;
    }
}