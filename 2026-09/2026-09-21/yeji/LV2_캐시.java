import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {

        // 캐시 크기가 0일 떄
        if (cacheSize == 0) {
            return cities.length * 5;
        }

        int answer = 0;
        List<String> cache = new ArrayList<>();

        for (String city : cities) {

            city = city.toLowerCase();

            if (cache.contains(city)) {
                // cache hit
                answer += 1;

                // LRU
                cache.remove(city);
                cache.add(city);

            } else {
                // cache miss
                answer += 5;

                // LRU
                if (cache.size() == cacheSize) {
                    cache.remove(0);
                }

                // 새로운 도시 추가
                cache.add(city);
            }
        }

        return answer;
    }
}