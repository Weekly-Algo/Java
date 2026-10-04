class Solution {
    public int solution(int n) {

        int countN = countOne(n);

        // n보다 큰 수부터 확인 반복
        for (int i = n + 1; ; i++) {
            if (countN == countOne(i)) {
                return i;
            }
        }
    }

    // 2진수로 변환했을 때 1의 개수 카운트
    private int countOne(int num) {

        int count = 0;

        while (num > 0) {
            if (num % 2 == 1) {
                count++;
            }
            num /= 2;
        }

        return count;
    }
}