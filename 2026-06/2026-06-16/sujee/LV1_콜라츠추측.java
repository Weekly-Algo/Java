class Solution {
    public int solution(int num) {

        int answer = 0;

        long n = num;

        if(num == 1) return answer;

        while(n != 1 && answer < 500){
            if(n % 2 == 0) n /= 2;
            else n = (n * 3) + 1;

            answer++;
        }

        if(answer >= 500) answer = -1;
        return answer;
    }
}