// n보다 큰 숫자(10진수) + 1의 갯수 동일(2진수) + 그중에서 가장 작은 수
// 생각과정 : 완전 탐색 + 비트 마스킹으로 갯수 세기?
class Solution {
    public int solution(int n) {

        int cnt = binary(n);
        int answer = n + 1;
        while(binary(answer) != cnt){
            answer++;
        }
        return answer;
    }
    private int binary(int n){

        int cnt = 0;
        while(n > 0){
            n &= (n - 1); // 1의 갯수 세는 법!!!
            cnt ++;
        }
        return cnt;
    }
}