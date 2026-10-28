class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder all = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        
        // 필요한 길이만큼 n진수로 이어붙이기
        int num = 0;
        while (all.length() < t*m) {
            all.append(Integer.toString(num, n).toUpperCase());
            num++;
        } // while
        
        // 튜브 순서 글자만 뽑기
        for (int i=0; i<t; i++) {
            answer.append(all.charAt(p-1 + i*m));
        } // for
        
        return answer.toString();
        
    } // solution
} // Solution