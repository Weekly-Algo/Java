import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        List<String> cache = new ArrayList<>();

        if (cacheSize == 0) {

            return cities.length * 5;
        }

        for (String city : cities) {

            // 대소문자 구분하지 않으므로
            city = city.toLowerCase();

            // 캐시 히트
            if (cache.contains(city)) {
                answer += 1;

                cache.remove(city);

            // 캐시 미스
            } else {
                answer += 5;

                if (cache.size() == cacheSize) {
                    // LRU 실행 앞에꺼 삭제 => 가장 오래된 것
                    cache.remove(0);
                }
            }


            cache.add(city);

        }

        return answer;
    }
}