class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            long n = numbers[i];

            // 짝수는 마지막 비트가 0이므로 1만 더하면 됨
            if (n % 2 == 0) {
                answer[i] = n + 1;
            } else {
                // 홀수는 가장 오른쪽의 0을 찾아 1로 변경
                long bit = 1;
                while ((n & bit) != 0) {
                    bit <<= 1;
                }

                answer[i] = n + bit - (bit >> 1);
            }
        }

        return answer;
    }
}
