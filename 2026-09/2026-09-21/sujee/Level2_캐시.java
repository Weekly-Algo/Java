import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        Set<String> set = new HashSet<>();
        Queue<String> q = new LinkedList<>();

        for(int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase(); // 대소문자 하나로

            // 캐시 히트
            if(set.contains(city)) {
                answer += 1;

                // 기존 위치에서 제거하고 가장 최근 위치로 이동
                q.remove(city);
                q.offer(city);


            }
            else {  // 캐시 미스
                answer += 5;

                // 캐시가 꽉 찼으면 가장 오래된 도시 제거
                if(q.size() == cacheSize) {
                    String old = q.poll();
                    set.remove(old);
                }

                // cacheSize가 0이면 저장하지 않음
                if(cacheSize > 0) {
                    q.offer(city);
                    set.add(city);
                }
            }
        }

        return answer;
    }
}