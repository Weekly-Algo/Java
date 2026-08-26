import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // 3개 더해서 합이 0이 되는 조합 찾기
        // 3중 for문 시간복잡도;;
        // 투포인터 써야 됨!

        // 배열 정렬한 다음 -> 하나의 숫자를 고정
        // 그 숫자를 기준으로 겹치지 않게 left, right 값 설정

        // 만약 정답이 if (sum == 0) -> return
        // 0보다 작으면 -> left 값 이동 -> 숫자를 더 크게 만들어야 함
        // 0보다 크면 -> right 값 이동 -> 숫자를 더 작게 만들어야 함


        // 결과 저장할 배열
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums); // 정렬

        // 첫 번째 숫자부터 고정하면서 for문 반복
        // left, right 값 있으니 length의 -2 까지만
        for (int i = 0; i < nums.length - 2; i++) {
            // 중복되면 건너뜀
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            // left와 right 만나기 전까지 반복
            while (left < right) {
                // 세 숫자의 합을 계산
                int sum = nums[i] + nums[left] + nums[right];

                // 세 숫자의 합이 0이면 정답!!
                if (sum == 0) {
                    result.add(Arrays.asList(
                            nums[i],
                            nums[left],
                            nums[right]
                    ));

                    // left 중복 처리
                    // 이전 값이랑 같으면 한 칸 옆으로 이동 (새로운 값 찾아서)
                    while (left < right && nums[left] == nums[left+1]) {
                        left++;
                    }

                    left++;
                    right--;
                }

                else if (sum < 0) {
                    // 합이 0보다 작으면 left 이동
                    left++;
                } else {
                    right--;
                }
            }
        }

        // for문 다 돌고 result 리턴
        return result;
    }
}