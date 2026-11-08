import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        Set<String> used = new HashSet<>();
        used.add(words[0]);
        
        // 두 번째 단어부터 확인
        for (int i=1; i<words.length; i++) {
            String prev = words[i-1];
            String cur = words[i];
            
            // 끝말이 안 이어지거나, 이미 나온 단어면 탈락
            if (prev.charAt(prev.length()-1) != cur.charAt(0) || used.contains(cur)) {
                int person = i % n + 1;
                int turn = i / n + 1;
                return new int[]{person, turn};
            }
            
            used.add(cur);
        } // for
        
        // 탈락자 없음
        return new int[]{0, 0};
        
    } // solution
} // Solution