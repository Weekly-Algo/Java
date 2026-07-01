import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        
        int answer = 0;

        //맵에 넣기
        Map<String, Integer> wantMap = new HashMap<>();
        Map<String, Integer> saleMap = new HashMap<>(); //할인상품 저장

        for(int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        for(int i = 0; i < 10; i++) {
            String item = discount[i];
            saleMap.put(item, saleMap.getOrDefault(item, 0) + 1);
        }

        //10일 검사
        if(wantMap.equals(saleMap)) answer++;

        //슬라이딩 윈도우
        for(int i = 0; i < discount.length - 10; i++) {
            
            //빠지는 상품
            String removeItem = discount[i];
            saleMap.put(removeItem, saleMap.get(removeItem) - 1);

            //개수가 0이면 Map에서 제거
            if(saleMap.get(removeItem) == 0) {
                saleMap.remove(removeItem);
            }

            //새로 들어오는 상품
            String addItem = discount[i + 10];
            saleMap.put(addItem, saleMap.getOrDefault(addItem, 0) + 1);

            //확인
            if(wantMap.equals(saleMap)) answer++;
        }

        return answer;
    }
}