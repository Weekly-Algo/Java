import java.util.ArrayDeque;
import java.util.Queue;

class Word {
    String word;
    int count; //변환 횟수

    public Word(String word, int count) {
        this.word = word;
        this.count = count;
    }
}

class Solution {
    static String[] wordsArr;
    static String targetStr;
    static boolean[] visited; //방문배열
    public int solution(String begin, String target, String[] words) {
        wordsArr = words;
        targetStr = target;
        visited = new boolean[wordsArr.length];

        int answer = bfs(begin);

        return answer;
    }

    public static int bfs(String begin) {
        Queue<Word> queue = new ArrayDeque<>();
        queue.offer(new Word(begin, 0)); //시작점 넣기
        
        while(!queue.isEmpty()) {
            Word curr = queue.poll(); //큐에서 하나 꺼내기
            if(curr.word.equals(targetStr)) return curr.count;

            for(int i = 0; i < wordsArr.length; i++) {
                int diffCount = 0; //다른 글자 개수
                if(visited[i]) continue; //방문했다면
                for(int j = 0; j < curr.word.length(); j++) {
                    if(curr.word.charAt(j) != wordsArr[i].charAt(j)) diffCount++; //글자가 다르면
                }
                if (diffCount == 1) { //한글자만 다르다면
                    queue.offer(new Word(wordsArr[i], curr.count + 1)); //큐에 넣기
                    visited[i] = true; //방문처리
                }
            }
        }

        return 0; //변환 불가능일 경우 0
    }
}