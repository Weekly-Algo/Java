import java.util.*;

class Solution {
    public int[] solution(String msg) {
        List<Integer> list = new ArrayList<>();
        Map<String, Integer> dict = new HashMap<>();
        
        // 사전 초기화
        for (char c='A'; c<='Z'; c++) {
            dict.put(String.valueOf(c), c-'A' + 1);
        }
        
        // 사전 탐색
        for (int i=0; i<msg.length(); i++) {
            int count = 1;
            while (i+count<msg.length()) {
                if (dict.containsKey(msg.substring(i,i+count+1))) {
                    count++;
                }
                else {
                    dict.put(msg.substring(i,i+count+1), dict.size()+1);
                    break;
                }
            } // while
            list.add(dict.get(msg.substring(i,i+count)));
            i += count-1;
            
        } // for
        
        //결과 배열로
        int[] ans = new int[list.size()];
        for (int i=0; i<list.size(); i++) {
            ans[i] = list.get(i);
        }
        
        return ans;
        
    } // solution
} // Solution