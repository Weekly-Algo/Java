class Solution {
    public int solution(int n) {
        int answer = n + 1; //n보다 1이 크다고 가정

        int target = Integer.bitCount(n); //이진수로 변환하여 1의 개수를 반환

        while(true) {
            //answer의 1의 개수가 target과 같다면
            if(Integer.bitCount(answer) == target) return answer;
            //다르다면
            answer += 1;
        }
    }
}