import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        List<String> list = new ArrayList<>();
        
        // 시작단어도 0번 인덱스로 단어 리스트에 넣음
        list.add(begin);

        for (String word : words) {
            list.add(word);
        }

        boolean[] visited = new boolean[list.size()];

        // 큐에 현재 (단어의 인덱스, 변환 횟수) 배열 형태로 저장
        ArrayDeque<int[]> q = new ArrayDeque<>();

        // begin은 list의 0번에 있으니까 0번 인덱스에서 시작
        q.offer(new int[] {0, 0});
        visited[0] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();

            int wordIndex = now[0];
            int count = now[1];

            String word = list.get(wordIndex);

            // 단어가 target이랑 같아지면 변환 횟수를 반환
            if (word.equals(target)) {
                return count;
            }

            // begin을 제외한 단어들 중에서 다음으로 변환 가능한 단어 찾기
            for (int i=1; i<list.size(); i++) {
                if (visited[i]) {
                    continue;
                }

                // 바꿀수있는 단어 -> 큐에 삽입
                if (canChange(word, list.get(i))) {
                    visited[i] = true;
                    q.offer(new int[] {i, count + 1});
                }
            }
        }

        // target까지 변환이 안되면 0리턴
        return 0;
    }

    private boolean canChange(String a, String b) {
        int diff = 0;

        // 한 글자씩 비교해서 다른 글자 수를 카운트
        for (int i=0; i<a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
            }

            // 두 글자 이상 다르면 불가
            if (diff > 1) {
                return false;
            }
        }

        // 한글자만 다르면 ㄱㄴ
        return diff == 1;
    }
}