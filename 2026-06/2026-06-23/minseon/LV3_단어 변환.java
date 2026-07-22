import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean hasTarget = false;

        // words에 target 단어가 있으면 true 로 바꾸고 break
        for (String word : words) {
            if (word.equals(target)) {
                hasTarget = true;
                break;
            }
        }

        // 다 돌고도 target 단어가 없으면 0 반환
        if (!hasTarget) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];

        // 시작 단어 집어넣기
        queue.offer(begin);

        int ans = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // 같은 변환 횟수에 있는 단어들을 한 번에 처리
            for (int s = 0; s < size; s++) {
                String curr = queue.poll();

                if (curr.equals(target)) {
                    return ans;
                }

                // 이미 방문한 문자인지 확인
                // 지금 문자랑 words 에 있는 문자랑 바꿀 수 있는지 확인
                for (int i = 0; i < words.length; i++) {
                    if (!visited[i] && canChange(curr, words[i])) {
                        visited[i] = true;
                        queue.offer(words[i]);
                    }
                }
            }
            ans++;
        }
        return 0;
    }

    // 문자 바꿀 수 있는지 확인
    // 한 개의 알파벳만 바꿀 수 있음
    // diff = 다른 알파벳 개수
    // diff = 1 경우에만 true
    private boolean canChange(String a, String b) {
        int diff = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
            }
        }

        return diff == 1;
    }
}