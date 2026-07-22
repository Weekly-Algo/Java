import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        //맵에 넣기
        Map<String, Integer> map = new HashMap<>();

        for(String name : participant) {
            map.put(name, map.getOrDefault(name, 0) + 1);
        }

        //완주자 맵에서 제거하기
        for(String name : completion) {
            map.put(name, map.get(name) - 1);
        }

        //맵에서 남은사람 찾기
        for(String key : map.keySet()) {
            if(map.get(key) > 0) {
                answer = key;
            }
        }
        
        return answer;
    }
}