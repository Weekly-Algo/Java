import java.util.*;
// 고정된 배열 -> 슬라이딩 윈도우
// 이중배열? 타입이 다르네,, -> map 근데 순서 상관없으니까 hash!!


class Solution {
    public int solution(String[] want, int[] number, String[] discount) {

        int day = 0;


        Map<String, Integer> bucket = new HashMap<>();

        for(int i = 0; i < want.length; i++){
            bucket.put(want[i], number[i]);
        }

        Map<String, Integer> window = new HashMap<>();
        for(int i = 0; i < 10; i++){
            String item = discount[i];

            // 해당한다면, 기존에 있던것과 더해주고
            if(window.containsKey(item)){
                window.put(item, window.get(item) + 1);
            }
            // 없다면, 1
            else {
                window.put(item, 1);
            }
        }

        if(bucket.equals(window)) day++;

        for(int r = 10; r < discount.length; r++){
            String addItem = discount[r];

            if(window.containsKey(addItem)){
                window.put(addItem, window.get(addItem)+1);
            }
            else{
                window.put(addItem, 1);
            }

            String removeItem = discount[r - 10];
            window.put(removeItem, window.get(removeItem)-1);

            if(window.get(removeItem) == 0) window.remove(removeItem);

            if(bucket.equals(window)) day++;

        }

        return day;
    }
}