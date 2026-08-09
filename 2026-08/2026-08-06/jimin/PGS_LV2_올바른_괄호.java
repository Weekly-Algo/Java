import java.util.Stack;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(char c : arr) {
            if(c == '(') {
                stack.add(c);
            } else if(!stack.isEmpty() && c == ')') {
                stack.pop();
            } else {
                answer = false;
            }
        }

        if(!stack.isEmpty()) answer = false;

        return answer;
    }
}