import java.util.*;

class Solution {

    static String begin;
    static String target;
    static String[] words;
    static boolean[] visited;

    public int solution(String begin, String target, String[] words) {
        int answer = 0;

        Solution.begin = begin;
        Solution.target = target;
        Solution.words = words;
        visited = new boolean[words.length];

        answer = bfs();
        return answer;

    }

    static int bfs() {
        int step = 0;
        Queue<String> q = new ArrayDeque<>();
        q.offer(begin);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                if (curr.equals(target)) return step;

                for (int j = 0; j < words.length; j++) {
                    if (visited[j]) continue;              // 방문여부 체크!

                    String str = words[j];
                    int diff = 0;                       // 다른 글자 하나일 경우에만 큐에 넣는다!
                    for (int x = 0; x < str.length(); x++) {
                        if (curr.charAt(x) != str.charAt(x)) diff++;
                    }
                    if (diff == 1) {
                        visited[j] = true;     // 방문 표시
                        q.offer(str);
                    }
                }
            }
            step++;
        }
        return 0;
    }
}