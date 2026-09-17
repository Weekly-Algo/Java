import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        
        int answer = 0;
        List<String> list = new ArrayList<>();
        
        for (int i=0; i<cities.length; i++) {
            cities[i] = cities[i].toUpperCase();
        } // for 대문자로 일괄 치환
        
        

        for (int i=0; i<cities.length; i++) {
            if (list.size() < cacheSize) {
                if (list.contains(cities[i])) {
                    list.remove(cities[i]);
                    list.add(cities[i]);
                    answer ++ ;
                }
                else {
                    list.add(cities[i]);
                    answer += 5;
                }    
                continue;
            }
            if (list.contains(cities[i])) {
                list.remove(cities[i]);
                list.add(cities[i]);
                answer ++ ;
            }
            else {
                list.remove(0);
                list.add(cities[i]);
                answer += 5;
            }    
            
        } // for 
        
        return answer;
    } // solution
}