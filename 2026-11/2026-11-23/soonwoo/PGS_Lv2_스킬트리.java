import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        // skill에 없다면 순서 상관 없음
        
        int cnt = 0;  // 가능한 스킬트리 개수
        boolean isAble;  // 현재 스킬 트리 가능 여부
        
        for (String s : skill_trees) {
            int idx = 0;  // 현재 배워야 할 스킬의 인덱스, 0으로 초기화
            isAble = true;
            
            for (char c : s.toCharArray()) {
                int currIdx = skill.indexOf(c);
                
                // 없는 스킬이면 skip
                if (currIdx == -1) continue;
                
                // 현재 배워야 할 스킬이 아닌 경우
                if (currIdx != idx) {
                    isAble = false;
                    break;
                }
                
                idx++;
            }
            
            if (isAble) cnt++;
        }
        
        return cnt;
    }
}