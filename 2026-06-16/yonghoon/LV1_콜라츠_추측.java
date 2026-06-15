class LV1_콜라츠_추측 {
    public int solution(int num) {
        int answer = collatz(num, 0);
        return answer;
    }

    static int collatz(long num, int cnt) {
        // num이 1이 되면 종료
        if(num == 1)
            return cnt;

        // 반복 횟수 500일 경우 -1리턴
        if(cnt == 500)
            return -1;

        // 짝수일때와 홀수일때 구분
        if(num % 2 == 0) {
            num /= 2;
        } else {
            num = num * 3 + 1;
        }

        return collatz(num, cnt + 1);
    }
}