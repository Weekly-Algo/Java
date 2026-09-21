import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        Deque<Boolean> stack = new ArrayDeque<>();
        int oneCount = 0;
        while (n > 0) {
            boolean i = (0 == n % 2);
            n /= 2;
            if (!i) {
                stack.push(true);
                oneCount++;
            } else {
                stack.push(false);
            }
        }
        int tmp = stack.size();
        int[] ezinsu = new int[tmp];
        for (int i = 0; i < tmp; i++) 
            if (stack.pop()) ezinsu[i] = 1;

        int idx = -1;
        for (int i = tmp - 2; i >= 0; i--) {
            if (ezinsu[i] == 0 && ezinsu[i + 1] == 1) {
                idx = i;
                break;
            }
        }
        if (idx != -1) {
            int noc = -1;
            for (int i = idx + 1; i < tmp; i++) if (ezinsu[i] == 1) noc++;
            ezinsu[idx] = 1;
            for (int i = idx + 1; i < tmp; i++) ezinsu[i] = 0;
            for (int i = tmp - 1; i > idx; i--) {
                if(noc>0){
                ezinsu[i] = 1;
                noc--;
                }else{break;}
            }
        }else {
            int[] ezinsuUp = new int[tmp + 1];
            ezinsuUp[0] = 1;
            for (int i = 0; i < oneCount - 1; i++) ezinsuUp[tmp - i] = 1;
            ezinsu = ezinsuUp;
            tmp++;
        }
        for (int i = 0; i < tmp; i++) answer = answer * 2 + ezinsu[i];

        return answer;
    }
}