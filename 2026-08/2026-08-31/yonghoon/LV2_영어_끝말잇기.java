import java.util.*;

class LV2_영어_끝말잇기 {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2]; // [0]: 탈락한 사람 번호, [1]: 몇 번째 차례인지
        Set<String> set = new HashSet<>(); // 중복단어 체크를 위함

        String prevWord = words[0];
        set.add(prevWord);

        for(int i = 1; i < words.length; i++) {
            String nowWord = words[i];

            // 이전 끝 문자와 현재 시작 문자가 다르거나 해당 단어를 말한적이 있음
            char prevChar = prevWord.charAt(prevWord.length() - 1);
            char nowChar = nowWord.charAt(0);

            if((prevChar != nowChar) || set.contains(nowWord)) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            } else {
                // 제대로 했으면 set에 단어 넣고 prevWord바꾼 후 다음 단어로
                set.add(nowWord);
                prevWord = nowWord;
            }
        }
        return answer;
    }
}