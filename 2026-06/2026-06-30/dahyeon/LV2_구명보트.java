import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {

        Arrays.sort(people);

        int light = 0;
        int heavy = people.length - 1;
        int answer = 0;

        while(light <= heavy){
            if(people[light] + people[heavy] <= limit)
                light++;

            heavy--;
            answer++;
        }
        return answer;
    }
}