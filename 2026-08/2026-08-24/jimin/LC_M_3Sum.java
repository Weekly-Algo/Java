import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    //투 포인터
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        int N = nums.length;

        Arrays.sort(nums);

        for(int i = 0; i < N - 2; i++) {
            //중복된다면
            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = N - 1;

            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if(sum == 0) {
                    //리스트에 넣기
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    answer.add(list);

                    //이동하기
                    left++;
                    right--;

                    //left 중복제거
                    while(left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    //right 중복제거
                    while(left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }

                } else if(sum < 0) { //합을 늘려야 하므로
                    left++;
                } else { //합을 줄여야 하므로
                    right--;
                }
            }
        }

        return answer;
    }
}