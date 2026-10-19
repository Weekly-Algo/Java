import java.util.*;

class Solution {
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        // 문자열 탐색
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            
            // 스택 top이랑 같으면 제거
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            }
            else {
                stack.push(c);
            }
        } // for
        
        // 다 제거되면 1, 남으면 0
        if (stack.isEmpty()) {
            return 1;
        }
        return 0;
        
    } // solution
} // Solution