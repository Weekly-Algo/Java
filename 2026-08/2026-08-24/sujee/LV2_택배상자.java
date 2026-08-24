import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;

        // 보조 컨테이너
        Stack<Integer> stack = new Stack<>();

        int i = 1, idx = 0;
        while (idx < order.length) {
            if(i == order[idx]) {
                answer++;
                i++; idx++;
                continue;
            }
            else if(!stack.isEmpty() && stack.peek() == order[idx]){
                answer++;
                stack.pop();
                idx++;
                continue;
            }
            else if(i <= order.length){
                stack.push(i);
                i++;
            }
            else {
                break;
            }
        }

        return answer;
    }
}