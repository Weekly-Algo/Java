class Solution {
    static int count;

    public int solution(int num) {
        count = 0; //테스트마다 초기화

        int answer = calc((long) num);

        return answer;
    }

    public int calc(long num) {
        //기저조건1: num이 1이 되면 현재 count 반환
        if(num == 1) return count;

        //기저조건2: 500번 이상 반복했는데도 1이 아니면 -1 반환
        if(count >= 500) return -1;

        //콜라츠 연산을 한 번 수행하므로 count 증가
        count++;

        //재귀파트
        if(num % 2 == 0) {
            num /= 2;
        } else {
            num = num * 3 + 1;
        }

        //재귀 호출 결과를 return
        return calc(num);
    }
}