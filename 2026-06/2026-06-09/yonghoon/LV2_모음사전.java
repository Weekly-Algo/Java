import java.util.*;

class LV2_모음사전 {
    static String[] words = {"A", "E", "I", "O", "U"};
    static List<String> list = new ArrayList<>();
    public int solution(String word) {
        int answer = 0;

        // 함수 호출
        makeWord("", 0);

        // 몇 번째인지 찾기
        for(int i = 0; i < list.size(); i++) {
            // 리스트 내의 단어가 현재 단어랑 일치하면 인덱스 반환
            if(list.get(i).equals(word)) {
                answer = i;
                break;
            }
        }
        return answer;
    }

    // 모든 단어 먼저 리스트에 넣어두기
    static void makeWord(String s, int depth) {
        list.add(s);
        if(depth == 5) return;

        for(int i = 0; i < words.length; i++) {
            makeWord(s + words[i], depth + 1);
        }
    }
}