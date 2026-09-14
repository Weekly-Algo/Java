import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[][] parts = new String[record.length][];
        
        // 배열 변환
        for (int i=0; i<record.length; i++) {
            parts[i] = record[i].split(" ");
        }
        
        // 해시맵으로 uid 관리
        HashMap<String, String> map = new HashMap<>();
        
        // 순회하면서, 닉변 최신화
        for (String[] part : parts) {
            if (!part[0].equals("Leave")) {
                map.put(part[1],part[2]);
            }
        }
        
        List<String> answerList = new ArrayList<>();
        
        for (String[] part : parts) {
            if (part[0].equals("Enter")) {
                answerList.add(map.get(part[1]) + "님이 들어왔습니다.");
            }
            else if (part[0].equals("Leave")) {
                answerList.add(map.get(part[1]) + "님이 나갔습니다.");
            }
        }
        
        // 반환타입인 배열로 변환
        String[] answer = answerList.toArray(new String[0]);
        
        return answer;
    }
}