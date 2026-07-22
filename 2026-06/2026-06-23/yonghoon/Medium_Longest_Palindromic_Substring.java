import java.util.*;

class Medium_Longest_Palindromic_Substring {
    public String longestPalindrome(String s) {
        // for문으로 모든 회문 체크 => 시간초과 엔딩
        // 현 위치 기준으로 양쪽으로 늘려가며 체크
        int n = s.length();
        // 1이하면 바로 return
        if(n <= 1) return s;

        int maxLength = 1; // 현재까지 가장 긴 회문 길이
        String answer = s.substring(0, 1); // 현재까지 가장 긴 회문

        for(int i = 0; i < n; i++) {
            int oddLen = checkPalindrome(s, i, i); // 홀수 길이 회문
            int evenLen = checkPalindrome(s, i, i + 1); // 짝수 길이 회문

            int len = Math.max(oddLen, evenLen);

            // 회문 갱신
            if(len > maxLength) {
                maxLength = len;
                answer = s.substring(i - (len - 1) / 2, i - (len - 1) / 2 + len); // 짝수인 경우를 고려해 (len - 1) 필요
            }
        }

        return answer;
    }

    static int checkPalindrome(String s, int left, int right) {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // 조건문에 걸리지 않았지만 left와 right는 각각 증감한 이후이므로 -1을 해주어야함
        return right - left - 1;
    }
}