import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        // 귤의 크기별 개수를 저장
        Map<Integer, Integer> countMap = new HashMap<>();

        // 크기별로 귤이 몇 개 있는지 계산
        // key, value 값 저장
        // ex) size가 1 -> 아직 저장되지 않은 값이라 0 + 1 = 1 value 값 저장
        for (int size : tangerine) {
            countMap.put(
                    size,
                    countMap.getOrDefault(size, 0) + 1
            );
        }

        // 크기별 개수만 리스트에 저장
        List<Integer> counts = new ArrayList<>(countMap.values());

        // 귤 개수를 내림차순으로 정렬
        counts.sort(Collections.reverseOrder());

        int selected = 0; // 지금까지 고른 귤의 수
        int ans = 0;   // 사용한 귤 크기의 종류 수

        // 개수가 많은 종류부터 선택
        for (int count : counts) {
            selected += count;
            ans++;

            // 필요한 귤을 모두 골랐다면 종료
            if (selected >= k) {
                break;
            }
        }

        return ans;
    }
}