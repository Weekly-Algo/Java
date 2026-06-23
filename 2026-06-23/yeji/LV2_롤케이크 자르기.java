import java.util.*;

class Solution {
    public int solution(int[] topping) {
        
        HashSet<Integer> left = new HashSet<>();
        HashMap<Integer, Integer> right = new HashMap<>();
        
        //topping을 hashmap(오른쪽)에 전부 저장
        for(int i = 0; i < topping.length ; i++){
            
            //key가 있으면 value + 1
            if (right.containsKey(topping[i])) {
                right.put(topping[i], right.get(topping[i]) + 1);
            }
            //key가 없으면 key와 value 모두 생성
            else {
                right.put(topping[i], 1);
            }
        }
        
        int answer = 0;
        
        //토핑 왼쪽으로 옮기기
        for(int i = 0; i < topping.length - 1 ; i++){
            
            int num = topping[i];
            
            left.add(num);
            right.put(num, right.get(num) -1);
            
            if(right.get(num) == 0){
                right.remove(num);
            }
            //토핑 종류 비교
            if(left.size() == right.size()){
                answer++;
            }
            
            
        }
        return answer;
        
        
        
        
    }
}