class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();

        boolean isFirst = true;

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);

            // 공백이면 다음 문자를 첫 글자로 처리
            if (curr == ' ') {
                answer.append(curr);
                isFirst = true;
            }
            // 첫 글자는 대문자로 변환
            else if (isFirst) {
                answer.append(Character.toUpperCase(curr));
                isFirst = false;
            }
            // 나머지는 소문자로 변환
            else {
                answer.append(Character.toLowerCase(curr));
            }
        }

        return answer.toString();
    }
}