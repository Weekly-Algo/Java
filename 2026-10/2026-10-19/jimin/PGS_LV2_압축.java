import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public int[] solution(String msg) {
        HashMap<String, Integer> dict = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 0; i < 26; i++) {
            char c = (char)('A' + i);

            String str = Character.toString(c);
            dict.put(str, i + 1);
        }

        int idx = 0; //현재 읽고 있는 문자열의 위치
        int nextNum = 27; //다음에 등록할 사전 번호

        while(idx < msg.length()) {
            String w = Character.toString(msg.charAt(idx));
            
            int nextIdx = idx + 1; //다음 인덱스

            //다음 문자를 붙여도 사전에 존재한다면 계속 늘리기
            while(nextIdx < msg.length()) {
                String next = w + msg.charAt(nextIdx);
                
                if(!dict.containsKey(next)) { //붙인 문자가 없다면
                    break;
                }

                w = next;
                nextIdx++;
            }

            //현재 문자열의 번호 저장
            result.add(dict.get(w));

            //새로운 문자열 등록
            if(nextIdx < msg.length()) {
                String newWord = w + msg.charAt(nextIdx);
                dict.put(newWord, nextNum++);
            }

            //압축한 문자열 길이만큼 이동
            idx += w.length();
        }

        int[] answer = new int[result.size()];
        
        for(int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}