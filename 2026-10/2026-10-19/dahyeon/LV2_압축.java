import java.util.*;
// 사전에 없다면 계속 한글자씩 늘리기
// 가장 긴 문자열을 출력
class Solution {
    public int[] solution(String msg) {

        Map<String, Integer> dict = new HashMap<>();
        List<Integer> ans = new ArrayList<>();

        int i = 0;
        int nxtNum = 27;

        for(char c = 'A'; c <= 'Z'; c++){
            dict.put(String.valueOf(c), c - 'A' + 1);
        }

        while(i < msg.length()){
            // 가장 긴 w 찾고 출력
            // 다음 글자가 있으면 새 단어 등록

            String w = String.valueOf(msg.charAt(i));
            // 기준 점에서 한칸씩 늘어날 인덱스
            int j = i + 1;

            while(j < msg.length()){

                String isContain = w + msg.charAt(j);
                // 더 붙인 단어가 사전에 없으면 멈추고, 사전 등록하기
                if(!dict.containsKey(isContain)){
                    break;

                }
                // 사전에 존재하면 w를 계속 늘려서 확인하기!
                w = isContain;
                j++;

            }

            // 가장 긴 w의 번호 출력
            ans.add(dict.get(w));
            // 다음 글자가 있다면, w + 다음글자 등록
            if(j < msg.length()){
                dict.put(w + msg.charAt(j), nxtNum++);
            }

            i = j;

        }

        int[] answer = new int[ans.size()];
        for(int k = 0; k < ans.size(); k++){
            answer[k] = ans.get(k);
        }
        return answer;
    }
}