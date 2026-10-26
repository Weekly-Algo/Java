class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        StringBuilder answer = new StringBuilder();

        int num = 0;

        // n진수로 변환하여 문자열 생성
        while (sb.length() < t * m) {
            sb.append(Integer.toString(num, n).toUpperCase());
            num++;
        }

        // 말할 문자 추출
        for (int i = 0; i < t; i++) {
            int index = p - 1 + i * m;
            answer.append(sb.charAt(index));
        }

        return answer.toString();
    }
}