class Solution { 
    public boolean solution(String s) { 
        int count = 0; 
 
        for (int i = 0; i < s.length(); i++) { 

            // 열린 괄호이면 개수 증가
            if (s.charAt(i) == '(') { 
                count++; 

            // 닫힌 괄호이면 개수 감소
            } else { 
                count--; 
            } 
 
            // 닫힌 괄호가 더 많으면 올바르지 않으므로 종료
            if (count < 0) { 
                return false; 
            } 
        } 
 
        // 열린 괄호가 모두 닫혔으면 true
        if (count == 0) { 
            return true; 
        } else { 
            return false; 
        } 
    } 
}