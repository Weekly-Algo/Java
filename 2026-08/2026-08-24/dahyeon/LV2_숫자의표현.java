class Solution {
    public int solution(int n) {
        int left = 1;
        int right = 1;
        int answer = 0;

        while (left <= n) {
            int count = right - left + 1;
            long sum = (long) (left + right) * count / 2;

            if (sum < n) {
                right++;
            } else {
                if (sum == n) {
                    answer++;
                }

                left++;
            }
        }

        return answer;
    }
}