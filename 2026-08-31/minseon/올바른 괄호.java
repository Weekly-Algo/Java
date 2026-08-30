class Solution {
    boolean solution(String s) {

        // 괄호 몇개 남았는지
        int count = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            // 이렇게 반복하면서 count가 0이 되어야 함
            if (c == '(') {
                count++;
            } else {
                count--;
            }

            // 닫는 괄호가 먼저 나온 경우
            // 그러면 괄호를 끝낼수가 없자나 그러니까 바로 false
            if (count < 0) {
                return false;
            }
        }

        // 모든 괄호의 짝이 맞으면 0
        return count == 0;
    }
}