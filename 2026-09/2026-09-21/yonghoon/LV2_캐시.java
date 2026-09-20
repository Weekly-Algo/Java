import java.util.*;

class LV2_캐시 {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0; // 실행시간

        // 캐시 최대크기가 30이니까 ArrayList사용
        // 나중에 커지면 HashMap 사용해야 할 듯
        List<String> cache = new ArrayList<>();

        // 캐시가 0일때
        if(cacheSize == 0) {
            return 5 * cities.length;
        }

        for(String s : cities) {
            // 소문자로 변경
            String city = s.toLowerCase();

            // 캐시에 없고 캐시 크기가 충분함
            if(!cache.contains(city) && cache.size() < cacheSize) {
                answer += 5;
                cache.add(city);
            } else if(!cache.contains(city)) {
                // 캐시에 없고 캐시가 꽉참
                answer += 5;
                cache.remove(0);
                cache.add(city);
            } else {
                // 캐시에 있음
                answer += 1;
                // 지우고 다시 맨 뒤에 넣기
                cache.remove(city);
                cache.add(city);
            }
        }
        return answer;
    }
}