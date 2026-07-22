import java.util.*;

class Solution {
    static String[] words = {"A", "E", "I", "O", "U"};
    static List<String> wordList;
    static int N = 5;
    static StringBuilder sb;
    
    public int solution(String word) {
        sb = new StringBuilder();
        wordList = new ArrayList();
        int answer = 0;
        
        dfs();
        
        for(int i = 0; i < wordList.size(); i++) {
            if(word.equals(wordList.get(i))) {
                answer = i + 1;
                break;
            }
        }
        
        return answer;
    }
    
    public static void dfs() {
        if(sb.length() == 5) { //현재 문자열 길이가 5라면
            return;
        }
        
        for(int i = 0; i < 5; i++) { //모음 5개 반복하기
            sb.append(words[i]); //현재 문자열에 모음 하나 붙이기
            wordList.add(sb.toString()); //현재 문자열 저장
                
            dfs(); //더 깊게 탐색
            
            sb.deleteCharAt(sb.length() - 1); //방금 붙인 글자 제거
        }
    }
}