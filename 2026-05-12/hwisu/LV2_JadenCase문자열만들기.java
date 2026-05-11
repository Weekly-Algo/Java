class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        
        boolean isFirst = true;
        
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            
            if (ch == ' ') {
                sb.append(ch);
                isFirst = true;
            }
            else {
                if (isFirst) {
                    sb.append(Character.toUpperCase(ch));
                    isFirst = false;
                }
                else {
                    sb.append(Character.toLowerCase(ch));
                }
            }     
            
        } // for
        
        
        return sb.toString();
    } // main
}