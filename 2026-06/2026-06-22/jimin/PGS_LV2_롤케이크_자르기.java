import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;

        Map<Integer, Integer> right = new HashMap<>();
        Map<Integer, Integer> left = new HashMap<>();

        //오른쪽 맵에 다 채우기
        for(int t : topping) {
            right.put(t, right.getOrDefault(t, 0) + 1);
        }

        for(int i = 0; i < topping.length; i++) {
            int t = topping[i];

            //왼쪽에 추가하기
            left.put(t, left.getOrDefault(t, 0) + 1);

            //오른쪽에서 개수 - 1
            right.put(t, right.get(t) - 1);

            //right에서 개수가 0이되면 key 삭제하기
            if(right.get(t) == 0) right.remove(t);

            //left와 right 개수 비교
            if(left.size() == right.size()) answer += 1;
        }

        return answer;
    }
}