class Solution {
    public String solution(String s) {

        StringBuilder sb = new StringBuilder();

        boolean isFirst = true; //초기값은 true
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ' ') { //공백이라면
                sb.append(c); //추가
                isFirst = true; //상태변경
            } else {
                if(isFirst) { //첫글자라면
                    sb.append(Character.toUpperCase(c)); //대문자로 추가
                    isFirst = false; //상태변경
                } else { // 첫글자가 아니라면
                    sb.append(Character.toLowerCase(c)); //소문자로 추가
                }
            }
        }

        return sb.toString();
    }
}