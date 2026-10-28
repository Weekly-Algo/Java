class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        String str = ""; //n진수로 변환한 숫자들 저장
        
        int num = 0;

        while(str.length() < t * m) { // (구할숫자의개수*참가인원) 만큼 구하기
            str += Integer.toString(num, n).toUpperCase(); // 0부터 진수변환, 10진수 이상은 알파벳 변환
            num++;
        }

        int idx = p - 1; // 나의 첫 위치
        for(int i = 0; i < t; i++) {
            answer += str.charAt(idx); // 내 차례의 숫자
            idx += m; // 다음 위치로 이동
        }

        return answer;
    }
}