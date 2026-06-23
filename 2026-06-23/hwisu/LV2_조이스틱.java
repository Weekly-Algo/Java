class Solution {
    public int solution(String name) {
        int answer = 0;
        int length = name.length();

        // 기본적으로는 오른쪽으로 쭉 이동
        int minMove = length - 1;

        for (int i=0; i<length; i++) {
            char current = name.charAt(i);

            // 현재 위치의 알파벳을 A에서 원하는 문자로 바꾸는 최소 횟수
            answer += getAlphabetMove(current);

            // 현재 위치 다음부터 연속된 A 구간탐색
            int next = i+1;

            while (next < length && name.charAt(next) == 'A') {
                next++;
            }

            // 방법 1
            // 오른쪽으로 i까지 갔다가 다시 왼쪽으로 돌아가서
            // 뒤쪽의 남은 문자들을 처리
            int moveRightThenLeft = i * 2 + length - next;

            // 방법 2
            // 처음부터 왼쪽으로 돌아 뒤쪽을 먼저 처리한 다음
            // 다시 오른쪽으로 와서 앞쪽을 처리
            int moveLeftThenRight = (length - next) * 2 + i;

            // 가능한 커서 이동 중 가장 작은 값을 선택
            minMove = Math.min(minMove, moveRightThenLeft);
            minMove = Math.min(minMove, moveLeftThenRight);
        }

        return answer + minMove;
    }

    private int getAlphabetMove(char alphabet) {
        int up = alphabet - 'A';
        int down = 'Z' - alphabet + 1;

        return Math.min(up, down);
    }
}