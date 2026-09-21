class Solution {
    public int solution(int n) {
        int cnt = 0;

        String N = Integer.toBinaryString(n);
        for(int i = 0; i < N.length(); i++) {
            if(N.charAt(i) == '1') cnt++;
        }

        while(true) {
            String tmp = Integer.toBinaryString(++n);

            int k = 0;
            for(int i = 0; i < tmp.length(); i++) {
                if(tmp.charAt(i) == '1') k++;
            }

            if(k == cnt) return n;

        }

    }
}