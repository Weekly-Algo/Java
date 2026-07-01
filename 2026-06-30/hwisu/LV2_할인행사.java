import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        // 원하는 제품과 수량을 맵에 저장
        Map<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        // 첫 윈도우(0~9일) 세팅
        Map<String, Integer> curMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            curMap.put(discount[i], curMap.getOrDefault(discount[i], 0) + 1);
        }

        if (curMap.equals(wantMap)) {
            answer++;
        }

        // 윈도우를 한 칸씩 밀기
        for (int i = 10; i < discount.length; i++) {
            // 오른쪽 제품 추가
            curMap.put(discount[i], curMap.getOrDefault(discount[i], 0) + 1);

            // 왼쪽 제품 제거
            String out = discount[i - 10];
            curMap.put(out, curMap.get(out) - 1);
            if (curMap.get(out) == 0) {
                curMap.remove(out);
            }

            if (curMap.equals(wantMap)) {
                answer++;
            }
        }

        return answer;
    }
}