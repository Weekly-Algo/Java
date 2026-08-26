class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();

        // 오름차순 정렬
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {

            // 이전 숫자와 같으면 중복이므로 건너뜀
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // i 다음 위치부터 양쪽 포인터 설정
            int left = i + 1;
            int right = nums.length - 1;

            // 두 포인터가 만날 때까지 반복
            while (left < right) {

                // 세 숫자의 합
                int sum = nums[i] + nums[left] + nums[right];

                // 합이 0이면 정답에 추가
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();

                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);

                    answer.add(list);

                    // 다음 숫자 확인
                    left++;
                    right--;

                    // left의 중복된 숫자 건너뜀
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }

                    // right의 중복된 숫자 건너뜀
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }

                // 합이 0보다 작으면 더 큰 값을 더하도록 left 증가
                } else if (sum < 0) {
                    left++;

                // 합이 0보다 크면 더 작은 값을 더하도록 right 감소
                } else {
                    right--;
                }
            }
        }

        return answer;
    }
}