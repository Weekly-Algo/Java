import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {

        Arrays.sort(people);

        int min = 0;
        int max = people.length - 1;
        int ans = 0;

        while(min <= max) {
            if (people[min] + people[max] <= limit) {
                min++;
                max--;
            } else {
                max--;
            }

            ans++;
        }

        return ans;
    }
}