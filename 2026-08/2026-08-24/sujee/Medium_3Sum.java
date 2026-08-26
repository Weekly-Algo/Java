class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums); // 일단 오름차순 정렬...

        for (int i = 0; i < nums.length - 2; i++) {

            // 중복은 건너뛴다
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(
                            nums[i],
                            nums[left],
                            nums[right]
                    ));

                    // 처음엔 다 돌았었는데 위에서 오름차순 정렬했기 때문에
                    // 이미 0이 되었다면 다른 조합은 찾을 수 없더라!
                    left++;
                    right--;

                    // 중복 제거
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }
}