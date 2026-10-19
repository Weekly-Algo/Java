import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<String> list = new ArrayList<>();
        // 1. A~Z를 담는 리스트
        for (char c = 'A'; c <= 'Z'; c++) {
            list.add(String.valueOf(c));
        }
        
        List<Integer> result = new ArrayList<>();
        
        int i = 0;
        while (i < msg.length()) {
            String w = "";
            int j = i;
            
            // 2. 사전에 w + 다음글자 가 포함되어 있는지 확인
            while (j < msg.length() && list.contains(w + msg.charAt(j))){
                w += msg.charAt(j);
                j++;
            }
            
            // 3. w가 사전의 몇 번째에 있는지 찾아 결과에 추가
            result.add(list.indexOf(w) + 1);
            
            // 4. 다음 글자가 남아있다면 w + c를 사전에 추가
            if (j < msg.length()) {
                char c = msg.charAt(j);
                list.add(w + c);
            }
            
            // 5. 인덱스 이동
            i += w.length();
        }
        
        int[] answer = new int[result.size()];
        for (int k = 0; k < result.size(); k++) {
            answer[k] = result.get(k);
        }
        return answer;
    }
}