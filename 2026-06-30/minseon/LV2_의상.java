import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        // 옷 종류별 개수를 저장할 HashMap
        // 예: "headgear" -> 2, "eyewear" -> 1
        Map<String, Integer> map = new HashMap<>();

        // clothes 배열을 돌면서 옷 종류별 개수 세기
        for (int i = 0; i < clothes.length; i++) {
            // clothes[i][0]은 옷 이름
            // clothes[i][1]은 옷 종류
            String type = clothes[i][1];

            // 해당 종류가 이미 있으면 기존 개수 + 1
            // 처음 보는 종류면 0에서 시작해서 + 1
            map.put(type, map.getOrDefault(type, 0) + 1);
        }

        // 전체 조합 수
        int ans = 1;

        // 각 옷 종류별 선택 개수
        for (int count : map.values()) {
            // count개의 옷 중 하나를 고르는 경우 + 아예 안 입는 경우 1개
            ans *= (count + 1);
        }

        // 아무것도 안 입는 경우는 제외
        return ans - 1;
    }
}