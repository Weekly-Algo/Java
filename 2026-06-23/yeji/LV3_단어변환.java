import java.util.*;

class Solution {

    static class Node {
        String word;
        int count;

        Node(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

    public int solution(String begin, String target, String[] words) {

        boolean[] visited = new boolean[words.length];

        Deque<Node> deque = new ArrayDeque<>();

        // 시작 단어 저장
        deque.offer(new Node(begin, 0));

        while (!deque.isEmpty()) {

            Node curr = deque.poll();

            // target 도달 시 변환 횟수 반환
            if (curr.word.equals(target)) {
                return curr.count;
            }

            // words 탐색
            for (int i = 0; i < words.length; i++) {

                // 이미 방문한 단어
                if (visited[i]) {
                    continue;
                }

                int diff = 0;

                // 다른 글자 수 계산
                for (int j = 0; j < curr.word.length(); j++) {

                    if (curr.word.charAt(j) != words[i].charAt(j)) {
                        diff++;
                    }
                }

                // 한 글자만 다르면 이동 가능
                if (diff == 1) {

                    visited[i] = true;

                    deque.offer(
                        new Node(words[i], curr.count + 1)
                    );
                }
            }
        }

        // target으로 변환할 수 없는 경우
        return 0;
    }
}