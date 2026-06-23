import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;

        Map<Integer, Integer> right_map = new HashMap<>();
        Set<Integer> left_set = new HashSet<>();

        // 일단 다 오른쪽이라고 생각하고 맵에 정보 저장
        for(int x : topping) {
            if(right_map.containsKey(x))
                right_map.put(x, right_map.get(x)+1);
            else
                right_map.put(x, 1);
        }

        // 토핑 종류의 개수?
        int topping_type = right_map.size();

        // 한칸씩 이동하면서 자른다~~
        for(int x : topping){
            left_set.add(x);
            right_map.put(x, right_map.get(x)-1);
            // map에서 0이 되면 키도 삭제해줘야 함!!
            if(right_map.get(x) == 0) right_map.remove(x);

            if(left_set.size() == right_map.size())
                answer++;
        }

        return answer;
    }
}