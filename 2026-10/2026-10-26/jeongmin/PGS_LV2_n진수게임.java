
class Solution {
    public String solution(int n, int t, int m, int p) {
        
        
        StringBuilder sb = new StringBuilder();
        
        int num = 0;
        while (sb.length() < t * m) {
            sb.append(conversion(num, n));
            num++;
        }
        
        StringBuilder answer = new StringBuilder();
        
        for (int i = 0; i < t; i++) {
            int index = p - 1 + (i * m);
            answer.append(sb.charAt(index));
        }
        
        
        return answer.toString();
        
        
    
    }
    
    public static String conversion(int number, int n){
        
        if (number == 0) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
	    int current = number;
	    
        
        while(current > 0){
            if(current % n < 10){
                sb.append(current % n);       
            } else {
                sb.append((char)(current % n - 10 + 'A'));
            }
            current /= n;
        }
        return sb.reverse().toString();
    }
}