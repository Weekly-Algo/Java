import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        String[] cache = new String[cacheSize];
        int shinip = 1;
        int flag = 0;

        // 수정 1: 도시가 없는 경우
        if (cities.length == 0) return 0;

        if (cacheSize > 0) {
            cache[0] = cities[0];
            answer += 5; // 수정 2: 첫 번째 도시의 cache miss 비용
        }
        else return cities.length * 5;

        for (int i = 1; i < cities.length; i++) {
            if (cacheSize > shinip) {
                boolean check = false;

                for (int j = 0; j < shinip; j++)
                    if (cache[j].equalsIgnoreCase(cities[i])) {
                        check = true;
                        flag = j;
                    }

                if (check) {
                    answer++;

                    // 수정 3: 캐시가 가득 차지 않았어도
                    // cache hit 발생 시 가장 최근 사용 위치로 이동
                    String tmp = cache[flag];

                    for (int j = flag + 1; j < shinip; j++)
                        cache[j - 1] = cache[j];

                    cache[shinip - 1] = tmp;
                }
                else {
                    cache[shinip++] = cities[i];
                    answer += 5;
                }
            }
            else {
                boolean check = false;

                for (int j = 0; j < shinip; j++)
                    if (cache[j].equalsIgnoreCase(cities[i])) {
                        check = true;
                        flag = j;
                    }

                if (check) {
                    answer++;

                    String tmp = cache[flag];

                    for (int j = flag + 1; j < cache.length; j++)
                        cache[j - 1] = cache[j];

                    cache[cache.length - 1] = tmp;
                }
                else {
                    for (int j = 1; j < cache.length; j++)
                        cache[j - 1] = cache[j];

                    cache[cache.length - 1] = cities[i];
                    answer += 5;
                }
            }
        }

        return answer;
    }
}