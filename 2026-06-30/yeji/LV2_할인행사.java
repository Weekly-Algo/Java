import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        
        //목표 HashMap 생성
        HashMap<String, Integer> targetMap = new HashMap<>();
        
        for(int i = 0; i < want.length; i++){
            targetMap.put(want[i], number[i]);
        }
        
        int answer = 0;        
        
        //한 칸씩 이동하면서 10일 할인 상품 HashMap에 저장
        for(int i = 0; i <= discount.length - 10; i++){
            
            HashMap<String, Integer> discountMap = new HashMap<>();
            
            for(int j = i; j < i + 10; j++){
                if (discountMap.containsKey(discount[j])) {
                    discountMap.put(discount[j], discountMap.get(discount[j]) + 1);
                } else {
                    discountMap.put(discount[j], 1);
                }
            }
            
            // 목표와 같으면 답 + 1
            if (targetMap.equals(discountMap)) {
                answer++;
            }    
        }
        
        return answer;
    }
}