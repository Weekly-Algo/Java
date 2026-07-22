import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int num = nums.length/2;

        for(int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int count = set.size();
        if(count >= num) count = num;

        return count;
    }
}