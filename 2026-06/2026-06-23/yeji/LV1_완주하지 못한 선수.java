import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        
        HashMap<String, Integer> map = new HashMap<>();
        
        // 참가자 저장
        for (int i = 0; i < participant.length; i++) {
            
            // 이미 있으면 value + 1
            if (map.containsKey(participant[i])) {
                map.put(participant[i], map.get(participant[i]) + 1);
            }
            // 없으면 생성
            else {
                map.put(participant[i], 1);
            }
        }
        
        // 완주한 선수 제거
        for (int i = 0; i < completion.length; i++) {
            
            map.put(completion[i], map.get(completion[i]) - 1);
            
            if (map.get(completion[i]) == 0) {
                map.remove(completion[i]);
            }
        }
        
        // 완주하지 못한 선수 찾기
        for (int i = 0; i < participant.length; i++) {
            
            if (map.containsKey(participant[i])) {
                return participant[i];
            }
        }
        
        return "";
    }
}