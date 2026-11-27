import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0, n = s.length();

        for(int i = 0; i < n; i++) {
            Stack<Character> st = new Stack<>();
            int j;

            for(j = 0; j < n; j++) {
                char c = s.charAt((i + j) % n); // 회전
                if(c == '(' || c == '[' || c == '{') st.push(c);
                else if(!st.isEmpty() && "([{".indexOf(st.peek()) == ")]}".indexOf(c)) st.pop();
                else break;
            }
            if(j == n && st.isEmpty()) answer++;
        }

        return answer;
    }
}