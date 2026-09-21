import java.util.LinkedList;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        LinkedList<String> cache = new LinkedList<>();
        for(int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            //캐시에 존재한다면
            if(cache.contains(city)) {
                cache.remove(city);
                cache.offer(city);
                answer += 1;
            //캐시에 존재하지 않는다면
            } else {
                //캐시할 수 없다면
                if(cacheSize == 0) return cities.length * 5;
                 //캐시가 가득 찼다면
                if(cache.size() == cacheSize) {
                    cache.poll();
                }
                cache.offer(city);
                answer += 5;
            }
        }

        return answer;
    }
}