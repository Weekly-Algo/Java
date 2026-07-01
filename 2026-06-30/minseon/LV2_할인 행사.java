import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int ans = 0;

        // 상품 이름, 개수 ex. banana 3
        Map<String, Integer> wantMap = new HashMap<>();

        // 원하는 배열 wantMap
        for (int i = 0; i < want.length; i++) {
            // want[i] 제품을 number[i]만큼 원함
            wantMap.put(want[i], number[i]);
        }

        // 연속된 10일 구간을 하나씩 확인
        // 배열 범위가 주어지니 discount - 10 주기로
        for (int start = 0; start <= discount.length - 10; start++) {
            // 현재 10일 동안의 할인하는 상품 개수 저장
            Map<String, Integer> discountMap = new HashMap<>();

            // start(시작일)부터 start + 9 까지 (10일 주기니까!!)
            for (int day = start; day < start + 10; day++) {
                // 현재 날짜에 할인하는 상품 이름 확인
                String product = discount[day];

                // product가 이미 discountMap에 있으면 기존 개수 + 1
                // product가 처음 나온 상품이면 0에서 시작해서 + 1
                // 이 메서드 굉장히 유용하네이;;
                discountMap.put(product, discountMap.getOrDefault(product, 0) + 1);
            }

            // 내가 원하는 구성과 10일간 할인 구성이 같으면
            // 정답 ans ++
            if (wantMap.equals(discountMap)) {
                ans++;
            }
        }

        return ans;
    }
}