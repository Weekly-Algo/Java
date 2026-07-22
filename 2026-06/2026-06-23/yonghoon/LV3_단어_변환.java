import java.util.*;

class LV3_단어_변환 {
    static class Word {
        String word;
        int cnt;

        public Word(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
    public int solution(String begin, String target, String[] words) {
        // 방문배열
        boolean[] visited = new boolean[words.length];

        // bfsㄱㄱ
        Queue<Word> q = new ArrayDeque<>();
        q.offer(new Word(begin, 0));

        while(!q.isEmpty()) {
            Word cur = q.poll();
            String word = cur.word;
            int cnt = cur.cnt;

            // 단어가 target과 같다면 cnt를 return
            if(word.equals(target))
                return cnt;

            // 아니라면 다음 단어로 이동
            for(int i = 0; i < words.length; i++) {
                // 방문한곳이면 패스
                if(visited[i])
                    continue;

                // 다음 단어로 갈 수 없으면 패스
                String next = words[i];
                if(!checkDiffer(word, next))
                    continue;

                // 큐에 넣고 visited체크
                q.offer(new Word(next, cnt + 1));
                visited[i] = true;
            }
        }

        // 여기까지 왔다는 것은 변환이 불가하다는 것이므로 0을 return
        return 0;
    }

    // 다음 단어로 갈 수 있는지 체크
    static boolean checkDiffer(String before, String after) {
        int differ = 0;
        for(int i = 0; i < before.length(); i++) {
            if(before.charAt(i) != after.charAt(i))
                differ++;
        }

        // differ가 2이상이면 갈 수 없음(2개 이상의 알파벳이 다름)
        if(differ >= 2)
            return false;

        return true;
    }
}