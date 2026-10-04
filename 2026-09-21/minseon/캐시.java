import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {

        int ans = 0;

        List<String> cache = new ArrayList<>();

        for (String city : cities) {
            // 대소문자 구별 x -> 다 소문자 통일
            city = city.toLowerCase();

            // 캐시에 이미 있는 경우 -> cache hit (+1)
            if(cache.contains(city)) {
                ans += 1;

                // 기존 위치에서 제거하고
                cache.remove(city);

                // 가장 최근에 사용했으므로 맨 뒤에 다시 추가
                cache.add(city);

            } else {
                // 캐시에 없는 경우 -> cache miss (+5)
                ans += 5;

                // 캐시 사이즈 0 -> 저장할 필요 없음
                if (cacheSize == 0) {
                    continue;
                }

                // 캐시가 꽉 찼다면
                if (cache.size() == cacheSize) {
                    // 가장 사용하지 않은 city 제거
                    // 최근 사용이 뒤에 추가되니까? 가장 사용 안 한건 맨 앞의 값
                    cache.remove(0);
                }

                cache.add(city);
            }
        }

        return ans;
    }
}