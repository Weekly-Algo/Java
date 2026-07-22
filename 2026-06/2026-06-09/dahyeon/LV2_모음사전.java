import java.util.*;
// 생각과정
// 사전이라길래 사전순으로 생각했더니 예시가 이해되지 않았음
// 예시를 이해해보니 깊게 한번 찍고 나오는 구조
// 그것은 재귀

class Solution {
    String[] alphas = {"A", "E", "I", "O", "U"};
    int answer = 0;
    int order = 0;
    String target;

    public int solution(String word) {
        target = word; // 내가 찾을 단어

        // 차례대로 탐색 시작!
        for (String alpha : alphas) {
            dictionary(alpha);
        }

        return answer;
    }

    void dictionary(String curr) {
        // 5개까지 붙이기 + 답 찾으면 재귀 종료
        if (curr.length() > 5 || answer != 0) {
            return;
        }

        // 차례 증가
        order++;

        if (curr.equals(target)) {
            answer = order;
            return;
        }

        for (String alpha : alphas) {
            dictionary(curr + alpha);
        }
    }
}