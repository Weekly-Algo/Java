class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];

        int changeCount = 0;
        int zeroCount = 0;

        while(!s.equals("1")) {

            StringBuilder sb = new StringBuilder();

            // 0 제거
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '0') {
                    zeroCount++;
                } else {
                    sb.append(s.charAt(i));
                }
            }

            String next = Integer.toBinaryString(sb.length()); // 길이를 2진수로 변환
            s = next; // s 갱신
            changeCount++; // 변환 횟수 증가
        }

        answer[0] = changeCount;
        answer[1] = zeroCount;

        return answer;
    }
}