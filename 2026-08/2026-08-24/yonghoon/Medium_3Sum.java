import java.util.*;

class Medium_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        int n = nums.length;

        // 정렬
        Arrays.sort(nums);

        // 투 포인터로 체크
        for(int i = 0; i < n - 2; i++) {
            // nums[i]가 이전과 같으면 같은 결과값일 것이므로 패스
            if(i > 0 && nums[i] == nums[i - 1])
                continue;

            // nums[i]가 양수면 이후 숫자들도 양수일 것이므로 0을 만들수가 없음
            if(nums[i] > 0)
                break;

            int left = i + 1;
            int right = n - 1;

            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                // sum이 0이되면 저장
                if(sum == 0) {
                    List<Integer> numList = new ArrayList<>();
                    numList.add(nums[i]);
                    numList.add(nums[left]);
                    numList.add(nums[right]);

                    // answer에 저장
                    answer.add(numList);

                    // left, right 증감
                    left++;
                    right--;

                    // left 중복 제거
                    while(left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    // right 중복 제거
                    while(left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if(sum < 0) {
                    // sum이 0보다 작으면 left를 올려서 수를 크게 만들어줘야함
                    left++;
                } else {
                    // sum이 0보다 크면 right를 내려서 수를 작게 만들어줘야함
                    right--;
                }
            }
        }

        return answer;
    }
}