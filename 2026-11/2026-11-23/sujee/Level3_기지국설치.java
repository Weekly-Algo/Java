class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int idx = 1;
        int len = 2*w + 1;

        for(int s : stations) {
            int left = (s - w) - idx; // 전파 왼쪽 끝 전까지가 빈 구간

            if(left > 0) {
                int tmp = left / len;
                if(left % len != 0) tmp++;
                answer += tmp;
            }
            idx = s + w + 1; // 전파 오른쪽 끝 다음부터
        }

        int left = n - idx + 1; // idx ~ n 포함

        if(left > 0) {
            int tmp = left / len;
            if(left % len != 0) tmp++;
            answer += tmp;
        }

        return answer;
    }
}