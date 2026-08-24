import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> answer = new ArrayList<>();

        // 정렬해줍니다.
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 2; i++){

            // 맨 앞이 0보다 크면 가망이 없쥬,,,
            if(nums[i] > 0) break;
            // 기준인 i 랑 전에꺼랑 같으면, 굳이 세는 의미 없잖아
            if(i > 0 && nums[i] == nums[i-1]) continue;

            // 투포인터 활용 시작
            // i < left < right
            int left = i + 1;
            int right = nums.length - 1;

            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];

                if(sum < 0)
                {
                    left++;
                }
                else if (sum > 0)
                {
                    right--;
                }
                else {
                    answer.add(
                        Arrays.asList(nums[i], nums[left], nums[right])
                    );

                    int leftValue = nums[left];
                    int rightValue = nums[right];

                    while (left < right && nums[left] == leftValue) {
                        left++;
                    }

                    while (left < right && nums[right] == rightValue) {
                        right--;
                    }
                }
            }
        }

        return answer;

    }
}