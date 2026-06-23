import java.util.*;

class LV2_롤케이크_자르기 {
    public int solution(int[] topping) {
        int answer = 0;

        // 철수와 동생의 토핑 세트
        HashSet<Integer> topping1 = new HashSet<>();
        HashMap<Integer, Integer> topping2 = new HashMap<>();

        // 우선 시작은 맨 앞이므로 동생의 토핑에 모든 것을 넣음
        for(int t : topping) {
            int cnt = topping2.getOrDefault(t, 0);
            topping2.put(t, cnt + 1);
        }

        // 철수의 토핑에 하나씩 추가하면서 사이즈 비교
        for(int t : topping) {
            // 철수
            topping1.add(t);

            // 동생
            int cnt2 = topping2.get(t);
            // 토핑 뺐을 때 해당 토핑이 0이되면 제거
            int remain = cnt2 - 1;
            if(remain == 0)
                topping2.remove(t);
            else
                topping2.put(t, remain);

            if(topping1.size() == topping2.size())
                answer++;
        }
        return answer;
    }
}